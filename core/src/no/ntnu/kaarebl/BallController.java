package no.ntnu.kaarebl;


/**
 * Created by kbl on 2/1/15.
 */
public class BallController{
	BallModel ball;
	
    public BallController(BallModel ball;){
		this.ball = ball;
    }
	
	//Gets called when the ball collides with an object. 
    public void collision(){
        ball.ySpeed = ball.ySpeed*-1;
    }
	
	//Updates the balls position based on the time elapsed since last tick
    public void updatePosition(float delta){
        if (ball.getX()+ball.getHeight()>480){
            ball.xSpeed = ball.xSpeed*-1;
        }
        if (ball.getX()<0){
            ball.xSpeed = ball.xSpeed*-1;
        }
        ball.setX(ball.getX()+ball.xSpeed*delta);
        ball.setY(ball.getY()+ball.ySpeed*delta);
        ball.view.setBounds();
    }
	
	//Gets called by the ball model for every game tick. 
    public void act(float delta) {
        updatePosition(delta);
        super.act(delta);
    }
}
