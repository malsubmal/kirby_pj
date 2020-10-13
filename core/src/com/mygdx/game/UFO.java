package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;

public class UFO extends Enemy implements finalCharacter {

    public StrikeSensor thisStrikeSensor = new StrikeSensor();
    public HitBox thisHitBox;
    public boolean setActive;
    private String[] spritesheets = {
        "UFO_default.png",
    };

    public UFO(Vector2 spawnVector) {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
    }

    public UFO() {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
    }


    @Override
    public void defineSource() {
        this.defineSource(spritesheets);
    }


    @Override
    public void defineHitBox() {

    }



    @Override
    public void defineSpawnVector() {
        defineSpawnVector(new Vector2(2, 200f));
    }

    public void movement(){
       
            this.body.setLinearVelocity(new Vector2(0, 0));
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
            System.out.println(this.HP);
            if (HP < 0) {
                this.body.setActive(false);
            }

    }

    @Override
    public void defineStrikeZone() {
        thisStrikeSensor.create(this.body, 20f);
    }
    }




