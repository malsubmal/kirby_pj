package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Animator {
    protected static ArrayList<Animation<TextureRegion>> sharedAnims;
    protected String sourceAnim;
    protected int spritenumber = 9;
    protected static ArrayList<SpriteRender> animateArray;
    public enum  spritesManager  { IdleRight, IdleLeft, WalkingRight, WalkingLeft, AttackRight, AttackLeft, Defeat, SuckRight, SuckLeft};
    protected static String[] sharedSpriteSource = {
        "defeat.png",
    };

    public static void createSharedAnimate(){
        sharedAnims = createAnim(sharedSpriteSource);
    }

    public static void Animate(){
        createSharedAnimate();
        animateArray = new ArrayList<SpriteRender>();
    }

    public static ArrayList<Animation<TextureRegion>> createAnim(String[] spriteSource){

        Texture walksheet;
        ArrayList<Animation<TextureRegion>> extraAnims = new ArrayList<Animation<TextureRegion>>();
        Animation<TextureRegion> currAnim;
        TextureRegion[][] tmp;
        TextureRegion[] walkFrames;
        float frameD = 0.1f;

        for (String source : spriteSource) {
            walksheet = new Texture(Gdx.files.internal(source));
            tmp = TextureRegion.split(walksheet,32,32);
            walkFrames = new TextureRegion[walksheet.getWidth()/32];
            int index = 0;
                    for (int j = 0; j < walksheet.getWidth()/32; j++) {
                        walkFrames[index++] = tmp[0][j];
                    }
            currAnim =  new Animation<TextureRegion>(frameD,walkFrames);
            extraAnims.add(currAnim); 
        }

        return extraAnims;
    }

    public static ArrayList<Animation<TextureRegion>> createAnim(String spriteSource){

        Texture walksheet;
        ArrayList<Animation<TextureRegion>> extraAnims = new ArrayList<Animation<TextureRegion>>();
        Animation<TextureRegion> currAnim;
        TextureRegion[][] tmp;
        TextureRegion[] walkFrames;
        float frameD = 0.3f;

            walksheet = new Texture(Gdx.files.internal(spriteSource));
            tmp = TextureRegion.split(walksheet,32,32);
            walkFrames = new TextureRegion[walksheet.getWidth()/32];
            int index = 0;
                    for (int j = 0; j < walksheet.getWidth()/32; j++) {
                        walkFrames[index++] = tmp[0][j];
                    }
            currAnim =  new Animation<TextureRegion>(frameD,walkFrames);
            extraAnims.add(currAnim); 
        

        return extraAnims;
    }

}