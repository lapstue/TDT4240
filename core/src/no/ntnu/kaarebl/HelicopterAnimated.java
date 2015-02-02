package no.ntnu.kaarebl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * Created by kbl on 1/24/15.
 */

public class HelicopterAnimated extends Actor {
    int xSpeed;
    int ySpeed;
    boolean facingRight;
    Sprite sprite;
    private static final int        FRAME_COLS = 4;
    private static final int        FRAME_ROWS = 1;
    Texture heliSheet;
    TextureRegion[] heliFrames;
    TextureRegion currentFrame;
    Animation heliAnimation;
    float stateTime;
    float xPos;
    float yPos;


    public HelicopterAnimated(float xPos, float yPos, int xSpeed, int ySpeed) {
        heliSheet = new Texture("animation_sheet.png");
        TextureRegion[][] tmp = TextureRegion.split(heliSheet, heliSheet.getWidth()/FRAME_COLS,heliSheet.getHeight()/FRAME_ROWS);
        heliFrames = new TextureRegion[FRAME_COLS*FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                heliFrames[index++] = tmp[i][j];
            }
        }
        //Each frame lasts for 100ms.
        heliAnimation = new Animation(0.010f, heliFrames);
        //Reset statetime counter
        stateTime = 0f;
        //Get the TextureRegion for the current frame based on statetime.
        currentFrame = heliAnimation.getKeyFrame(stateTime, true);
        //Load the current frame as a sprite.
        this.sprite = new Sprite(currentFrame);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.facingRight = true;
        this.xPos = xPos;
        this.yPos = yPos;
        this.sprite.setX(xPos);
        this.sprite.setY(yPos);
        //Setup bounds so that the sprite knows its bounds when checking for collisions
        this.sprite.setBounds(xPos,yPos,sprite.getWidth(),sprite.getHeight());
        this.sprite.flip(true,false);

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

    //Makes sure that the given x will not place the heli outside the screen bounds, and adjusts
    //the given y to enfore that.
    public float sanitizeY(float y){
        if(y+sprite.getHeight()>Constants.screenHeight){
            y = Constants.screenHeight-sprite.getHeight();
        }
        if(y-sprite.getHeight()<0){
            y = 0;
        }
        return y;
    }

    //Sets the direction the sprite is facing based on the direction of travel in the x-axis
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
        //Helicopter is about to leave the right of the screen.
        if (xPos > Constants.screenWidt - sprite.getWidth()) {
            xSpeed = xSpeed * -1;
            sprite.flip(true, false);
        }
        //Helicopter is about to leave the left side of the screen
        if (xPos < 0) {
            xSpeed = xSpeed * -1;
            sprite.flip(true, false);
        }
        //Helicopter is about to leave the top of the screen
        if (yPos + sprite.getHeight() > Constants.screenHeight) {
            ySpeed = ySpeed * -1;
        }
        //Helicopter is about to leave the bottom of the screen
        if (yPos < 0 + sprite.getHeight() / 2) {
            ySpeed = ySpeed * -1;
        }
        //Calculates the new xPos and yPos based on the speed in X and Y direction.
        //Note the hackish solution with the dT-variable. Will probably break horribly when the FPS
        //fluctuates.
        xPos = (xPos + xSpeed * Math.round(dT * 100));
        yPos = (yPos + ySpeed * Math.round(dT * 100));
        sprite.setPosition(getX(), getY());
    }

    @Override
    public void act(float delta) {
        update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //Gets the elapsed time since the last draw call. This is used to update the animation
        stateTime += Gdx.graphics.getDeltaTime();
        //Extract current TextureRegion based on time.
        currentFrame = heliAnimation.getKeyFrame(stateTime, true);
        sprite.setRegion(currentFrame);
        batch.draw(sprite,xPos,yPos);
    }
}
