package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by kbl on 2/1/15.
 */
public class Exercise4 implements Screen, InputProcessor {

    private Stage stage;
    private Ball ball;
    private int player1Score;
    private int player2Score;
    private Paddle player1Paddle;
    private Paddle player2Paddle;
    private SpriteBatch batch;
    private float scaling;

    public Exercise4(){
        create();
    }

    public void create() {
        stage = new Stage(new StretchViewport(480,800));
        scaling = 480/Constants.screenWidt;
        batch = new SpriteBatch();
        ball = new Ball();
        player1Score = 0;
        player2Score = 0;
        player1Paddle = new Paddle(false,ball,240,75);
        player2Paddle = new Paddle(true,ball,240,725);
        stage.addActor(ball);
        stage.addActor(player1Paddle);
        stage.addActor(player2Paddle);
        Gdx.input.setInputProcessor(this);
    }

    public void checkCollision(){
        if(player1Paddle.bounds.overlaps(ball.bounds)){
            ball.collision();
        }
        if(player2Paddle.bounds.overlaps(ball.bounds)){
            ball.collision();
        }
    }

    public void checkIfScore(){
        if(ball.getY()<0){
            player2Score = player2Score + 1;
            System.out.println(player2Score);
            ball.setPosition(240,400);
        }
        if(ball.getY()>800){
            player1Score = player1Score + 1;
            System.out.println(player1Score);
            ball.setPosition(240,400);
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        checkCollision();
        checkIfScore();
        stage.act(delta);
        stage.draw();
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        player1Paddle.moveToTouch(screenX*scaling);
        return true;
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
