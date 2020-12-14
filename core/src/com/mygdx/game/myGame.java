package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Entities.Kirby;
import com.mygdx.game.Entities.KirbyDefault;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.Stage.GameStage;
import com.mygdx.game.UI.HUD;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;



public class myGame extends ApplicationAdapter {

	private static SpriteBatch batch;
	private static OrthographicCamera camera;
	static public Kirby kirby;

	static public float stateTime = 0f;
	public static WrapperStage currentStage;
	private Stage titleStage;
	private Stage loadStage;
	private Stage pauseStage;
	private Stage configStage;
	private HUD thisHUD;
	private Vector2 kirbystarter;

	@Override
	public void create() {
		Sound bgSong = Gdx.audio.newSound(Gdx.files.internal("spec.mp3"));
		bgSong.loop();
		// new stage for title screen
		// new stage for loading screen
		// new stage for pause screen
		// new stage for option/config screen
		// stage = new Stage(new ScreenViewport());
		kirby = new KirbyDefault();
		currentStage = new GameStage();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 200);
		camera.zoom -= 0.00004f;

		batch = new SpriteBatch();
		// testStage = new currentStage<>(new GameStage());
		camera.position.set(kirby.body.getPosition().x / 2, kirby.body.getPosition().y, 0);
	}

	public static OrthographicCamera getCamera() {
		return camera;
	}

	public static SpriteBatch getBatch() {
		return batch;
	}
	
	//public currentStage<GameStage> testStage;
	@Override
	public void render () {

      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	  stateTime += Gdx.graphics.getDeltaTime();

	  float delta = Gdx.graphics.getDeltaTime();
	  currentStage.act(delta);

      // tell the camera to update its matrices.
	  camera.update();
	  
      // tell the SpriteBatch to render in the coordinate system specified by the camera.
	  batch.setProjectionMatrix(camera.combined);

	  currentStage.StageDraw();

	}
	
	@Override
	public void dispose () {
		
	}

	
}
