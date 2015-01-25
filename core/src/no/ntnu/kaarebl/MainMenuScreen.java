package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by kbl on 1/25/15.
 */
public class MainMenuScreen implements Screen {

    final TDT4240Game game;

    private Stage stage;
    private Skin skin;
    private BitmapFont font;
    private OrthographicCamera camera;

    public MainMenuScreen(final TDT4240Game gam){
        game = gam;
        stage = new Stage();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Constants.screenWidt,Constants.screenHeight);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        TextButton ex1Btn = new TextButton("Exercise 1",skin);

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(255, 0, 192, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
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
