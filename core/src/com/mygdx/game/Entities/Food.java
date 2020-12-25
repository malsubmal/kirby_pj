package com.mygdx.game.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HelperClass.Updatable;

public class Food extends Characters implements Updatable {
    public static ArrayList<Food> existingFoods = new ArrayList<Food>();
    public boolean eaten = false;

    static void FoodSpawn(TiledMap tilemap){
        MapObjects Foods = tilemap.getLayers().get("Foodobject").getObjects();
        for (MapObject object: Foods) {
            Ellipse ellipse = ((EllipseMapObject)object).getEllipse();
            Vector2 center = new Vector2(ellipse.x, ellipse.y);
            Food temp = new Food();
            temp.fixtureDef.isSensor = true;
            temp.body.setTransform(center, 0);
            existingFoods.add(temp);
        }

    } 

    static void FoodUpdate(){
        for (Food temp: existingFoods) {
            if (temp.eaten) {
                temp.body.setActive(false);
                existingFoods.remove(temp);
            }
        }

    }
    

    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineSpriteSource() {
        // TODO Auto-generated method stub

    }

    @Override
    public void Updatable() {
        // TODO Auto-generated method stub

    }
    
}