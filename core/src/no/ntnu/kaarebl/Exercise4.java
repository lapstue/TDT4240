package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by kbl on 2/1/15.
 */
public class Exercise4 implements Screen, InputProcessor {

    private Stage stage;
    private BitmapFont font;
    private SpriteBatch batcher;
    private Ball ball;
    private int player1Score;
    private int player2Score;
    private Paddle player1Paddle;
    private Paddle player2Paddle;
    private String player1String;
    private String player2String;
    private float scaling;

    public Exercise4(){
        create();
    }

    public void create() {
        //Scaling factor used to translate screen coordinates to stage coordinates
        scaling = 480/Constants.screenWidt;
        stage = new Stage(new StretchViewport(480,800));
        batcher = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"),Gdx.files.internal("font.png"),false);
        ball = new Ball();
        player1Score = 0;
        player2Score = 0;
        player1String = "" + player1Score;
        player2String = "" + player2Score;
        //Set player1Paddle to be touch controlled and set start postion
        player1Paddle = new Paddle(false,ball,240,75);
        //player2Paddle is computer controlled
        player2Paddle = new Paddle(true,ball,240,725);
        stage.addActor(ball);
        stage.addActor(player1Paddle);
        stage.addActor(player2Paddle);
        //Makes sure the Exercise4 class handles touch inputs
        Gdx.input.setInputProcessor(this);
    }

    //Checks whether player 1 or player 2 paddle overlaps the ball. In that case trigger bounce
    public void checkCollision(){
        if(player1Paddle.bounds.overlaps(ball.bounds)){
            ball.collision();
        }
        if(player2Paddle.bounds.overlaps(ball.bounds)){
            ball.collision();
        }
    }

    //Checks if the ball has left either screen in y direction, in that case increment score
    public void checkIfScore(){
        //Player 2 has scored a point. Award point and reset the ball
        if(ball.getY()<0){
            player2Score = player2Score + 1;
            player2String = "" + player2Score;
            System.out.println(player2Score);
            ball.setPosition(240,400);
            if(player2Score>20){
                endGame("Player2");
            }
        }
        //Player1 has scored a point. Award point and reset the ball
        if(ball.getY()>800){
            player1Score = player1Score + 1;
            player1String = "" + player1Score;
            System.out.println(player1Score);
            ball.setPosition(240,400);
            if(player1Score>20){
                endGame("Player1");
            }
        }
    }

    public void endGame(String player){
        player1Paddle.remove();
        player2Paddle.remove();
        ball.remove();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //First check if a collision is to be triggered
        checkCollision();
        //Check that the ball has not left the screen, if it has score and reset
        checkIfScore();
        //Move the on screen components according to the deltaT elapsed since last frame
        stage.act(delta);

        //Call the draw function of all the actors added to the scene.
        stage.draw();
        batcher.begin();
        font.draw(batcher, player1String, 36, 100/scaling);
        font.draw(batcher, player2String, 36, 700/scaling);
        batcher.end();
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
        //Tell the player1Paddle to move to the coordinate of touch.
        player1Paddle.moveToTouch(screenX*scaling);
        //Remember to return true to indicate that the input has been processed.
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
