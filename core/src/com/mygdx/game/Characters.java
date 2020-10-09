package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Shape;


public abstract class Characters {
    protected int HP, DP;
    protected Texture defsprite;
    protected float frameD = 0.1f;
    protected BodyDef bodyDef = new BodyDef();
    protected Texture walksheet;
    protected ArrayList<Animation<TextureRegion>> Anims;
    protected StrikeZone strikezone;
    protected boolean setActive = false;
    protected Animation<TextureRegion> animHolder;
    protected TextureRegion[] walkFrames;
    protected TextureRegion[][] tmp;
    protected TextureRegion currentFrame;
    protected int counter = 0;
    protected String sourceAnim;
    public Body body;
    public FixtureDef fixtureDef;
    public Fixture fixture;
    protected int vcon = 1000;
    protected int density = 500000;
    protected int bodyradius;
    protected int spritenumber = 9;
    public enum  spritesManager  { IdleRight, IdleLeft, WalkingRight, WalkingLeft, AttackRight, AttackLeft, Defeat, SuckRight, SuckLeft};
    protected Vector2 spawnVector;


    protected ArrayList<String> spriteSource = new ArrayList<String>();


    protected void create(){
        defineSpawnVector();

        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(spawnVector.x, spawnVector.y);
        body = myGame.world.createBody(bodyDef);
        body.setUserData(this);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(8);
        fixtureDef.density = density;

        fixture = body.createFixture(fixtureDef);

        defineSource();
        createAnim();
    }

    protected void create(Vector2 spawn){
        defineSpawnVector(spawn);
        this.create();
    }

    protected abstract void  defineSource();
    protected abstract void  defineSpawnVector();

    protected void defineSource(String[] sprite) {
        for (int i = 0; i< sprite.length; i++) {
            spriteSource.add(sprite[i]);
        }
    }

    protected void defineSpawnVector(Vector2 spawn) {
        spawnVector = spawn;
    }


    protected void createAnim(){
        Anims = new ArrayList<Animation<TextureRegion>>();
        Animation<TextureRegion> currAnim;
        for (String source : spriteSource) {
            walksheet = new Texture(Gdx.files.internal(source));
            tmp = TextureRegion.split(walksheet,32,32);
            walkFrames = new TextureRegion[walksheet.getWidth()/32];
            int index = 0;
                    for (int j = 0; j < walksheet.getWidth()/32; j++) {
                        walkFrames[index++] = tmp[0][j];
                    }
            currAnim =  new Animation<TextureRegion>(frameD,walkFrames);
            Anims.add(currAnim); 
            counter++;
        }
    }


}
