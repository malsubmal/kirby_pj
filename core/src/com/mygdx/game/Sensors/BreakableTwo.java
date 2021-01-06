package com.mygdx.game.Sensors;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.game.Stage.GameStage;
import com.mygdx.game.Tools.Animator;

public class BreakableTwo extends Breakable {

    public Body body;
    public int HP = 30;
    int height = 64;
    int width = 64;
    public Animation<TextureRegion> Anim;
    public TextureRegion currentFrame;
    String spriteSource = "iceBlock.png";

    // bodyDef.BodyType = BodyType.DynamicBody;
    public BreakableTwo(GameStage gameStage) {
        super(gameStage);
        this.Anim = Animator.createAnim(spriteSource, 64 , 64);
        this.currentFrame = Anim.getKeyFrame(0);
        gameStage.existingBreakables.add(this);
        this.body.setUserData(this);
    }
    
}
