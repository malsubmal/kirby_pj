package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HUD extends Actor {
	//unused
	TextureRegion region;
	hpHUD kirbyHP = new hpHUD();
    public HUD(String source) {
		region = new TextureRegion(new Texture(Gdx.files.internal(source)));
        setBounds(region.getRegionX(), region.getRegionY(),
		region.getRegionWidth(), region.getRegionHeight());
    }
    @Override
	public void draw (Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
}
