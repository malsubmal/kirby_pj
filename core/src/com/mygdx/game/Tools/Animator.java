package com.mygdx.game.Tools;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.HelperClass.AnimateWrapper;
import com.mygdx.game.HelperClass.SpriteRender;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Animator {
    public static ArrayList<Animation<TextureRegion>> sharedAnims;
    protected String sourceAnim;
    protected int spritenumber = 9;
    public ArrayList<SpriteRender> animateArray = new ArrayList<SpriteRender>();
    public enum  spritesManager  { IdleRight, IdleLeft, WalkingRight, WalkingLeft, AttackRight, AttackLeft, Defeat, SuckRight, SuckLeft};
    protected static String[] sharedSpriteSource = {
        "defeat.png",
    };

    public static void createSharedAnimate(){
        sharedAnims = createAnim(sharedSpriteSource);
    }

    public static void Animate(){
        createSharedAnimate(); 
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
                        //walkFrames[index++].setRegion(walkFrames[index++], -16, 16, 32,32);
                    }
            currAnim =  new Animation<TextureRegion>(frameD,walkFrames);
            extraAnims.add(currAnim); 
        }

        return extraAnims;
    }

    public static ArrayList<Animation<TextureRegion>> createAnim(ArrayList<AnimateWrapper> spriteSource){

        Texture walksheet;
        ArrayList<Animation<TextureRegion>> extraAnims = new ArrayList<Animation<TextureRegion>>();
        Animation<TextureRegion> currAnim;
        TextureRegion[][] tmp;
        TextureRegion[] walkFrames;
        float frameD = 0.1f;

        for (AnimateWrapper wrapper : spriteSource) {
            walksheet = new Texture(Gdx.files.internal(wrapper.source));
            tmp = TextureRegion.split(walksheet,wrapper.width,wrapper.height);
            walkFrames = new TextureRegion[walksheet.getWidth()/wrapper.width];
            int index = 0;
                    for (int j = 0; j < walksheet.getWidth()/wrapper.width; j++) {
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
