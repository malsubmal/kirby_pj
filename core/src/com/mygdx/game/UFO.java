package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.Gdx;

public class UFO extends Enemy implements finalCharacter {

    public StrikeSensor thisStrikeSensor = new StrikeSensor();
    public HitBox thisHitBox;
    public int attackWindow = 0;
    private String[] spritesheets = {
        "UFO_default.png",
        "defeat.png",
        "UFOprojectile.png"

    };

    public UFO(Vector2 spawnVector) {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
        this.type = elemental.one;
    }

    public UFO() {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
        this.type = elemental.one;
    }


    public void destroyAnimation() {
    }

    @Override
    public void defineHitBox() {
        thisHitBox = new HitBox(this.body, this.body.getPosition(), 5);
        thisHitBox.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x - this.body.getPosition().x, myGame.kirby.body.getPosition().y - this.body.getPosition().y));
    }



    @Override
    public void defineSpawnVector() {
        defineSpawnVector(new Vector2(2, 200f));
    }

    @Override
    public void defineSpriteSource() {
        this.spriteSource = spritesheets;
    }

    public void movement(){
        if (!setActive) {
            this.body.setLinearVelocity(new Vector2(0, 0));
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
        } else {
            
            this.body.setLinearVelocity(new Vector2(0, 0));
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
            if (attackWindow == 0) {
                Projectiles uFOProjectiles = new Projectiles(this.body, this.body.getLocalCenter(), 2, myGame.kirby.body.getPosition(), spriteSource[2]);
                attackWindow = 50;
            } else {
                attackWindow--;
            }
        }
    }

    @Override
    public void defineStrikeZone() {
        thisStrikeSensor.create(this.body, 40f);
    }

    @Override
    public void addToAnimator() {
        // TODO Auto-generated method stub

    }
    }




