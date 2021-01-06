package com.mygdx.game.Entities;

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
import com.mygdx.game.myGame;
import com.mygdx.game.setting;
import com.mygdx.game.HelperClass.AnimateWrapper;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.Stage.GameStage;
import com.mygdx.game.Tools.Animator;


public abstract class Characters  {
    public int HP = setting.HP;
	protected int DP = setting.DP;
    protected Texture defsprite;
    protected BodyDef bodyDef = new BodyDef();
    public boolean setActive = false;
    public Body body;
    public FixtureDef fixtureDef;
    public Fixture fixture;
    protected int vcon = 5000;
    protected int density = 10;
    protected int bodyradius;
    protected Vector2 spawnVector;
    public boolean defeat;
    public ArrayList<Animation<TextureRegion>> Anims;
    public TextureRegion currentFrame;
    protected String[] spriteSource;
    public ArrayList<AnimateWrapper> spriteSourceVar;
    protected int[] height, width;
    public Vector2 spriteOffset = new Vector2(0, 0);
    public enum elemental {
        electrice,
        neutral,
        fire,
        two,
        three
    };
    public elemental type;
    public GameStage ownerStage;
    public World world;
    public HitBox hitBox;

    public void create(World world){
        this.defineSpawnVector();
        this.world = world;
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(spawnVector.x, spawnVector.y);
        body = this.world.createBody(bodyDef);
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

    public TextureRegion getFrame(int order){        
        TextureRegion currentFrame  = Anims.get(order).getKeyFrame(myGame.stateTime, true);
        if (spriteSourceVar != null) {
        if ((spriteSourceVar.get(order).height != 0) && (spriteSourceVar.get(order).width != 0)) {
            spriteOffset = new Vector2(spriteSourceVar.get(order).width,spriteSourceVar.get(order).height);
        } }
        return currentFrame;
    }

}
