package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.maps.objects.RectangleMapObject;

public class GameStage extends Stage {
    //HUD
    //Map
    //Sound
    //private HUD stageHUD;
    private String mapSource = "prototype.tmx";
    //private String soundSource;
    public Animator levelAnimator;
    public World world;
    private int gravity = 500;
    TiledMap tilemap;
    public OrthogonalTiledMapRenderer tilemaprenderer;
    public ArrayList<Projectiles> existingProjectiles = new ArrayList<Projectiles>();
    public static ArrayList<Enemy> existingEnemy = new ArrayList<Enemy>();

    public GameStage(){        
        levelAnimator = new Animator();
        world = new World(new Vector2(0, -gravity), true);
        myGame.kirby.create(world);
        Listener listener = new Listener();
        world.setContactListener(listener); 
        importTiled(mapSource);
        Enemy.EnemySpawn(this);
        Animator.Animate();
        //kirby.body.setTransform(kirbystarter, 0);
	    
    }
    
    public GameStage(String source){  
        this.mapSource = source;      
        world = new World(new Vector2(0, -gravity), true);
        myGame.kirby.create(world);
        Listener listener = new Listener();
        world.setContactListener(listener); 
        importTiled(mapSource);
        Enemy.EnemySpawn(this);
        Animator.Animate();
        //kirby.body.setTransform(kirbystarter, 0);
	    
    }

    public void importTiled(String tilemapsource){
		tilemap = new TmxMapLoader().load(tilemapsource);
        tilemaprenderer = new OrthogonalTiledMapRenderer(tilemap);
		//MapObjects kirbystart = tilemap.getLayers().get("start").getObjects();
		//for (MapObject pos: kirbystart) {
		//	Ellipse ellipse = ((EllipseMapObject) pos).getEllipse();
        //    kirbystarter = new Vector2(ellipse.x, ellipse.y);
		//}
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
    
    public void entitiesUpdate(){
        projectilesUpdate();
        EnemyUpdate();
    }

    private void EnemyUpdate(){
        ArrayList<Enemy> tobeDisposed = new ArrayList<Enemy>();
        for (Enemy temp: existingEnemy) {
            if (temp.HP < 0) {
                //add to death animation array
                temp.frameCounter++;
                temp.body.setActive(false);
                Animator.sharedAnims.get(0).setFrameDuration(0.1f);
                temp.currentFrame  = Animator.sharedAnims.get(0).getKeyFrame(myGame.stateTime, true);
                if (temp.frameCounter > 25) {
                tobeDisposed.add(temp); }
            } else {
            temp.movement();
             }
             temp.ownerStage.levelAnimator.animateArray.add( new SpriteRender(temp.currentFrame, temp.body.getPosition()));
        }
         if (tobeDisposed != null) {
        for (Enemy temp: tobeDisposed) {
            existingEnemy.remove(temp);
        } 
    } 
    }
    
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
}
