package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.event.InputEvent;

/**
 * Created by kbl on 1/25/15.
 */
public class MainMenuScreen implements Screen {

    private TDT4240Game game;
    private Stage stage;
    private InputListener inputListener;
    private Table btnTable;
    private SpriteButton btn1;
    private SpriteButton btn2;


    public MainMenuScreen(TDT4240Game gam){
        create();
        game = gam;

    }
    public void create(){
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        inputListener = new InputListener();

        btnTable = new Table();

        btn1 = new SpriteButton("button.png");
        btn2 = new SpriteButton("button2.png");
        btnTable.add(btn1).row();
        btnTable.add(btn2).row();
        btnTable.setFillParent(true);
        stage.addActor(btnTable);
        Gdx.input.setInputProcessor(stage);


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
        //stage.getViewport().update(width,height,true);
        stage.getCamera().update();
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
