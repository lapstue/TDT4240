package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Paddle extends Actor {
    Sprite sprite;
    Texture texture;
    boolean isComputer;
    Ball ball;
    Rectangle bounds;

    public Paddle(boolean isComputer, Ball ball, float xPos, float yPos){
        texture = new Texture("paddle.png");
        sprite = new Sprite(texture);
        this.isComputer = isComputer;
        this.ball = ball;
        setHeight(texture.getHeight());
        setWidth(texture.getWidth());
        setX(xPos);
        setY(yPos);
        //Rotates the paddle by 270 degrees. 90 degrees makes the collision rectangle and the sprite
        //not match.
        rotateBy(270);
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getHeight(), (int)getWidth());
        //Not strictly necessary, but let's do it anyway.
        setBounds();
    }
    //Set collision bounds. Must be called each frame, in case the paddle has moved since last time.
    public void setBounds(){
        bounds.set(getX(),getY(),getHeight(),getWidth());
    }

    //Reads the xPos of the ball and sets the paddle to the same position.
    public void moveToBall(Ball ball){
        //Correct for the fact that the sprite's (0,0) is the bottom left of the sprite
        //remember that since the sprite has been rotated by 270 degrees, the height is not width
        if(ball.getX()+getHeight()>480){
            setX(480-getHeight());
        }
        //No need to correct for (0,0) of the sprite.
        else{
            setX(ball.getX());
        }
        setBounds();
    }

    //Responds to the xPos of a touchDragged event and moves the paddle accordingly.
    public void moveToTouch(float x){
        if(x+getHeight()>480){
            x = 480-getHeight();
        }
        if (x<0){
            x = 0+getHeight();
        }
        setX(x);
        setBounds();
    }

    @Override
    public void act(float delta) {
        //If the paddle is controlled by the computer, let the paddle move to the balls xPos
        if(isComputer){
            moveToBall(ball);
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        //Draw the sprite at the given coordinates. Remember to include getRotation to make sure the
        //rotation is set correctly.
        batch.draw(sprite,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
    }
}
