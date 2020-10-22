package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Projectiles extends HitBox implements Animate {
    protected ArrayList<Animation<TextureRegion>> Anims;
    protected TextureRegion currentFrame;
    protected String spriteSource;
    protected int range = 100;
    protected int frameCounter = 0;
    public static ArrayList<Projectiles> existingProjectiles = new ArrayList<Projectiles>();


    public Projectiles(Body mainBody, Vector2 spawnVector, float rad, Vector2 dir, String spriteSource) {
        super(mainBody, spawnVector, rad);
        this.body.setLinearVelocity(new Vector2((dir.x - this.body.getPosition().x)*20, (dir.y - this.body.getPosition().y)*20));
        this.spriteSource = spriteSource;
        this.Anims = Animator.createAnim(spriteSource);
        existingProjectiles.add(this);
    }

    static void projectilesUpdate(){
        ArrayList<Projectiles> tobeDisposed = new ArrayList<Projectiles>();
        for (Projectiles temp: existingProjectiles) {
            if (temp.range < 0) {
                tobeDisposed.add(temp); 
            } else {
                temp.range--;
                temp.currentFrame = temp.Anims.get(0).getKeyFrame(myGame.stateTime, true);
                Animator.animateArray.add( new SpriteRender(temp.currentFrame, temp.body.getPosition()));
            }
        }
         if (tobeDisposed != null) {
        for (Projectiles temp: tobeDisposed) {
            existingProjectiles.remove(temp);
        } 
    } 
    }

    @Override
    public void defineSpriteSource() {
        
    }

    @Override
    public void addToAnimator() {

    }


}
