package com.mygdx.game.HelperClass;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpriteRender {
    public TextureRegion frame;
    public Vector2 position;
    public SpriteRender(TextureRegion frame, Vector2 position){
        this.frame = frame;
        this.position = position;
    }
}
