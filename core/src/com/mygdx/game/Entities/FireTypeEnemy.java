package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.myGame;
import com.mygdx.game.HelperClass.finalCharacter;

public class FireTypeEnemy extends Enemy implements finalCharacter {

    
    private String[] spritesheets = {
        "EnemyDemo.png",
    };

    public FireTypeEnemy(Vector2 spawnVector) {
        super();
        defineStrikeZone();
        movement();
        this.type = elemental.fire;
    }

    public FireTypeEnemy() {
        super();
        defineStrikeZone();
        movement();
        this.type = elemental.fire;
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

    @Override
    public void Updatable() {
        // TODO Auto-generated method stub

    }

}
