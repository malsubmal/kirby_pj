package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Kirby {
	public Texture defsprite;
    public Rectangle bod;

    public void create(){
    bod = new Rectangle();
    bod.x = 800 / 2 - 64 / 2; // center the bucket horizontally
    bod.y = 20; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
    bod.width = 64;
    bod.height = 64;
    defsprite = new Texture(Gdx.files.internal("Sprite-0001.jpeg"));
    }
    
    void movement(){
        if(Gdx.input.isKeyPressed(Keys.LEFT)) bod.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) bod.x += 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.UP)) { 
            bod.y += 200 * Gdx.graphics.getDeltaTime();}
    }
}
