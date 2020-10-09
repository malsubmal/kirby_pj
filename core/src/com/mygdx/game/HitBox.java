package com.mygdx.game;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.math.Vector2;

public class HitBox implements ContactListener {

    public BodyDef bodyDef;
    public Body body;
    public boolean setActive = false;

    public HitBox(Vector2 pos, float rad) {
        bodyDef.type = BodyType.DynamicBody;
        //body at spawn
        bodyDef.position.set(pos.x, pos.y);
        body = myGame.world.createBody(bodyDef);
        body.setUserData(this);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(rad);

        Fixture fixture = body.createFixture(fixtureDef);
    }

    public HitBox(Vector2 pos, float height, float width) {
        bodyDef.type = BodyType.DynamicBody;
        //body at spawn
        bodyDef.position.set(pos.x, pos.y);
        body = myGame.world.createBody(bodyDef);
        body.setUserData(this);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(height/2, width/2);
        fixtureDef.shape = poly;

        Fixture fixture = body.createFixture(fixtureDef);
    }
     

    @Override
    public void beginContact(Contact contact) {
        Fixture fixture = contact.getFixtureA();
        Body bodyContact = fixture.getBody();
        Object contacter = bodyContact.getUserData();
        if (contacter instanceof Kirby) {
            System.out.println("Take Damage");
        }

    }



    @Override
    public void endContact(Contact contact) {}


    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {}

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {}

    //physics body
    //Event listener

}
