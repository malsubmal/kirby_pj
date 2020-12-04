package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Characters {

    protected int frameCounter = 0;
    public StrikeSensor strikeSensor = new StrikeSensor();
    public int attackWindow = 0;

    enum EnemyList {
        UFO,
    }

    Enemy(){
        super();
    }

    static void EnemyManager(){

    }

    static void EnemySpawn(GameStage gameStage){
        MapObjects UFOs = gameStage.tilemap.getLayers().get("UFOobject").getObjects();
        for (MapObject object: UFOs) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            UFO temp = new UFO(gameStage.world);
            temp.ownerStage = gameStage;
            temp.body.setTransform(center, 0);
            gameStage.existingEnemy.add(temp);
        }

//        MapObjects Fires = myGame.tilemap.getLayers().get("Fireobject").getObjects();
//        for (MapObject object: Fires) {
//            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
//            Vector2 center = new Vector2(ellipse.x, ellipse.y);
//            FireTypeEnemy temp = new FireTypeEnemy();
//            temp.body.setTransform(center, 0);
//            existingEnemy.add(temp);
//        }
    }

    public void movement(){};

}
