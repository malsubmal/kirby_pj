package com.mygdx.game;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;


public abstract class Kirby extends Characters {
    private ArrayList<Kirby> kArrayList;
    protected boolean aug = false;

    /* public void create(){
        //default
        kArrayList.add(new KirbyDefault());
        //elemental
        kArrayList.add(new KirbyAbilityOne());
        kArrayList.add(new KirbyAbilityTwo());
        kArrayList.add(new KirbyAbilityThree());
    } */

    public Kirby(){
        if (!aug) {
        myGame.kirby = new KirbyDefault();
        }
        
    }

    public Kirby(Boolean check){
        super();
    }

    public void movement(int key){
        if (myGame.kirby instanceof KirbyDefault) {
            myGame.kirby.movement(key);
        }
    }



}
