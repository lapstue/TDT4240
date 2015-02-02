package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Random;

public class Exercise3 implements Screen{

    HelicopterAnimated heli;
    Stage stage;
    SpriteBatch batch;
    BitmapFont font;
    ArrayList<Exercise3Ball> balls;
    ArrayList<Exercise3Ball> collisionBalls;
    Exercise3Ball currentBall;
    Random rand;

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
        balls = new ArrayList<Exercise3Ball>();
        collisionBalls = new ArrayList<Exercise3Ball>();
        rand = new Random();
        createBalls();
    }

    public void createBalls(){
        for(int i = 0;i<10;i++){
            currentBall = new Exercise3Ball();
            //Give the ball random xSpeed, ySpeed and xPos and yPos
            currentBall.xSpeed = rand.nextFloat() * (700+700)-700;
            currentBall.ySpeed = rand.nextFloat() * (700-100)+100;
            currentBall.setX(rand.nextFloat() * (Constants.screenWidth +50));
            currentBall.setY(rand.nextFloat() * (Constants.screenHeight+50));
            balls.add(currentBall);
            stage.addActor(currentBall);
        }
    }

    public void checkCollision(){
        //Copy balls list into collisionBalls
        collisionBalls = balls;
        for(int i = 0;i<collisionBalls.size();i++){
            //Get a ball out of the list,and check for collisions for all balls with an index larger
            currentBall = collisionBalls.get(i);
            for(int j = i;j<collisionBalls.size();j++){
                //Collision has been detected
                if(currentBall.bounds.overlaps(collisionBalls.get(j).bounds)){
                    //invoke collision method on the currentball and the ball it collided with.
                    currentBall.collision();
                    collisionBalls.get(j).collision();
                }
            }
        }
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(128, 0, 128, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Remember to check for collisions before drawing
        stage.act(Gdx.graphics.getDeltaTime());
        checkCollision();
        stage.draw();
        batch.begin();
        //Draw the Helicopters position on screen
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