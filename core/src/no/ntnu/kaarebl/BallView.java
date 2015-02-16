package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by kbl on 2/1/15.
 */
public class BallView {
	BallModel ball;
    Sprite sprite;
    Texture texture;
    Rectangle bounds;
	Batch batch;

    public BallView(Ballmodel ball){
		this.ball = ball;
		//Set the texture and sprite for the ball
        texture = new Texture("ball.png");
        sprite = new Sprite(texture);
		//Make a new batch for this particular ball
		batch = new Batch();
		//Set the height and width so that collision detections has something to work with.
        ball.setHeight(texture.getHeight());
        ball.setWidth(texture.getWidth());
		//Surround the ball with a rectangle that has the dimensions of the sprite. 
        bounds=new Rectangle((int)ball.getX(), (int)ball.getY(), (int)ball.getWidth(), (int)ball.getHeight());
        ball.setBounds();
		
    }
	//Call this on every gametick, or else the collision wont work!
    public void setBounds(){
        ball.bounds.set(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight());
    }
	//Called on every tick, draws the ball. 
    public void draw(float parentAlpha){
        batch.draw(sprite,ball.getX(),ball.getY());
    }
}