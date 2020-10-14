package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends Characters {

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

    }

    static void EnemyUpdate(){
        for (Enemy temp: existingEnemy) {
           ((UFO) temp).movement();
        }
    }
}
