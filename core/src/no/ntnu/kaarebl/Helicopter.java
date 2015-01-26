package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
/**
 * Created by kbl on 1/24/15.
 */
public class Helicopter extends Actor {
    int xPos;
    int yPos;
    int xGoal;
    int yGoal;
    int xSpeed;
    int ySpeed;
    int dX;
    int dY;
    boolean bounceMode;
    Texture texture;
    Sprite sprite;

    public Helicopter(int xPos, int yPos, int xSpeed, int ySpeed, boolean bounceMode){

        texture = new Texture("heli1_east.png");
        sprite = new Sprite(texture);
        this.xPos = xPos;
        this.yPos = yPos;
        this.xSpeed = xSpeed;
        this.ySpeed =  ySpeed;
        this.bounceMode = bounceMode;
        setHeight(texture.getHeight());
        setWidth(texture.getWidth());
        sprite.setRotation(0);
    }

    //Move the helicopter to a given X and Y coordinate.
    public void moveTo(int xGoal, int yGoal) {
        this.bounceMode = false;
        System.out.println("Got Touch event!");
        MoveToAction action = new MoveToAction();
        dX = xGoal - xPos;
        dY = yGoal - yPos;
        float duration = (float) Math.sqrt(dX*dX+dY*dY);
        action.setPosition(xGoal,yGoal);
        action.setDuration(duration);
        this.addAction(action);
    }

    //Update the gamelogic for the helicopter. Wall collision should probably be separated into its
    //own method.
    public void update(Float dT){
        if(bounceMode){
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
            if(yPos < 0 + sprite.getHeight()/2 ){
                ySpeed = ySpeed*-1;
            }
            //Calculates the new xPos and yPos based on the speed in X and Y direction.
            //Note the hackish solution with the dT-variable. Will probably break horribly when the FPS
            //fluctuates.
            xPos = xPos + xSpeed * Math.round(dT*100);
            yPos = yPos + ySpeed * Math.round(dT*100);
            sprite.setPosition(xPos,yPos);
        }
        else{
            this.act(dT);
        }
    }

    @Override
    public void act(float delta){
        if(bounceMode){
            update(delta);
        }
        else{
            super.act(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(sprite,xPos,yPos);
    }
}
