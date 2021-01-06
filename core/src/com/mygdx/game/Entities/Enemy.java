package com.mygdx.game.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HelperClass.Updatable;
import com.mygdx.game.Sensors.Breakable;
import com.mygdx.game.Sensors.StrikeSensor;
import com.mygdx.game.Stage.GameStage;

public class Enemy extends Characters implements Updatable {

    public int frameCounter = 0;
    public StrikeSensor strikeSensor = new StrikeSensor();
    public int attackWindow = 0;
    public ArrayList<Enemy> existingEnemy = new ArrayList<Enemy>();    

    enum EnemyList {
        UFO, ElectricEnemy
    }

    public Enemy(){
        super();
    }

    @Override
    public void Updatable() {
        if (HP < 0) {
            body.setActive(false);

        } else {
            movement();
        }
    }

     public void EnemySpawn(GameStage gameStage){
        MapObjects UFOs = gameStage.tilemap.getLayers().get("UFOobject").getObjects();
        for (MapObject object: UFOs) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            UFO temp = new UFO(gameStage.world);
            temp.ownerStage = gameStage;
            temp.body.setTransform(center, 0);
            existingEnemy.add(temp);
        }



        MapObjects Electrics = gameStage.tilemap.getLayers().get("ElectricObject").getObjects();
        for (MapObject object: Electrics) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            ElectricEnemy temp = new ElectricEnemy(gameStage.world);
            temp.ownerStage = gameStage;
            temp.body.setTransform(center, 0);
            existingEnemy.add(temp);
        }

        MapObjects BreakableObjects = gameStage.tilemap.getLayers().get("BreakableObject").getObjects();
        for (MapObject object: BreakableObjects) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            Breakable temp = new Breakable(gameStage);
            temp.body.setTransform(center, 0);
            gameStage.existingBreakables.add(temp);
        }


    }

    public void movement(){}

    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineSpriteSource() {
        // TODO Auto-generated method stub

    };

}
