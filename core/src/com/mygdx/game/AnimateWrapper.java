package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class AnimateWrapper {
	public String source;
	public int width;
	public int height;
	public Vector2 offset = Vector2.Zero;

	
	public AnimateWrapper(String source, int width, int height) {
		this.source = source;
		this.height = height;
		this.width = width;
		if (this.width == 0) this.width = 32;
		if (this.height == 0) this.height = 32;
	}
	public AnimateWrapper(String source, int measure) {
		this.source = source;
		this.height = measure;
		this.width = measure;
	}
}
