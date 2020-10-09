package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;


public class StrikeSensor {
    public boolean setActive = false;
    public FixtureDef fixtureDef;
    public Fixture fixture;
    

    public void create(Body body, float rad) {
        fixtureDef = new FixtureDef();
        fixtureDef.shape = new CircleShape();
        fixtureDef.shape.setRadius(rad);
        fixtureDef.isSensor = true;

        fixture = body.createFixture(fixtureDef);

    }
}
