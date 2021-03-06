package com.mygdx.game.Sensors;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.myGame;
import com.mygdx.game.Entities.Characters;
import com.mygdx.game.HelperClass.Updatable;
import com.mygdx.game.Stage.GameStage;

public class HitBox implements Updatable{
    public boolean setActive = false;
    public boolean isSuckBox = false;
    public Body body;
    public Characters caller;
    public BodyDef bodyDef = new BodyDef();
    public int DP;
    
    private float radius;

    //round hitbox
    public HitBox(Body mainBody, Vector2 spawnVector, float rad) {
        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y);
        //bodyDef.gravityScale = 0;
        World tempWorld = ((Characters)mainBody.getUserData()).world;
        //((GameStage)myGame.currentStage).world;
        this.body = tempWorld.createBody(bodyDef);
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.density = 0.00001f;
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(rad);
        radius = rad;
        this.caller = (Characters) mainBody.getUserData();
        //this.body.setTransform(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y, 0);
        this.DP = 1;
        Fixture fixture = this.body.createFixture(fixtureDef);
        
    }

    public float getRadius() {
        return radius;
    }

    public HitBox(boolean check) {}

    public void behaviour(){}

    //hitbox update

    //square hitbox
    public HitBox(Body mainBody, Vector2 spawnVector, float width, float height) {

        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y);
        this.body = ((GameStage)myGame.currentStage).world.createBody(bodyDef);
        //bodyDef.gravityScale = 0;
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 0.00001f;
        fixtureDef.isSensor = true;
        this.DP = 1;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox( height/2, width/2);
        fixtureDef.shape = poly;
        this.caller = (Characters) mainBody.getUserData();
        //this.body.setTransform(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y, 0);

        Fixture fixture = this.body.createFixture(fixtureDef);
    }

    public void setActive(boolean input){
        if (input) {
            this.body.setActive(true);
        } else {
            this.body.setActive(false);
        }
    }

    @Override
    public void Updatable() {
        // TODO Auto-generated method stub

    }
     

}
