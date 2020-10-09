package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.maps.objects.RectangleMapObject;


public class myGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Kirby kirby;
	public boolean keypressed = false;
	static public World world;
	private Box2DDebugRenderer debugRenderer;
	static public float stateTime = 0f;
	private TiledMapRenderer tilemaprenderer;
	private int gravity = 500;


	@Override
	public void create () {
	debugRenderer  = new Box2DDebugRenderer();
	world = new World(new Vector2(0, -gravity), true); 
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 800, 480);
	batch = new SpriteBatch();
	kirby = new Kirby();
	kirby.create();
	importTiled("prototype.tmx");
	}
	

	@Override
	public void render () {

      Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	  stateTime += Gdx.graphics.getDeltaTime();
	
	  tilemaprenderer.setView(camera);
	  tilemaprenderer.render();
	  
      // tell the camera to update its matrices.
	  camera.update();
	  
      // tell the SpriteBatch to render in the coordinate system specified by the camera.
      batch.setProjectionMatrix(camera.combined);
	 
	  managerUI();
       
      batch.begin();
      if (!keypressed) {
		  batch.draw(kirby.defsprite, kirby.body.getPosition().x-16, kirby.body.getPosition().y-8);
		  kirby.movement(0);
		
      } else {
    	  batch.draw(kirby.currentFrame, kirby.body.getPosition().x-16, kirby.body.getPosition().y-8);
    	  //camera.translate(kirby.body.getPosition());
      }
	  batch.end();
	  
	  keypressed = false;

	  //update physics world
	  world.step(1/60f, 6, 2);

	  //render box2D object
	  debugRenderer.render(world, camera.combined);
	}
	
	@Override
	public void dispose () {
		
	}

	public void managerUI() {
		if(Gdx.input.isKeyPressed(Keys.RIGHT))	 {kirby.movement(Keys.RIGHT); keypressed = true;}
		if(Gdx.input.isKeyPressed(Keys.LEFT))	  {kirby.movement(Keys.LEFT); keypressed = true;}
		if(Gdx.input.isKeyPressed(Keys.UP))	      {kirby.movement(Keys.UP); keypressed = true;}
		if(Gdx.input.isKeyPressed(Keys.DOWN))	 {kirby.movement(Keys.DOWN); keypressed = true;}
	}

	public void importTiled(String tilemapsource){
		TiledMap tilemap = new TmxMapLoader().load(tilemapsource);
		tilemaprenderer = new OrthogonalTiledMapRenderer(tilemap);
		MapObjects objects = tilemap.getLayers().get("object").getObjects();
    	for (MapObject object: objects) {
		Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
		BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
		Body body = world.createBody(bodyDef);
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(rectangle.width/2, rectangle.height/2);
		Fixture fix = body.createFixture(poly, 1f);
		Vector2 center = new Vector2();
		rectangle.getCenter(center);
		body.setTransform(center, 0);
		
	}
	}

}
