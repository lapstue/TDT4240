package no.ntnu.kaarebl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class TDT4240Game extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    public Stage stage;

    public void create() {
        Constants.screenWidt = Gdx.graphics.getWidth();
        Constants.screenHeight = Gdx.graphics.getHeight();
        stage = new Stage();
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new Exercise1(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
