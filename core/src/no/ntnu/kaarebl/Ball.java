package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by kbl on 2/1/15.
 */
public class Ball extends Actor {
    Sprite sprite;
    Texture texture;
    float xSpeed;
    float ySpeed;
    Rectangle bounds;

    public Ball(){
        texture = new Texture("ball.png");
        sprite = new Sprite(texture);
        setHeight(texture.getHeight());
        setWidth(texture.getWidth());
        setX(200);
        setY(400);
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
        setBounds();
        xSpeed = 200;
        ySpeed = 321;
    }
    public void setBounds(){
        bounds.set(getX(),getY(),getWidth(),getHeight());
    }

    public void collision(){
        xSpeed = xSpeed*-1;
        ySpeed = ySpeed*-1;
    }

    public void updatePosition(float delta){
        if (getX()+getHeight()>480){
            xSpeed = xSpeed*-1;
        }
        if (getX()<0){
            xSpeed = xSpeed*-1;
        }
        if (getY()+getWidth()>800){
            ySpeed = ySpeed*-1;
        }
        if (getY()<0){
            ySpeed = ySpeed*-1;
        }
        setX(getX()+xSpeed*delta);
        setY(getY()+ySpeed*delta);
        setBounds();
    }

    @Override
    public void act(float delta) {
        updatePosition(delta);
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(sprite,getX(),getY());
    }
}
