package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Helicopter extends Actor {
    int xSpeed;
    int ySpeed;
    boolean bounceMode;
    boolean facingRight;
    boolean acceptHeliClick;
    Texture texture;
    Sprite sprite;


    public Helicopter(float xPos, float yPos, int xSpeed, int ySpeed, boolean bounceMode, boolean acceptHeliClick) {
        texture = new Texture("heli1_east.png");
        sprite = new Sprite(texture);
        setHeight(texture.getHeight());
        setWidth(texture.getWidth());
        setBounds(xPos, yPos, texture.getWidth(), texture.getHeight());
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.bounceMode = bounceMode;
        this.facingRight = true;
        this.acceptHeliClick = acceptHeliClick;

    }

    //Makes sure that the given x will not place the heli outside the screen bounds, and adjusts
    //the given x to enfore that.
    public float sanitizeX(float x) {
        setDirection(x);
        if(x+sprite.getWidth()>Constants.screenWidth){
            x = Constants.screenWidth -sprite.getWidth();
            return x;
        }
        else{
            return x;
        }
    }

    public float sanitizeY(float y){
        if(y+sprite.getHeight()>Constants.screenHeight){
            y = Constants.screenHeight-sprite.getHeight();
        }
        if(y-sprite.getHeight()<0){
            y = 0;
        }
        return y;
    }

    public void setDirection(float x){
        float dX = x - getX();
        if(dX<1 && facingRight){
            sprite.flip(true,false);
            this.facingRight = false;
        }
        if(dX>1 && !facingRight){
            sprite.flip(true,false);
            this.facingRight = true;
        }
    }

    //Update the gamelogic for the helicopter. Wall collision should probably be separated into its
    //own method.
    public void update(Float dT) {
        if (bounceMode) {
            //Helicopter is about to leave the right of the screen.
            if (getX() > Constants.screenWidth - sprite.getWidth()) {
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