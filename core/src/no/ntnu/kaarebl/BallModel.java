package no.ntnu.kaarebl;

import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by kbl on 2/1/15.
 */
public class BallModel extends Actor {
    float xSpeed;
    float ySpeed;
	BallView view;
	BallController controller;

    public BallModel(){
        setX(200);
        setY(400);
        xSpeed = 200;
        ySpeed = 321;
		view = new BallView(this);
		controller = new BallController(this);
    }
	
	public void setPosition(float xPos,float yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void draw(float delta){
		view.draw(float);
	}
	
	public void act(float delta){
		controller.act(delta);
	}
	
	act(delta);
	draw(delta);
}
