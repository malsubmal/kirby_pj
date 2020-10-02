package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import java.util.ArrayList;
import com.badlogic.gdx.Input.Keys;

public class myGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Kirby kirby;
	private boolean keypressed = false;
	static public float stateTime = 0f;


	@Override
	public void create () {
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 800, 480);
	batch = new SpriteBatch();
	kirby = new Kirby();
	kirby.create();

	}

	@Override
	public void render () {
	  // clear the screen with a dark blue color. The
      // arguments to glClearColor are the red, green
      // blue and alpha component in the range [0,1]
      // of the color to be used to clear the screen.
      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	  stateTime += Gdx.graphics.getDeltaTime();

      // tell the camera to update its matrices.
      camera.update();

      // tell the SpriteBatch to render in the
      // coordinate system specified by the camera.
      batch.setProjectionMatrix(camera.combined);

	  if(Gdx.input.isKeyPressed(Keys.RIGHT))	 {kirby.movement(Keys.RIGHT); keypressed = true;}
	  if(Gdx.input.isKeyPressed(Keys.LEFT))	  {kirby.movement(Keys.LEFT); keypressed = true;}
	  //if(Gdx.input.isKeyPressed(Keys.A))	  kirby.movement(Keys.A);
	  //if(Gdx.input.isKeyPressed(Keys.D))	  kirby.movement(Keys.D);
	
      // begin a new batch and draw the bucket and
      // all drops
      batch.begin();
      if (!keypressed) {
    	  batch.draw(kirby.defsprite, kirby.bod.x, kirby.bod.y);
      } else {
    	  batch.draw(kirby.currentFrame, kirby.bod.x, kirby.bod.y);
      }
      batch.end();
      keypressed = false;
	}
	
	@Override
	public void dispose () {
		
	}
}
