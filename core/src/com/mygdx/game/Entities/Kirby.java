package com.mygdx.game.Entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.myGame;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.Sensors.SuckBox;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.Stage.GameStage;

public abstract class Kirby extends Characters {


    public static HitBox kirbyHitBox;
    public static HitBox kirbySHitBox;
    public static boolean rightDirection = true;
    public static boolean change = false;
    public static boolean fly = false;
    public static boolean fallin = false;
    private float attackv = 20f;
    public static elemental type;
    public static Vector2 spriteOffset = new Vector2(0, 0);
    private static Vector2 position;
    private int attackWindow = 0;
    //sound
    public static boolean death = false;
    private Sound flySound;
    private Sound lowKick;
    protected static Sound currSound;
    // private Sound inhale;

    public Kirby() {

        myGame.kirby = new KirbyDefault();

    }

    public abstract HitBox defineHitBox();

    public static void kirbyUpdate() {
        Kirby.spriteOffset = Vector2.Zero;
        if (myGame.kirby.HP <= 0) {
            death = true;
        }
        if (change) {
            position = myGame.kirby.body.getPosition();
            myGame.kirby.body.setActive(false);
        switch (Kirby.type) {
            case neutral:
            myGame.kirby = new KirbyDefault(position);
            change = false;
            break;
            case fire:
            myGame.kirby = new KirbyAbilityOne(position);
            change = false;
            break;
            case ice:
            myGame.kirby = new KirbyAbilityTwo(position);
            change = false;
            break;
            case three:
            change = false;
            break;
        }
        myGame.kirby.create(((GameStage)	myGame.currentStage).world);
        System.out.println(Kirby.type);
        kirbySHitBox = myGame.kirby.defineHitBox();
        kirbyHitBox.DP = 100;
        currSound.play();
     }
    }

    public Kirby(Boolean check){
         super();
         //need to check the sounds again
        lowKick = Gdx.audio.newSound(
            Gdx.files.internal("lowkick.wav"));
        flySound = Gdx.audio.newSound(
            Gdx.files.internal("fly.wav"));
    }

    public abstract void specialAttack();

    // D attack
    // A suck - special attack
    private void neutralAttack(){
        if (attackWindow<10){
            currSound = lowKick;
        attackWindow+=1; 
    if (rightDirection) {
        this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime()*attackv,0));
        this.currentFrame = this.Anims.get(4).getKeyFrame(myGame.stateTime, true);
        if (kirbyHitBox.body.isActive() == false) {
            kirbyHitBox = new HitBox(myGame.kirby.body, new Vector2(8, 0),  8  , 8);                
            } else {
            kirbyHitBox.body.setTransform(new Vector2(this.body.getPosition().x + 8,this.body.getPosition().y - 8 ), 0);
        }
    } else {
        this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime()*attackv,0));
        this.currentFrame = this.Anims.get(5).getKeyFrame(myGame.stateTime, true);
        if (kirbyHitBox.body.isActive() == false) {
            kirbyHitBox = new HitBox(myGame.kirby.body,new Vector2(-8, 0), 8  , 8);
            } else {
            kirbyHitBox.body.setTransform(new Vector2(this.body.getPosition().x -8,this.body.getPosition().y -8 ), 0);
            } 
        }
    }
    
    }


    public void movement(int keyPressed){
        if (keyPressed != Keys.D) {
            if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
            attackWindow = 0;
        }
        if (keyPressed != Keys.A) {
            if (myGame.kirby instanceof KirbyDefault) {if (KirbyDefault.kirbySuckBox != null) {KirbyDefault.kirbySuckBox.body.setActive(false);}}
            if (myGame.kirby instanceof KirbyAbilityOne) {if (KirbyAbilityOne.kirbyFireHitBox != null) {KirbyAbilityOne.kirbyFireHitBox.body.setActive(false);}}
            
            if (myGame.kirby instanceof KirbyAbilityTwo) {if (KirbyAbilityTwo.kirbyFireHitBox != null) {KirbyAbilityTwo.kirbyFireHitBox.body.setActive(false);}}

        if (myGame.kirby.body.getLinearVelocity().y < 0){fallin = true;
         //   System.out.println("fallin");
            fly = false;
        } else if (myGame.kirby.body.getLinearVelocity().y > 0) {  fly = true;
            fallin = false;
        } else {
            fallin = false;
            fly = false;
        }
        }
        switch (keyPressed) {
            case Keys.UP:
                fly = true;
                fallin = false;
                currSound = flySound;
                this.body.setLinearVelocity(new Vector2(0, vcon*Gdx.graphics.getDeltaTime()));
                if (rightDirection) {
                    this.currentFrame = this.Anims.get(9).getKeyFrame(myGame.stateTime, true);
                } else {
                    this.currentFrame = this.Anims.get(10).getKeyFrame(myGame.stateTime, true);
                }
                break;
            case Keys.RIGHT:
                rightDirection = true;
                if (!fly && !fallin) {
                    this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime(),0));
                    this.currentFrame  = this.Anims.get(2).getKeyFrame(myGame.stateTime, true);
                } else if (fly || fallin) {
                    this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime(),-vcon*Gdx.graphics.getDeltaTime()/2));
                    this.currentFrame  = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);   
                } 
                break;
            case Keys.LEFT:
                rightDirection = false;
                if (!fly && !fallin) {
                    this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime(),0));
                    this.currentFrame  = this.Anims.get(3).getKeyFrame(myGame.stateTime, true);
                } else if (fly || fallin) {
                    this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime(),-vcon*Gdx.graphics.getDeltaTime()/2));
                    this.currentFrame  = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);
                }
                break;
            case Keys.DOWN:
                fly = false;
                fallin = true;
                this.body.setLinearVelocity(new Vector2(0, -vcon*Gdx.graphics.getDeltaTime()));
                if (rightDirection) {
                    this.currentFrame = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);
                    } else {
                    this.currentFrame = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);
                    }
                break;
            case Keys.A:
                myGame.kirby.specialAttack();
                break;
            case Keys.D:
                
                this.body.setLinearVelocity(new Vector2(0,0));
           
                    neutralAttack();
               
                break;
            case Keys.X:
                if (Kirby.type == elemental.neutral) {

                } else {
                Kirby.type = elemental.neutral;
                Kirby.change = true;
                }
                this.currentFrame = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
                break;
            case 0:
            //need to put this in constructor
            //need to adjust instantiation
            //need a loading timeout
            //need to optimize here
                if (kirbyHitBox == null) {
                    kirbyHitBox = new HitBox(this.body, new Vector2(8, -8),  8  , 8);
                    kirbyHitBox.body.setActive(false);
                }

                if (kirbySHitBox == null){
                    kirbySHitBox = myGame.kirby.defineHitBox();
                    kirbyHitBox.DP = 100;
                }
               
                if (rightDirection && (fallin || fly)) {
                    this.currentFrame = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);
                } else if (!rightDirection  && (fallin || fly)) {
                    this.currentFrame = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);
                }  else if (!rightDirection) {
                    this.currentFrame = this.Anims.get(1).getKeyFrame(myGame.stateTime, true);
                } else if (rightDirection) {
                    this.currentFrame = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
                }
                this.body.setLinearVelocity(new Vector2(0,0)); 
                break;
            default:
                break;
        }
    }
}
