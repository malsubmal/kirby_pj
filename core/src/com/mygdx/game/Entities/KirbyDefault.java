package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.myGame;
import com.mygdx.game.HelperClass.finalCharacter;
import com.mygdx.game.Sensors.SuckBox;

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


    //private float duration;
    

    public static SuckBox kirbySuckBox;

    public void destroy(){
        this.currentFrame = this.Anims.get(6).getKeyFrame(myGame.stateTime, true);
    }

    public KirbyDefault() {
        super(true);       
        this.spawnVector = new Vector2(1, 200f);
        Kirby.type = elemental.neutral;
    }

    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineHitBox() {
        
    }
    

    @Override
    public void defineSpriteSource() {
        this.spriteSource = spritesheets;
    }

    @Override
    public void defineStrikeZone() {
        // TODO Auto-generated method stub

    }

  

    public void specialAttack(){
        this.body.setLinearVelocity(new Vector2(0,0));
        System.out.print("in suck");
                if (Kirby.rightDirection) {
                   this.currentFrame = this.Anims.get(7).getKeyFrame(myGame.stateTime, true);
                if (kirbySuckBox.body.isActive() == false) {
                    kirbySuckBox = new SuckBox(myGame.kirby.body, new Vector2(8, 32),  16  , 8);                
                    } else {
                    kirbySuckBox.body.setTransform(new Vector2(this.body.getPosition().x + 8,this.body.getPosition().y ), 0);
                    }
                } else {
                this.currentFrame = this.Anims.get(8).getKeyFrame(myGame.stateTime, true);
                if (kirbySuckBox.body.isActive() == false) {
                    kirbySuckBox = new SuckBox(myGame.kirby.body,new Vector2(-8, 32), 16  , 8);
                    } else {
                        kirbySuckBox.body.setTransform(new Vector2(this.body.getPosition().x -8,this.body.getPosition().y ), 0);
                    } 
                }
    }

    
}