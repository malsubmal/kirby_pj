package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Characters {

    protected int frameCounter = 0;
    public StrikeSensor strikeSensor = new StrikeSensor();
    public int attackWindow = 0;

    public static ArrayList<Enemy> existingEnemy = new ArrayList<Enemy>();
    //public ArrayList<Vector2> UFOplacement = new ArrayList<Vector2>();
    enum EnemyList {
        UFO,
    }

    Enemy(){
        super();
    }

    static void EnemyManager(){

    }

    static void EnemySpawn(){

        MapObjects UFOs = myGame.tilemap.getLayers().get("UFOobject").getObjects();
        for (MapObject object: UFOs) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            UFO temp = new UFO();
            temp.body.setTransform(center, 0);
            existingEnemy.add(temp);
        }

        MapObjects Fires = myGame.tilemap.getLayers().get("Fireobject").getObjects();
        for (MapObject object: Fires) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            FireTypeEnemy temp = new FireTypeEnemy();
            temp.body.setTransform(center, 0);
            existingEnemy.add(temp);
        }
    }

    public void movement(){};

    static void EnemyUpdate(){
        ArrayList<Enemy> tobeDisposed = new ArrayList<Enemy>();
        for (Enemy temp: existingEnemy) {
            if (temp.HP < 0) {
                temp.frameCounter++;
                temp.body.setActive(false);
                Animator.sharedAnims.get(0).setFrameDuration(0.1f);
                temp.currentFrame  = Animator.sharedAnims.get(0).getKeyFrame(myGame.stateTime, true);
                if (temp.frameCounter > 25) {
                tobeDisposed.add(temp); }
            } else {
            temp.movement();
             }
            Animator.animateArray.add( new SpriteRender(temp.currentFrame, temp.body.getPosition()));
        }
         if (tobeDisposed != null) {
        for (Enemy temp: tobeDisposed) {
            existingEnemy.remove(temp);
        } 
    } 
    }

}
