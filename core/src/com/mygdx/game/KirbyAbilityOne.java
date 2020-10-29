package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

public class KirbyAbilityOne extends Kirby implements finalCharacter {

    public static HitBox kirbyFireHitBox;
    public static boolean spacing = false;

    private String[] spritesheets = {
        "fireKrestR.png", //0
        "fireKrestL.png", //1
        "fireKwalkR.png", //2
        "fireKwalkL.png",  //3
        //attackright
        "fireKattackR.png", //4
        //attackleft
        "fireKattackL.png", //5
        //defeat
        "Sprite-defeat.png", //6
        //fireballRight
        "fireKSattackR.png", //7
        //fireballL
        "fireKSattackL.png", //8
        "fireKflyR.png", //9
        "fireKflyL.png", //10
        "Sprite-fallR.png", //11
        "Sprite-fallL.png", //12
    };

    private int[] height =  {0,0,0,0,0,0,0,0,0,0,0,0,0};
    private int[] width = {0,0,0,0,0,0,0,64,64,0,0,0,0};

    //private float duration;

    public void destroy(){
        this.currentFrame = this.Anims.get(6).getKeyFrame(myGame.stateTime, true);
    }

    @Override
    public void defineSpriteSource() {
      
        this.spriteSourceVar = new ArrayList<AnimateWrapper>();
        for (int i = 0; i < this.spritesheets.length; i++) {
            this.spriteSourceVar.add(new AnimateWrapper(spritesheets[i],width[i],height[i]));
        }
        
      
    }

    public KirbyAbilityOne(Vector2 pos) {
        super(true);    
        this.spawnVector = pos;
    }
  

    @Override
    public void defineHitBox() {
 

    }

    @Override
    public void defineStrikeZone() {
        // TODO Auto-generated method stub

    }


    @Override
    public void defineSpawnVector() {
        // TODO Auto-generated method stub
    }

    @Override
    public void specialAttack() {
        this.body.setLinearVelocity(new Vector2(0,0));
        System.out.print("spit fire");
                if (Kirby.rightDirection) {
                   this.currentFrame = this.Anims.get(7).getKeyFrame(myGame.stateTime, true);
                if (kirbyFireHitBox.body.isActive() == false) {
                    kirbyFireHitBox = new HitBox(myGame.kirby.body, new Vector2(8, 32),  16  , 16);                
                    } else {
                        kirbyFireHitBox.body.setTransform(new Vector2(this.body.getPosition().x + 32,this.body.getPosition().y ), 0);
                    }
                } else {
                spacing = true;
                this.currentFrame = this.Anims.get(8).getKeyFrame(myGame.stateTime, true);
                if (kirbyFireHitBox.body.isActive() == false) {
                    kirbyFireHitBox = new HitBox(myGame.kirby.body,new Vector2(-8, 32), 16  , 16);
                    } else {
                        kirbyFireHitBox.body.setTransform(new Vector2(this.body.getPosition().x -32,this.body.getPosition().y ), 0);
                    } 
                }
    }

}
