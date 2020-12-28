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
    private Sound flySound;
    private Sound lowKick;
    protected static Sound currSound;
    //private Sound inhale;

    public Kirby(){
        
        myGame.kirby = new KirbyDefault();
        
    }

    public static void kirbyUpdate(){
        Kirby.spriteOffset = Vector2.Zero;
        if (change) {
        switch (Kirby.type) {
            case neutral:
            change = false;
            break;
            case one:
            position = myGame.kirby.body.getPosition();
            myGame.kirby.body.setActive(false);
            myGame.kirby = new KirbyAbilityOne(position);
            myGame.kirby.create(((GameStage)	myGame.currentStage).world);
            change = false;
            break;
            case two:
            change = false;
            break;
            case three:
            change = false;
            break;
        }
        System.out.println(Kirby.type);
        
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

        if (myGame.kirby.body.getLinearVelocity().y < 0){fallin = true;
            //System.out.println("fallin");
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
            case 0:
            //need to put this in constructor
            //need to adjust instantiation
            //need a loading timeout
                if (kirbyHitBox == null) {
                    kirbyHitBox = new HitBox(this.body, new Vector2(8, -8),  8  , 8);
                    kirbyHitBox.body.setActive(false);
                }
                if (KirbyDefault.kirbySuckBox == null) {
                    KirbyDefault.kirbySuckBox = new SuckBox(this.body, Vector2.Zero,  8  , 8);
                    KirbyDefault.kirbySuckBox.body.setActive(false);
                }
                if (KirbyAbilityOne.kirbyFireHitBox == null) {
                    KirbyAbilityOne.kirbyFireHitBox = new HitBox(this.body, Vector2.Zero,  8  , 8);
                    KirbyAbilityOne.kirbyFireHitBox.body.setActive(false);
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
