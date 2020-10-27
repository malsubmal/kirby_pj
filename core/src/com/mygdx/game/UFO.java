package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;


public class UFO extends Enemy implements finalCharacter, projectileType {

    public StrikeSensor strikeSensor = new StrikeSensor();
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
        this.type = elemental.neutral;
    }

    public UFO() {
        super();
        create(spawnVector);
        defineStrikeZone();
        movement();
        this.type = elemental.neutral;
    }


    public void destroyAnimation() {
    }

    @Override
    public void defineHitBox() {
        /* hitBox = new HitBox(this.body, this.body.getPosition(), 5);
        hitBox.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x - this.body.getPosition().x, myGame.kirby.body.getPosition().y - this.body.getPosition().y)); */
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
        strikeSensor.create(this.body, 40f);
    }

    }




