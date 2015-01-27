package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by kbl on 1/24/15.
 */

public class Helicopter extends Actor {
    int xSpeed;
    int ySpeed;
    boolean bounceMode;
    Texture texture;
    Sprite sprite;

    public Helicopter(float xPos, float yPos, int xSpeed, int ySpeed, boolean bounceMode) {
        texture = new Texture("heli1_east.png");
        sprite = new Sprite(texture);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.bounceMode = bounceMode;
        setHeight(texture.getHeight());
        setWidth(texture.getWidth());
        setBounds(xPos, yPos, texture.getWidth(), texture.getHeight());
    }

    //Update the gamelogic for the helicopter. Wall collision should probably be separated into its
    //own method.
    public void update(Float dT) {
        if (bounceMode) {
            //Helicopter is about to leave the right of the screen.
            if (getX() > Constants.screenWidt - sprite.getWidth()) {
                xSpeed = xSpeed * -1;
                sprite.flip(true, false);
            }
            //Helicopter is about to leave the left side of the screen
            if (getX() < 0) {
                xSpeed = xSpeed * -1;
                sprite.flip(true, false);
            }
            //Helicopter is about to leave the top of the screen
            if (getY() + sprite.getHeight() > Constants.screenHeight) {
                ySpeed = ySpeed * -1;
            }
            //Helicopter is about to leave the bottom of the screen
            if (getY() < 0 + sprite.getHeight() / 2) {
                ySpeed = ySpeed * -1;
            }
            //Calculates the new xPos and yPos based on the speed in X and Y direction.
            //Note the hackish solution with the dT-variable. Will probably break horribly when the FPS
            //fluctuates.
            setX(getX() + xSpeed * Math.round(dT * 100));
            setY(getY() + ySpeed * Math.round(dT * 100));
            sprite.setPosition(getX(), getY());
        } else {
            this.act(dT);
        }
    }

    @Override
    public void act(float delta) {
        if (bounceMode) {
            update(delta);
        } else {
            super.act(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY());
    }
}