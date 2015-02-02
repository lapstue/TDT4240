package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen implements Screen {

    private TDT4240Game game;
    private Stage stage;
    private InputListener inputListener;
    private Image btn1;
    private Image btn2;
    private Image btn3;
    private Image btn4;
    private Table btnTable;


    public MainMenuScreen(TDT4240Game gam){
        create();
        game = gam;

    }
    public void create(){
        //Force the menu screen to have the size 480*800, and stretch the screen to fit the actual
        //screensize. This makes sure that the buttons are readable on all devices, even HIDPI
        stage = new Stage(new StretchViewport(480,800));
        Gdx.input.setInputProcessor(stage);
        inputListener = new InputListener();
        btn1 = new Image(new Texture("button.png"));
        btn2 = new Image(new Texture("button2.png"));
        btn3 = new Image(new Texture("button3.png"));
        btn4 = new Image(new Texture("button4.png"));

        btnTable = new Table();
        btnTable.add(btn1).padBottom(10).row();
        btnTable.add(btn2).padBottom(10).row();
        btnTable.add(btn3).padBottom(10).row();
        btnTable.add(btn4).padBottom(10).row();

        btnTable.setFillParent(true);
        stage.addActor(btnTable);
        Gdx.input.setInputProcessor(stage);

        btn1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Exercise12(game,true,false));
            }
        });
        btn2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Exercise12(game,true,true));
            }
        });
        btn3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Exercise3(game));
            }
        });
        btn4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Exercise4());
            }
        });


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
