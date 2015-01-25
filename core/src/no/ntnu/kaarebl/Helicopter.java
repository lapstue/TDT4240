package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by kbl on 1/24/15.
 */
public class Helicopter {
    int xPos;
    int yPos;
    int xGoal;
    int yGoal;
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
        xGoal = 0;
        yGoal = 0;

    }

    //Move the helicopter to a given X and Y coordinate.
    public void moveTo(int xNew, int yNew) {
        dX = xNew - xPos;
        dY = yNew - yPos;
        xGoal = xNew;
        yGoal = yNew;
        if (dX > 0) {
            xSpeed = 1;
        }
        if (dX < 0) {
            xSpeed = -1;
        }
        if (dX == 0){
            xSpeed = 0;
        }
        if (dY > 0){
            ySpeed = 1;
        }
        if(dY < 0){
            ySpeed = -1;
        }
        if (dY == 0){
            ySpeed = 0;
        }
    }

    //Update the gamelogic for the helicopter. Wall collision should probably be separated into its
    //own method.
    public void update(Float dT){
        moveTo(xGoal,yGoal);
        //Helicopter is about to leave the right of the screen.
        if(xPos > Constants.screenWidt-sprite.getWidth()){
            xSpeed = xSpeed*-1;
            sprite.flip(true,false);
        }
        //Helicopter is about to leave the left side of the screen
        if(xPos < 0){
            xSpeed = xSpeed*-1;
            sprite.flip(true,false);
        }
        //Helicopter is about to leave the top of the screen
        if(yPos + sprite.getHeight() > Constants.screenHeight){
            ySpeed = ySpeed*-1;
        }
        //Helicopter is about to leave the bottom of the screen
        if(yPos < 0 + sprite.getHeight()/2){
            ySpeed = ySpeed*-1;
        }
        //Calculates the new xPos and yPos based on the speed in X and Y direction.
        //Note the hackish solution with the dT-variable. Will probably break horribly when the FPS
        //fluctuates.
        xPos = xPos + xSpeed * Math.round(dT*100);
        yPos = yPos + ySpeed * Math.round(dT*100);
        sprite.setPosition(xPos,yPos);
    }

    public void draw(SpriteBatch spriteBatch){
        sprite.draw(spriteBatch);
    }
}
