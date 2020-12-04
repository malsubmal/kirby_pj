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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.math.Vector2;



public class myGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	static public Kirby kirby;
	public boolean keypressed = false;

	private Box2DDebugRenderer debugRenderer;
	static public float stateTime = 0f;

	public static Stage currentStage;
	private Stage titleStage;
	private Stage loadStage;
	private Stage pauseStage;
	private Stage configStage;
	private HUD thisHUD;
	private Vector2 kirbystarter;
	static public boolean receiveUI = true;

	@Override
	public void create () {
		Sound bgSong = Gdx.audio.newSound(
			Gdx.files.internal("spec.mp3"));
			bgSong.loop();
	//new stage for title screen
	//new stage for loading screen
	//new stage for pause screen
	//new stage for option/config screen
	//stage = new Stage(new ScreenViewport());
	kirby = new KirbyDefault();
	currentStage = new GameStage();
	debugRenderer  = new Box2DDebugRenderer();

	camera = new OrthographicCamera();
	camera.setToOrtho(false, 500, 200);
	camera.zoom -= 0.00004f;

	batch = new SpriteBatch();
	//testStage = new currentStage<>(new GameStage());
	camera.position.set(kirby.body.getPosition().x/2,kirby.body.getPosition().y,0 );
	}
	
	//public currentStage<GameStage> testStage;
	@Override
	public void render () {

      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	  stateTime += Gdx.graphics.getDeltaTime();

	  float delta = Gdx.graphics.getDeltaTime();
	  currentStage.act(delta);

	  //testStage.stage.tilemaprenderer.setView(camera);

      // tell the camera to update its matrices.
	  camera.update();
	  
      // tell the SpriteBatch to render in the coordinate system specified by the camera.
	  batch.setProjectionMatrix(camera.combined);

	  

	  if (currentStage instanceof GameStage) {
			GameStage tempStage = (GameStage) currentStage;
			tempStage.tilemaprenderer.setView(camera);
			tempStage.tilemaprenderer.render();
			
			if (receiveUI){ managerUI();}
			batch.begin();
			if (!keypressed) {		  kirby.movement(0);      }
			//improve shaking when Kirby's stuck
			camera.position.set(kirby.body.getPosition().x,kirby.body.getPosition().y,0 );
			
			batch.draw(kirby.currentFrame
					, kirby.body.getPosition().x-16-kirby.spriteOffset.x
					,kirby.body.getPosition().y-8-kirby.spriteOffset.y);
			for (SpriteRender temp : tempStage.levelAnimator.animateArray) {
				batch.draw(temp.frame, temp.position.x-16, temp.position.y-8);
			}	
			batch.end();
			//update physics world
			tempStage.world.step(1/60f, 6, 2);
			tempStage.levelAnimator.animateArray.clear();
			Kirby.kirbyUpdate();
			tempStage.entitiesUpdate();

			//render box2D object
			debugRenderer.render(tempStage.world, camera.combined);
		}
    
		
	  
		//currentStage.draw();

	}
	
	@Override
	public void dispose () {
		
	}

	public void managerUI() {
		keypressed = false;
		//movement
		if(Gdx.input.isKeyPressed(Keys.RIGHT))	 {kirby.movement(Keys.RIGHT); keypressed = true;}
		if(Gdx.input.isKeyPressed(Keys.LEFT))	  {kirby.movement(Keys.LEFT); keypressed = true;}
		if(Gdx.input.isKeyPressed(Keys.UP))	      {kirby.movement(Keys.UP); keypressed = true;}
		if(Gdx.input.isKeyPressed(Keys.DOWN))	 {kirby.movement(Keys.DOWN); keypressed = true;}
		//suck
		if (Gdx.input.isKeyPressed(Keys.A))		{kirby.movement(Keys.A); keypressed = true;}
		//attack
		if (Gdx.input.isKeyPressed(Keys.D))		{kirby.movement(Keys.D); keypressed = true;}		
	}

}
