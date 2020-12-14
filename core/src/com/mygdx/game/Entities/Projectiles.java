package com.mygdx.game.Entities;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.Stage.GameStage;
import com.mygdx.game.Tools.Animator;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Projectiles extends HitBox {
    public ArrayList<Animation<TextureRegion>> Anims;
    public TextureRegion currentFrame;
    protected String spriteSource;
    public int range = 10;
    protected int frameCounter = 0;
    protected int projectileV = 1;
    protected GameStage ownerStage;
    

    //projectile range

    public Projectiles(Body mainBody, Vector2 spawnVector, float rad, Vector2 dir, String spriteSource) {
        super(mainBody, spawnVector, rad);
        this.ownerStage = ((Characters) mainBody.getUserData()).ownerStage;
        this.body.setLinearVelocity(new Vector2((dir.x - this.body.getPosition().x)*20, (dir.y - this.body.getPosition().y)*20));
        this.spriteSource = spriteSource;
        this.Anims = Animator.createAnim(spriteSource);
        ownerStage.existingProjectiles.add(this);
    }

}
