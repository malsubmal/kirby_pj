package com.mygdx.game.Stage;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Entities.*;
import com.mygdx.game.myGame;
import com.mygdx.game.HelperClass.SpriteRender;
import com.mygdx.game.HelperClass.Updatable;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.Sensors.Breakable;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.Tools.Animator;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;

public class GameStage extends WrapperStage {
    // HUD
    // Map
    // Sound
    // private HUD stageHUD;
    private String mapSource = "prototype.tmx";
    // private String soundSource;
    public Animator levelAnimator;
    public World world;
    private int gravity = 500;
    public TiledMap tilemap;
    public OrthogonalTiledMapRenderer tilemaprenderer;
    public ArrayList<Projectiles> existingProjectiles = new ArrayList<Projectiles>();
    public ArrayList<Breakable> existingBreakables = new ArrayList<Breakable>();
    private Box2DDebugRenderer debugRenderer;
    private boolean receiveUI = false;
    public boolean keypressed = false;
    ArrayList<Updatable> updateArray = new ArrayList<Updatable>();
    private Enemy enemyHolder = new Enemy();
    private Vector2 kirbystarter;
    private Image bgImage;
    public Breakable breakableTest;

    public GameStage() {
        //create ArrayList 
        enemyHolder = new Enemy();
        updateArray.add(enemyHolder);
        debugRenderer = new Box2DDebugRenderer();
        levelAnimator = new Animator();
        
        world = new World(new Vector2(0, -gravity), true);
        myGame.kirby.create(world);
        Listener listener = new Listener();
        world.setContactListener(listener);
        importTiled(mapSource);
        enemyHolder.EnemySpawn(this);
        Animator.Animate();
        // kirby.body.setTransform(kirbystarter, 0);

        loaded = true;

    }

    public GameStage(String source) {
        
        myGame.bgSong = Gdx.audio.newSound(Gdx.files.internal("bg.mp3"));
        enemyHolder = new Enemy();
        updateArray.add(enemyHolder);
        this.mapSource = source;
        debugRenderer = new Box2DDebugRenderer();
        levelAnimator = new Animator();
        world = new World(new Vector2(0, -gravity), true);
        myGame.kirby.create(world);

        Listener listener = new Listener();
        world.setContactListener(listener);
        importTiled(mapSource);
        enemyHolder.EnemySpawn(this);
        Animator.Animate();
        myGame.kirby.body.setTransform(kirbystarter, 0);
        //test Breakable
        breakableTest = new Breakable(this);
        breakableTest.body.setTransform(new Vector2(myGame.kirby.body.getPosition().x + 20,myGame.kirby.body.getPosition().y), 0);

        loaded = true;
        receiveUI = true;

    }

