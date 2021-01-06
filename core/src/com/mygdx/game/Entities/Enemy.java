package com.mygdx.game.Entities;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.HelperClass.Updatable;
import com.mygdx.game.Sensors.Breakable;
import com.mygdx.game.Sensors.StrikeSensor;
import com.mygdx.game.Stage.GameStage;

public class Enemy extends Characters implements Updatable {

    public int frameCounter = 0;
    public StrikeSensor strikeSensor = new StrikeSensor();
    public int attackWindow = 0;
    public ArrayList<Enemy> existingEnemy = new ArrayList<Enemy>();
    public GameStage currStage;

    class EnemyGroup<T extends Enemy> {
        Class<T> enemy;
        String layerName;

        EnemyGroup(Class<T> enemy, String layer) {
            this.enemy = enemy;
            this.layerName = layer;
        }

    }

    public Enemy() {
        super();
        this.HP = 10;
    }

    public Enemy(GameStage gameStage) {
        super();
        this.currStage = gameStage;
    }

    @Override
    public void Updatable() {
        if (HP < 0) {
            body.setActive(false);

        } else {
            movement();
        }
    }

    public void EnemySpawn(GameStage gameStage) {
        this.currStage = gameStage;

        individualSpawn(new EnemyGroup<UFO>(UFO.class, "UFOobject"));
        individualSpawn(new EnemyGroup<ElectricEnemy>(ElectricEnemy.class, "ElectricObject"));

        MapObjects BreakableObjects = gameStage.tilemap.getLayers().get("BreakableObject").getObjects();
        for (MapObject object : BreakableObjects) {
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            Breakable temp = new Breakable(gameStage);
            temp.body.setTransform(center, 0);
            gameStage.existingBreakables.add(temp);
        }

    }

    public void movement() {
    }

    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub

    }

    public void individualSpawn(EnemyGroup enemyGroup) {
        MapObjects temps = currStage.tilemap.getLayers().get(enemyGroup.layerName).getObjects();
        for (MapObject object : temps) {
            Ellipse ellipse = ((EllipseMapObject) object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            Enemy temp;
            try {
                temp =  ((Class<? extends Enemy>)enemyGroup.enemy).getConstructor(World.class).newInstance(currStage.world);
                temp.ownerStage = currStage;
                temp.body.setTransform(center, 0);
                existingEnemy.add(temp);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
        }
    }

    @Override
    public void defineSpriteSource() {
        // TODO Auto-generated method stub

    };

}
