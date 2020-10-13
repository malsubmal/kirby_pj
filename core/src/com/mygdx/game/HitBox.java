package com.mygdx.game;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.math.Vector2;

public class HitBox{
    public boolean setActive = false;
    public Body body;
    public BodyDef bodyDef = new BodyDef();

    public HitBox(Body body, Vector2 spawnVector, float rad) {
        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(body.getPosition().x + spawnVector.x, body.getPosition().y + spawnVector.y);
        this.body = myGame.world.createBody(bodyDef);
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(rad);

        Fixture fixture = this.body.createFixture(fixtureDef);
        
    }

    public HitBox(Body body, Vector2 spawnVector, float height, float width) {
        bodyDef.type = BodyType.KinematicBody;
        bodyDef.position.set(body.getPosition().x + spawnVector.x, body.getPosition().y + spawnVector.y);
        this.body = myGame.world.createBody(bodyDef);
        this.body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.isSensor = true;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(height/2, width/2);
        fixtureDef.shape = poly;

        Fixture fixture = this.body.createFixture(fixtureDef);
    }
     

}
