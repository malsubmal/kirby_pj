package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Vector2;

public class Food extends Characters {
    public static ArrayList<Food> existingFoods = new ArrayList<Food>();
    boolean eaten = false;

    static void FoodSpawn(){
        MapObjects Foods = myGame.tilemap.getLayers().get("Foodobject").getObjects();
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
    
}
