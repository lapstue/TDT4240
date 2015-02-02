package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
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
public class Exercise4 implements Screen {

    private Stage stage;
    private Ball ball;
    private int player1Score;
    private int player2Score;
    private Paddle player1Paddle;
    private Paddle player2Paddle;
    private SpriteBatch batch;

    public Exercise4(){
        create();
    }

    public void create() {
        stage = new Stage(new StretchViewport(480,800));
        batch = new SpriteBatch();
        ball = new Ball();
        player1Score = 0;
        player2Score = 0;
        player1Paddle = new Paddle(false,ball,150,50);
        player2Paddle = new Paddle(true,ball,200,300);
        stage.addActor(ball);
        stage.addActor(player1Paddle);
        stage.addActor(player2Paddle);
    }

    public void checkCollision(){
        if(player1Paddle.bounds.overlaps(ball.bounds)){
            ball.collision();
        }
        if(player2Paddle.bounds.overlaps(ball.bounds)){
            ball.collision();
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
}