    public void importTiled(String tilemapsource) {
        tilemap = new TmxMapLoader().load(tilemapsource);
        tilemaprenderer = new OrthogonalTiledMapRenderer(tilemap);
        //MapProperties levelImage = tilemap.getLayers().get("bg").getProperties();
        //System.out.println(levelImage.getKeys().get);
		MapObjects kirbystart = tilemap.getLayers().get("start").getObjects();
		for (MapObject pos: kirbystart) {
			Ellipse ellipse = ((EllipseMapObject) pos).getEllipse();
            kirbystarter = new Vector2(ellipse.x, ellipse.y);
		}
		MapObjects objects = tilemap.getLayers().get("object").getObjects();
    	for (MapObject object: objects) {
            if (object instanceof PolygonMapObject) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
			//Rectangle rectangle = ((RectangleMapObject)object).getRectangle(); - done
			BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.KinematicBody;
            Body body = world.createBody(bodyDef);
            //polygon.setScale(scaleX, scaleY);
            PolygonShape poly = new PolygonShape();
            poly.set(polygon.getTransformedVertices());
			Fixture fix = body.createFixture(poly, 1f);
            body.setTransform(new Vector2(polygon.getOriginX(),polygon.getOriginY()), 0);
            } 
            else if (object instanceof RectangleMapObject) {
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
    
    public void entitiesUpdate(){
        projectilesUpdate();
        breakableUpdate();
        EnemyUpdate();
    }

    private void EnemyUpdate(){
        ArrayList<Enemy> tobeDisposed = new ArrayList<Enemy>();
        for (Enemy temp: enemyHolder.existingEnemy) {
            if (temp.HP < 0) {
                //separate into death array
                temp.frameCounter++;
                temp.body.setActive(false);
                if (temp.hitBox != null) {
                    temp.hitBox.body.setActive(false);
                }
                Animator.sharedAnims.get(0).setFrameDuration(0.1f);
                temp.currentFrame  = Animator.sharedAnims.get(0).getKeyFrame(myGame.stateTime, true);
                if (temp.frameCounter > 25) {
                tobeDisposed.add(temp); }
            } else {
            temp.movement();
            }
            
            temp.ownerStage.levelAnimator.animateArray.add( new SpriteRender(temp.currentFrame, 
            new Vector2(temp.body.getPosition().x - (temp.spriteOffset.x/4),
            temp.body.getPosition().y - temp.spriteOffset.y/4)
            ));
            temp.spriteOffset = Vector2.Zero;
            
        }
         if (tobeDisposed != null) {
        for (Enemy temp: tobeDisposed) {
            enemyHolder.existingEnemy.remove(temp);
        } 
    }

    }
    
    //need to re-vamp here
    public void projectilesUpdate(){
        ArrayList<Projectiles> tobeDisposed = new ArrayList<Projectiles>();
        for (Projectiles temp: existingProjectiles) {
            if (temp.range < 0) {
                tobeDisposed.add(temp); 
                temp.body.setActive(false);
            } else {
                temp.range--;
                temp.currentFrame = temp.Anims.get(0).getKeyFrame(myGame.stateTime, true);
                levelAnimator.animateArray.add( new SpriteRender(temp.currentFrame, temp.body.getPosition()));
            }
        }
         if (tobeDisposed != null) {
        for (Projectiles temp: tobeDisposed) {
            existingProjectiles.remove(temp);
        } 
    } 
    }

    public void breakableUpdate(){
        ArrayList<Breakable> tobeDisposed = new ArrayList<Breakable>();
        for (Breakable temp: existingBreakables) {
            if (temp.HP <= 20) {
                temp.body.setActive(false);
                tobeDisposed.add(temp);
            } else {
                levelAnimator.animateArray.add( new SpriteRender(temp.Anim.getKeyFrames()[4-temp.HP/10],
                new Vector2 (temp.body.getPosition().x-16, temp.body.getPosition().y-16-8)));
            }
        }
        if (tobeDisposed != null) {
            for (Breakable temp: tobeDisposed) {
                existingProjectiles.remove(temp);
            } 
        } 
    }

   

    @Override
	public void StageDraw() {
        
        if (myGame.kirby.death == false){
            tilemaprenderer.setView(myGame.getCamera());
            tilemaprenderer.render();
            if (receiveUI){ manageUI();}
            myGame.getBatch().begin();
            if ( receiveUI && !keypressed) {		  myGame.kirby.movement(0);      }
            //bgImage.setPosition(myGame.kirby.body.getPosition().x,myGame.kirby.body.getPosition().y );
            //bgImage.draw(myGame.getBatch(), Gdx.graphics.getDeltaTime());
            //improve shaking when Kirby's stuck
            myGame.getCamera().position.set(myGame.kirby.body.getPosition().x,myGame.kirby.body.getPosition().y,0 );
            
           

            for (SpriteRender temp : levelAnimator.animateArray) {
                myGame.getBatch().draw(temp.frame,
                 temp.position.x-16,
                 temp.position.y-8);
            }	
            myGame.getBatch().draw(myGame.kirby.currentFrame
            , myGame.kirby.body.getPosition().x-16-myGame.kirby.spriteOffset.x
            ,myGame.kirby.body.getPosition().y-8-myGame.kirby.spriteOffset.y);
            myGame.getBatch().end();
            //update physics world
            world.step(1/60f, 6, 2);
            levelAnimator.animateArray.clear();
            Kirby.kirbyUpdate();
            entitiesUpdate();
    
            //render box2D object
            debugRenderer.render(world, myGame.getCamera().combined);
        } else {
            
            System.out.println("death");
        
           myGame.getBatch().begin();
            myGame.getBatch().draw(myGame.kirby.Anims.get(6).getKeyFrame(myGame.stateTime, true)
            , myGame.kirby.body.getPosition().x-16-myGame.kirby.spriteOffset.x
            ,myGame.kirby.body.getPosition().y-8-myGame.kirby.spriteOffset.y);
            myGame.getBatch().end(); 
            //    myGame.bufferStage = new OpenStage();
            
        }
        }

	@Override
	public void manageUI() {
        // TODO Auto-generated method stub
            keypressed = false;
            //movement
            if(Gdx.input.isKeyPressed(Keys.RIGHT))	 {myGame.kirby.movement(Keys.RIGHT); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.LEFT))	  {myGame.kirby.movement(Keys.LEFT); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.UP))	      {myGame.kirby.movement(Keys.UP); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.DOWN))	 {myGame.kirby.movement(Keys.DOWN); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.K))	 {myGame.kirby.movement(Keys.RIGHT); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.H))	  {myGame.kirby.movement(Keys.LEFT); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.U))	      {myGame.kirby.movement(Keys.UP); keypressed = true;}
            if(Gdx.input.isKeyPressed(Keys.J))	 {myGame.kirby.movement(Keys.DOWN); keypressed = true;}
            if (Gdx.input.isKeyPressed(Keys.A))		{myGame.kirby.movement(Keys.A); keypressed = true;}
            if (Gdx.input.isKeyPressed(Keys.D))		{myGame.kirby.movement(Keys.D); keypressed = true;}	
            if (Gdx.input.isKeyPressed(Keys.X))		{myGame.kirby.movement(Keys.X); keypressed = true;}		
       
	}

    public Vector2 getKirbystarter() {
        return kirbystarter;
    }
}

