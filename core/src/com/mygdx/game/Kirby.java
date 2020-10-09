package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;


public class Kirby extends Characters {

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

    void movement(int keyPressed){
        switch (keyPressed) {
            case Keys.UP:
                fly = true;
                this.body.setLinearVelocity(new Vector2(0, vcon*Gdx.graphics.getDeltaTime()));
                if (rightDirection) {
                    this.currentFrame = this.Anims.get(9).getKeyFrame(myGame.stateTime, true);
                } else {
                    this.currentFrame = this.Anims.get(10).getKeyFrame(myGame.stateTime, true);
                }
                break;
            case Keys.RIGHT:
                rightDirection = true;
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
                this.body.setLinearVelocity(new Vector2(0, -vcon*Gdx.graphics.getDeltaTime()));
                if (rightDirection) {
                    this.currentFrame = this.Anims.get(11).getKeyFrame(myGame.stateTime, true);
                    } else {
                    this.currentFrame = this.Anims.get(12).getKeyFrame(myGame.stateTime, true);
                    }
                break;
            case Keys.A:
                this.body.setLinearVelocity(new Vector2(0,0));
                if (rightDirection) {
                this.currentFrame = this.Anims.get(7).getKeyFrame(myGame.stateTime, true);
                } else {
                this.currentFrame = this.Anims.get(8).getKeyFrame(myGame.stateTime, true);
                }
                break;
            case 0:
                this.body.setLinearVelocity(new Vector2(0,0));
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


    Kirby() {
        super();
        this.spawnVector = new Vector2(1, 200f);
    }

    Kirby(Vector2 spawnVector2){
        super();
        this.spawnVector = spawnVector2;
    }

    @Override
    protected void defineSpawnVector() {
        // TODO Auto-generated method stub

    }
    

    
}
