package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;


public class Kirby extends Characters{


    private String[] spritesheets = {"Sprite-0001.png", "Sprite-0002.png","Sprite-0010.png", "Sprite-0011.png", "Sprite-0003.png", "Sprite-0004.png", "Sprite-defeat.png", "Sprite-suckR.png", "Sprite-suckL.png"};

    void movement(int keyPressed){
        switch (keyPressed) {
            case Keys.RIGHT:
                this.body.setLinearVelocity(new Vector2(vcon*Gdx.graphics.getDeltaTime(),0));
                this.currentFrame  = this.Anims.get(2).getKeyFrame(myGame.stateTime, true);
                break;
            case Keys.LEFT:
                this.body.setLinearVelocity(new Vector2(-vcon*Gdx.graphics.getDeltaTime(),0));
                this.currentFrame = this.Anims.get(3).getKeyFrame(myGame.stateTime, true);
                break;
            case Keys.UP:
                this.body.setLinearVelocity(new Vector2(0, vcon*Gdx.graphics.getDeltaTime()));
                this.currentFrame = this.Anims.get(1).getKeyFrame(myGame.stateTime, true);
                break;
            case Keys.DOWN:
                this.body.setLinearVelocity(new Vector2(0, -vcon*Gdx.graphics.getDeltaTime()));
                this.currentFrame = this.Anims.get(1).getKeyFrame(myGame.stateTime, true);
                break;
            case 0:
                this.body.setLinearVelocity(new Vector2(0,0));
            default:
                break;
        }
        
    }

    @Override
    void defineSource() {
        this.defineSource(spritesheets);

    }

    

    
}
