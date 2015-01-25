package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.event.InputEvent;

/**
 * Created by kbl on 1/25/15.
 */
public class MainMenuScreen implements Screen {

    private TDT4240Game game;
    private Stage stage;
    private Skin skin;
    private static BitmapFont font;
    private OrthographicCamera camera;
    TextButton ex1Btn;


    public MainMenuScreen(TDT4240Game gam){
        create();
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(true,Constants.screenHeight,Constants.screenWidt);

    }
    public void create(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton ex1Btn = new TextButton("Exercise 1",skin,"default");
        ex1Btn.setWidth(200f);
        ex1Btn.setHeight(50f);
        ex1Btn.setPosition(Constants.screenWidt/2-ex1Btn.getWidth()/2,Constants.screenHeight/2);
        stage.addActor(ex1Btn);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 2, 200, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
