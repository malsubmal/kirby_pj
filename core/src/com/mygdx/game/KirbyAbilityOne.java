package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class KirbyAbilityOne extends Kirby implements finalCharacter {

    private String[] spritesheets = {
        "fireKrestR.png", 
        "fireKrestL.png",
        "fireKwalkR.png", 
        "fireKwalkL.png", 
        //attackright
        "Sprite-0003.png", 
        //attackleft
        "Sprite-0004.png", 
        //defeat
        "Sprite-defeat.png", 
        //fireballRight
        "Sprite-suckR.png", 
        //fireballL
        "Sprite-suckL.png",
        "fireKflyR.png",
        "fireKflyL.png",
        "Sprite-fallR.png",
        "Sprite-fallL.png",
    };


    //private float duration;

    public void destroy(){
        this.currentFrame = this.Anims.get(6).getKeyFrame(myGame.stateTime, true);
    }


    public KirbyAbilityOne(Vector2 pos) {
        super(true);       
        this.spawnVector = pos;
    }
  

    @Override
    public void defineHitBox() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineStrikeZone() {
        // TODO Auto-generated method stub

    }

    @Override
    public void defineSpriteSource() {
        this.spriteSource = spritesheets;
    }

    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub
    }

}
