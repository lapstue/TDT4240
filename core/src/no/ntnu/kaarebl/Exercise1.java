package no.ntnu.kaarebl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Exercise1 implements Screen {

    float dT;
    int fpsString;
    TDT4240Game game;
    BitmapFont font;
    SpriteBatch batch;
    Helicopter heli;

    public Exercise1(TDT4240Game game){
        create();
    }
    public void create () {
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        Constants.screenHeight = Gdx.graphics.getHeight();
        Constants.screenWidt = Gdx.graphics.getWidth();

        batch = new SpriteBatch();
        heli = new Helicopter(500,500,1,1);
        //heli.moveTo(270,120);
    }

    //Gamelogic. Feeds different update methods with the deltaTime since last execution,
    public void update(float dT){
        heli.update(dT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        fpsString = Gdx.graphics.getFramesPerSecond();
        dT = Gdx.graphics.getDeltaTime();
        //Sets the background color to match the background around the helicopter sprite and clears
        //the screen with this color.
        Gdx.gl.glClearColor(255, 0, 192, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(dT);
        batch.begin();
        heli.draw(batch);
        //Displays the current FPS. Should probably increase the text size.
        font.draw(batch, ""+fpsString, 20,20);
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
