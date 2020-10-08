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


public class Characters {
    private int HP, DP;
    protected Texture defsprite;
    protected float frameD = 0.1f;
    protected BodyDef bodyDef = new BodyDef();
    protected Texture walksheet;
    protected ArrayList<Animation<TextureRegion>> Anims;
    protected Animation<TextureRegion> animHolder;
    protected TextureRegion[] walkFrames;
    protected TextureRegion[][] tmp;
    protected TextureRegion currentFrame;
    protected int movementVector = 30;
    protected int counter = 0;
    protected String sourceAnim;
    public Body body;
    protected int vcon = 1000;
    protected int density = 500000;

    public void create(){
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(1, 200f);
        body = myGame.world.createBody(bodyDef);
        body.setUserData(this);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(8);
        fixtureDef.density = density;

        Fixture fixture = body.createFixture(fixtureDef);


    //sprite
    defsprite = new Texture(Gdx.files.internal("Sprite-0001.gif"));
    createAnim();
    
    }

    public void createAnim(){
        Anims = new ArrayList<Animation<TextureRegion>>();
        Anims.add(animHolder);
        Anims.add(animHolder);
        for (Animation<TextureRegion> currAnim : Anims) {
            switch (counter) {
                case 0:
                    walksheet = new Texture(Gdx.files.internal("Sprite-0010.png"));
                    break;
                case 1:
                    walksheet = new Texture(Gdx.files.internal("Sprite-0011.png"));
                    break;
            }
            
            tmp = TextureRegion.split(walksheet,32,32);
            walkFrames = new TextureRegion[10];
            int index = 0;
                    for (int j = 0; j < 10; j++) {
                        walkFrames[index++] = tmp[0][j];
                    }
            currAnim =  new Animation<TextureRegion>(frameD,walkFrames);
            Anims.set(counter,currAnim); 
            counter++;
        }
    }

    void movement(){

    }
}
