package no.ntnu.kaarebl;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kbl on 1/26/15.
 */
public class SpriteButton extends Actor {

    private Texture texture;
    private Sprite sprite;
    private float xPos;
    private float yPos;
    
    public SpriteButton(String resourceName){
        this.texture = new Texture(resourceName);
        this.sprite = new Sprite(texture);
    }

    public void draw(Batch batch, float parentAlpha){
        batch.draw(sprite,getX(),getY());
    }

}
