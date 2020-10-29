package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class FireTypeEnemy extends Enemy implements finalCharacter {

    
    private String[] spritesheets = {
        "EnemyDemo.png",
    };

    public FireTypeEnemy(Vector2 spawnVector) {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
        this.type = elemental.one;
    }

    public FireTypeEnemy() {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
        this.type = elemental.one;
    }


    public void destroyAnimation() {
    }

    @Override
    public void defineHitBox() {}



    @Override
    public void defineSpawnVector() {
        defineSpawnVector(new Vector2(2, 200f));
    }

    @Override
    public void defineSpriteSource() {
        this.spriteSource = spritesheets;
    }

    public void movement(){
    this.body.setLinearVelocity(new Vector2(0, 0));
    this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
    }

    @Override
    public void defineStrikeZone() {}

}