package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.HelperClass.finalCharacter;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.myGame;
import java.lang.Math;

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

        float range = (float) Math.sqrt(Math.pow((myGame.kirby.body.getPosition().x - hitBox.body.getPosition().x),2)+Math.pow((myGame.kirby.body.getPosition().y - hitBox.body.getPosition().y),2));
        System.out.printf("(%.2f,%.2f) : (%.2f,%.2f) range = %.2f, radius = %.2f\n", myGame.kirby.body.getPosition().x, myGame.kirby.body.getPosition().y, hitBox.body.getPosition().x, hitBox.body.getPosition().y, range, hitBox.getRadius());

        if(range <= hitBox.getRadius()*5){
            System.out.println("No trung roi");
        }
        else{
            System.out.println("Khong no trung");
        }
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
          //  System.out.println("set Active Electric");
        //    System.out.println(this.body.getPosition().x + " : " + myGame.kirby.body.getPosition().x);
            //get on the same y-coord as Kirby and shoot projectiles horizontally
            if(this.body.getPosition().x < myGame.kirby.body.getPosition().x){
             //   System.out.println("Right side: " + this.body.getPosition().x);

            }
            else{
           //     System.out.println("Left side " + this.body.getPosition().x);
            }
            this.body.setLinearVelocity(new Vector2((myGame.kirby.body.getPosition().x-this.body.getPosition().x),
                    0));

            //Attack anim
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
            if (attackWindow == 0) {
//                Projectiles uFOProjectiles = new Projectiles(this.body, this.body.getLocalCenter(), 2, myGame.kirby.body.getPosition(), spriteSource[2]);
//                uFOProjectiles.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x-this.body.getPosition().x,0));

                // Attack define
                System.out.println("Attack");
                defineHitBox();
                attackWindow = 200;
            } else {
                attackWindow--;
            }
        }
    }
}
