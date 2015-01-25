package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kbl on 1/24/15.
 */
public class Helicopter {
    int xPos;
    int yPos;
    int goalX;
    int goalY;
    int xSpeed;
    int ySpeed;
    int dX;
    int dY;
    Texture texture;
    Sprite sprite;

    public Helicopter(int xPos, int yPos, int xSpeed, int ySpeed){

        texture = new Texture("heli1_east.png");
        sprite = new Sprite(texture);
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        sprite.setPosition(xPos,yPos);
        sprite.setPosition(xPos,yPos);

    }

    public void moveTo(int xNew, int yNew) {
        dX = xPos - xNew;
        dY = yPos - yNew;
        if (dX > 0 && dX != 0) {
            xSpeed = 1;
        }
        if (dX < 0 && dX != 0) {
            xSpeed = -1;
        }
    }

    public void update(Float dT){
        if(xPos > Constants.screenWidt-sprite.getWidth()){
            xSpeed = xSpeed*-1;
            sprite.flip(true,false);
        }
        if(xPos < 0){
            xSpeed = xSpeed*-1;
            sprite.flip(true,false);
        }
        if(yPos + sprite.getHeight() > Constants.screenHeight){
            ySpeed = ySpeed*-1;
        }
        if(yPos < 0 + sprite.getHeight()/2){
            ySpeed = ySpeed*-1;
        }
        xPos = xPos + xSpeed * Math.round(dT*100);
        yPos = yPos + ySpeed * Math.round(dT*100);
        sprite.setPosition(xPos,yPos);
    }

    public void draw(SpriteBatch spriteBatch){
        sprite.draw(spriteBatch);
    }
}
