package com.mygdx.game;


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
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public abstract class Characters {
    protected int HP = 50, DP = 100;
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
    public enum elemental {
        neutral,
        one,
        two,
        three
    };
    protected elemental type;

    protected void create(){
        this.defineSpawnVector();

        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(spawnVector.x, spawnVector.y);
        body = myGame.world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(8);
        fixtureDef.density = density;
        fixture = body.createFixture(fixtureDef);

        defineSpriteSource(); 
        Anims = Animator.createAnim(this.spriteSource);
    }

    public abstract void defineSpawnVector();

    public abstract void defineSpriteSource();

    protected void create(Vector2 spawn) {
        defineSpawnVector(spawn);
        this.create();
    }

    protected void defineSpawnVector(Vector2 spawn) {
        spawnVector = spawn;
    }

}
