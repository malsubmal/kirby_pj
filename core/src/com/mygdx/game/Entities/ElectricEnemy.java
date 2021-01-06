package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.HelperClass.AnimateWrapper;
import com.mygdx.game.HelperClass.finalCharacter;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.myGame;
import java.lang.Math;
import java.util.ArrayList;

public class ElectricEnemy extends Enemy implements finalCharacter {

    private String[] spritesheets = {
        //rest right
        "electricEnemyRestL.png",
        //rest left
        "electricEnemyRestR.png",
        //attack
        "electricEnemyAttack.png"

    };

    public int[] height =  {0,0,64};
    public int[] width = {0,0,64};


    public ElectricEnemy(World world) {
        super();
        create(world);
        defineStrikeZone();
        movement();
        this.type = elemental.fire;
    }


    @Override
    public void defineSpawnVector() {
        defineSpawnVector(new Vector2(2, 200f));
    }

    @Override
    public void defineHitBox() {
        hitBox = new HitBox(this.body, this.body.getLocalCenter(), 10f);
        hitBox.setActive(false);

        /* float range = (float) Math.sqrt(Math.pow((myGame.kirby.body.getPosition().x - hitBox.body.getPosition().x),2)+Math.pow((myGame.kirby.body.getPosition().y - hitBox.body.getPosition().y),2));
        System.out.printf("(%.2f,%.2f) : (%.2f,%.2f) range = %.2f, radius = %.2f\n", myGame.kirby.body.getPosition().x, myGame.kirby.body.getPosition().y, hitBox.body.getPosition().x, hitBox.body.getPosition().y, range, hitBox.getRadius());

        if(range <= hitBox.getRadius()*5){
            System.out.println("No trung roi");
        }
        else{
            System.out.println("Khong no trung");
        } */
        //hitBox.body.setLinearVelocity(new Vector2(myGame.kirby.body.getPosition().x - this.body.getPosition().x, 0));

    }

    @Override
    public void defineStrikeZone() {
        strikeSensor.create(this.body, 40f);
    }

    @Override
    public void defineSpriteSource() {
        this.spriteSourceVar = new ArrayList<AnimateWrapper>();
        for (int i = 0; i < this.spritesheets.length; i++) {
            this.spriteSourceVar.add(new AnimateWrapper(spritesheets[i],width[i],height[i]));
        }
        
    }

    public void movement(){
        if (this.hitBox == null) {defineHitBox();}
        if (!setActive) {
            this.body.setLinearVelocity(new Vector2(0, 0));
            //rest anim
            this.currentFrame  = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
        } else {
          //  System.out.println("set Active Electric");
        //    System.out.println(this.body.getPosition().x + " : " + myGame.kirby.body.getPosition().x);
            //get on the same y-coord as Kirby and shoot projectiles horizontally
            //if(this.body.getPosition().x < myGame.kirby.body.getPosition().x){
             //   System.out.println("Right side: " + this.body.getPosition().x);

            //}
            //else{
           //     System.out.println("Left side " + this.body.getPosition().x);
            //}
            this.body.setLinearVelocity(new Vector2((myGame.kirby.body.getPosition().x-this.body.getPosition().x),
                    0));

            //Attack anim
      /*       int orderAnim = 2;
            this.currentFrame  = this.Anims.get(orderAnim).getKeyFrame(myGame.stateTime, true);
            if ((height[orderAnim] != 0) || (width[orderAnim] != 0)) {
                this.spriteOffset = new Vector2(width[orderAnim],height[orderAnim]);
            }  */

            this.currentFrame = getFrame(2);
            //khung thời gian đánh là 100 giây, trong đó 40 giây là HitBox hoạt động, 60 là không hoạt động
            if (attackWindow == 0) {
                // Attack define
                System.out.println("Attack");
                this.hitBox.setActive(true);
                this.hitBox.body.setTransform(this.body.getPosition(),0);
                attackWindow = 100;
            } else if ( attackWindow >= 60) {                
                this.hitBox.body.setTransform(this.body.getPosition(),0);
                attackWindow--;
            } else {
                this.hitBox.setActive(false);
                attackWindow--;
            }
        }
    }
}
