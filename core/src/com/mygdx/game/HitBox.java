package com.mygdx.game;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.math.Vector2;

public class HitBox{
    public boolean setActive = false;
    public Body body;
    public BodyDef bodyDef = new BodyDef();

    public HitBox(Body mainBody, Vector2 spawnVector, float rad) {
        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y);
        this.body = myGame.world.createBody(bodyDef);
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(rad);
        //this.body.setTransform(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y, 0);

        Fixture fixture = this.body.createFixture(fixtureDef);
        
    }

    public HitBox(boolean check) {}

    public HitBox(Body mainBody, Vector2 spawnVector, float width, float height) {
        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y);
        this.body = myGame.world.createBody(bodyDef);
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(height/2, width/2);
        fixtureDef.shape = poly;
        //this.body.setTransform(mainBody.getPosition().x + spawnVector.x, mainBody.getPosition().y + spawnVector.y, 0);

        Fixture fixture = this.body.createFixture(fixtureDef);
    }
     

}
