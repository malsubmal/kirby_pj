package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.myGame;
import com.mygdx.game.HelperClass.finalCharacter;


public class UFO extends Enemy implements finalCharacter {
    
    private String[] spritesheets = {
        "UFO_default.png",
        "defeat.png",
        "UFOprojectile.png"
    };


    public UFO(World world) {
        super();
        create(world);
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
            //rest anim
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
        } else {
            //get on the same y-coord as Kirby and shoot projectiles horizontally            
            this.body.setLinearVelocity(new Vector2((myGame.kirby.body.getPosition().x-this.body.getPosition().x)/2,
                                                     myGame.kirby.body.getPosition().y-this.body.getPosition().y));
            //Attack anim
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
            if (attackWindow == 0) {
                Projectiles uFOProjectiles = new Projectiles(this.body, this.body.getLocalCenter(), 2, myGame.kirby.body.getPosition(), spriteSource[2]);
                uFOProjectiles.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x-this.body.getPosition().x,0));
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

    @Override
    public void Updatable() {
        // TODO Auto-generated method stub
        
    }


    }




