package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.HelperClass.finalCharacter;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.myGame;

public class ElectricEnemy extends Enemy implements finalCharacter {


    private String[] spriteSheets = {"EnemyDemo.png"};

    public ElectricEnemy(World world) {
        super();
        create(world);
        defineStrikeZone();
        movement();
        this.type = elemental.electric;
    }


    @Override
    public void defineSpawnVector() {
        defineSpawnVector(new Vector2(2, 200f));
    }
    @Override
    public void defineHitBox() {
        HitBox hitBox = new HitBox(this.body, this.body.getLocalCenter(), 10);
        //hitBox.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x - this.body.getPosition().x, 0));

    }

    @Override
    public void defineStrikeZone() {
        strikeSensor.create(this.body, 40f);
    }

    @Override
    public void defineSpriteSource() {
        this.spriteSource = spriteSheets;
    }

    public void movement(){
        if (!setActive) {
            this.body.setLinearVelocity(new Vector2(0, 0));
            //rest anim
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
        } else {
            System.out.println("set Active Electric");
            //get on the same y-coord as Kirby and shoot projectiles horizontally
            this.body.setLinearVelocity(new Vector2((myGame.kirby.body.getPosition().x-this.body.getPosition().x)/2,
                    this.body.getPosition().y));
            //Attack anim
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
            if (attackWindow == 0) {
//                Projectiles uFOProjectiles = new Projectiles(this.body, this.body.getLocalCenter(), 2, myGame.kirby.body.getPosition(), spriteSource[2]);
//                uFOProjectiles.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x-this.body.getPosition().x,0));
                // Attack define
                defineHitBox();
                attackWindow = 100;
            } else {
                attackWindow--;
            }
        }
    }
}
