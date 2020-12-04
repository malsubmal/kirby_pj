package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;


public abstract class Characters{
    protected int HP = setting.HP, DP = setting.DP;
    protected Texture defsprite;
    protected BodyDef bodyDef = new BodyDef();
    protected boolean setActive = false;
    public Body body;
    public FixtureDef fixtureDef;
    public Fixture fixture;
    protected int vcon = 5000;
    protected int density = 500000;
    protected int bodyradius;
    protected Vector2 spawnVector;
    public boolean defeat;
    protected ArrayList<Animation<TextureRegion>> Anims;
    protected TextureRegion currentFrame;
    protected String[] spriteSource;
    protected ArrayList<AnimateWrapper> spriteSourceVar;
    public enum elemental {
        neutral,
        one,
        two,
        three
    };
    protected elemental type;
    protected GameStage ownerStage;

    protected void create(World world){
        this.defineSpawnVector();

        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(spawnVector.x, spawnVector.y);
        body = world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(8);
        fixtureDef.density = density;
        fixture = body.createFixture(fixtureDef);

        defineSpriteSource(); 
        if (spriteSourceVar == null) {
        Anims = Animator.createAnim(this.spriteSource);
        } else {
        Anims = Animator.createAnim(this.spriteSourceVar);
        }
    }

    public abstract void defineSpawnVector();

    public abstract void defineSpriteSource();

    protected void defineSpawnVector(Vector2 spawn) {
        spawnVector = spawn;
    }

}
