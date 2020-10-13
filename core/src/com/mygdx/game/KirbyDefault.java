package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class KirbyDefault extends Kirby implements finalCharacter{
 //arrow keys to move
    //a key to suck

    //need to customize frameD time
    //change suck Sprite to non-looping
    //flesh out the suck function in general 

    private String[] spritesheets = {
        "Sprite-0001.png", 
        "Sprite-0002.png",
        "Sprite-0010.png", 
        "Sprite-0011.png", 
        "Sprite-0003.png", 
        "Sprite-0004.png", 
        "Sprite-defeat.png", 
        "Sprite-suckR.png", 
        "Sprite-suckL.png",
        "Sprite-flyR.png",
        "Sprite-flyL.png",
        "Sprite-fallR.png",
        "Sprite-fallL.png",
    };

    private boolean rightDirection = true;
    private boolean fly = false;
    private float duration;
    private float attackv = 20f;
    public static HitBox kirbyHitBox;

    public void movement(int keyPressed){
        switch (keyPressed) {
            case Keys.UP:
                fly = true;
                 if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
                this.body.setLinearVelocity(new Vector2(0, vcon*Gdx.graphics.getDeltaTime()));
                if (rightDirection) {
                    this.currentFrame = this.Anims.get(9).getKeyFrame(myGame.stateTime, true);
                } else {
                    this.currentFrame = this.Anims.get(10).getKeyFrame(myGame.stateTime, true);
                }
                break;
            case Keys.RIGHT:
                rightDirection = true;
                if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
                if (!fly) {
                    this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime(),0));
                    this.currentFrame  = this.Anims.get(2).getKeyFrame(myGame.stateTime, true);
                    } else {
                    this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime(),-vcon*Gdx.graphics.getDeltaTime()/2));
                    this.currentFrame  = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);   
                }
                break;
            case Keys.LEFT:
                rightDirection = false;
                if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
                if (!fly) {
                    this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime(),0));
                    this.currentFrame  = this.Anims.get(3).getKeyFrame(myGame.stateTime, true);
                } else {
                    this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime(),-vcon*Gdx.graphics.getDeltaTime()/2));
                    this.currentFrame  = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);   
                    }
                break;
            case Keys.DOWN:
                fly = false;
                if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
                this.body.setLinearVelocity(new Vector2(0, -vcon*Gdx.graphics.getDeltaTime()));
                if (rightDirection) {
                    this.currentFrame = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);
                    } else {
                    this.currentFrame = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);
                    }
                break;
            case Keys.A:
                this.body.setLinearVelocity(new Vector2(0,0));
                if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
                if (rightDirection) {
                this.currentFrame = this.Anims.get(7).getKeyFrame(myGame.stateTime, true);
                } else {
                this.currentFrame = this.Anims.get(8).getKeyFrame(myGame.stateTime, true);
                }
                break;
            case Keys.D:
                //set duration limit
                //set movement limit
                //set DP limit
                this.body.setLinearVelocity(new Vector2(0,0));
                if (rightDirection) {
                this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime()*attackv,0));
                this.currentFrame = this.Anims.get(4).getKeyFrame(myGame.stateTime, true);
                if (kirbyHitBox.body.isActive() == false) {
                kirbyHitBox = new HitBox(this.body, new Vector2(8, -8),  8  , 8);                
                } else {
                kirbyHitBox.body.setTransform(new Vector2(this.body.getPosition().x + 8,this.body.getPosition().y - 8 ), 0);
                }
                //new Vector2(this.body.getPosition().x + 8, this.body.getPosition().y -8),
                } else {
                this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime()*attackv,0));
                this.currentFrame = this.Anims.get(5).getKeyFrame(myGame.stateTime, true);
                if (kirbyHitBox.body.isActive() == false) {
                kirbyHitBox = new HitBox(this.body,new Vector2(-8, -8), 8  , 8);
                } else {
                kirbyHitBox.body.setTransform(new Vector2(this.body.getPosition().x -8,this.body.getPosition().y -8 ), 0);
                } 
                }
                break;
            case 0:
                if (kirbyHitBox == null) {
                kirbyHitBox = new HitBox(this.body, new Vector2(8, -8),  8  , 8);
                kirbyHitBox.body.setActive(false);
                }
                this.body.setLinearVelocity(new Vector2(0,0));
                if (kirbyHitBox != null) {kirbyHitBox.body.setActive(false);}
                if (rightDirection && !fly) {
                    this.currentFrame = this.Anims.get(0).getKeyFrame(myGame.stateTime, true);
                } else if (!rightDirection && !fly) {
                    this.currentFrame = this.Anims.get(1).getKeyFrame(myGame.stateTime, true);
                } else if (rightDirection && fly) {
                    this.currentFrame = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);
                    //stop sprite when reaching the ground and change fly bool
                } else if (!rightDirection && fly) {
                    this.currentFrame = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);
                    //stop sprite when reaching the ground and change fly bool
                }
                
            default:
                break;
        }
        
    }

    @Override
    public void defineSource() {
        this.defineSource(spritesheets);

    }


    public KirbyDefault() {
        super(true);        
        this.spawnVector = new Vector2(1, 200f);
    }

    /* Kirby(Vector2 spawnVector2){
        super();        
        this.spawnVector = spawnVector2;
    } */

    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineHitBox() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineStrikeZone() {
        // TODO Auto-generated method stub

    }
    
}
