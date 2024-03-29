package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Exercise12 implements Screen, InputProcessor {

    Helicopter heli;
    Stage stage;
    SpriteBatch batch;
    BitmapFont font;
    Boolean bounceMode;
    Boolean acceptHelicopterCClick;


    public Exercise12(TDT4240Game game, Boolean bounceMode, Boolean acceptHelicopterClick) {
        //If true, this instance is for task 2, otherwise its for task 1
        this.acceptHelicopterCClick = acceptHelicopterClick;
        this.bounceMode = bounceMode;
        //If true, that means that this is task 3
        create();
    }

    public void create() {
        stage = new Stage(new ScreenViewport());
        heli = new Helicopter(100, 100, 1, 1, bounceMode, acceptHelicopterCClick);
        stage.addActor(heli);
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(128, 0, 128, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.begin();
        font.draw(batch, "X:"+heli.getX()+" Y:"+(Constants.screenHeight-heli.getY()),30,Constants.screenHeight-30);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Touch down!");
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //If false, just ignore the click, but return true, just to show that we handled the event.
        if(acceptHelicopterCClick){
            MoveToAction mta = new MoveToAction();
            mta.setPosition(heli.sanitizeX(screenX), heli.sanitizeY(Constants.screenHeight-screenY-heli.getHeight()/2));
            mta.setDuration(2);
            heli.addAction(mta);
            heli.bounceMode = false;
            System.out.print("Touch up at " + "X:" + screenX + " Y:"+screenY);
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
