package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by kbl on 1/24/15.
 */

public class Helicopter extends Actor {
    int xSpeed;
    int ySpeed;
    boolean bounceMode;
    boolean facingRight;
    Texture texture;
    Sprite sprite;
    static final int FRAME_COLS = 4;
    Texture heliSheet;
    TextureRegion[] heliFrames;


    public Helicopter(float xPos, float yPos, int xSpeed, int ySpeed, boolean bounceMode, boolean animate) {
        if(animate){
            heliSheet = new Texture("animation_sheet.png"));
            TextureRegion[] tmp = TextureRegion.split(heliSheet, heliSheet.getWidth()/FRAME_COLS);
            heliFrames = new TextureRegion[FRAME_COLS];
            int index = 0;
            for (int j = 0; j < FRAME_COLS; j++) {
                heliFrames[index++]=[j];
            }
        }
        else{
            texture = new Texture("heli1_east.png");
        }
        sprite = new Sprite(texture);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.bounceMode = bounceMode;
        this.facingRight = true;
        setHeight(texture.getHeight());
        setWidth(texture.getWidth());
        setBounds(xPos, yPos, texture.getWidth(), texture.getHeight());
    }

    //Makes sure that the given x will not place the heli outside the screen bounds, and adjusts
    //the given x to enfore that.
    public float sanitizeX(float x) {
        setDirection(x);
        if(x+sprite.getWidth()>Constants.screenWidt){
            x = Constants.screenWidt-sprite.getWidth();
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