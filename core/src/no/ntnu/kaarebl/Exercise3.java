package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Exercise3 implements Screen{

    HelicopterAnimated heli;
    Stage stage;
    SpriteBatch batch;
    BitmapFont font;


    public Exercise3(TDT4240Game game) {
        create();
    }

    public void create() {
        stage = new Stage(new ScreenViewport());
        heli = new HelicopterAnimated(200, 200, 1, 1);
        stage.addActor(heli);
        batch = new SpriteBatch();
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
        font.draw(batch, "X:" + heli.xPos + " Y:" + (Constants.screenHeight - heli.yPos), 30, Constants.screenHeight - 30);
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
}