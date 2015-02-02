package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kbl on 2/2/15.
 */
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
        rotateBy(270);
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getHeight(), (int)getWidth());
        setBounds();
    }
    public void setBounds(){
        bounds.set(getX(),getY(),getHeight(),getWidth());
    }

    public void moveToBall(Ball ball){
        sprite.setX(ball.getX());
        setBounds();
        System.out.print(ball.getX());
    }

    public void moveToTouch(float y){
        sprite.setY(y);
    }

    @Override
    public void act(float delta) {
        if(isComputer){
            moveToBall(ball);
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(sprite,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),1,1,getRotation());
    }
}