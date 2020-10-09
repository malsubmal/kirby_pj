package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StrikeZone implements ContactListener, ContactFilter {

    public Fixture kirbyFixture;

    public void setFixture(Fixture fixtureinput) {
        kirbyFixture = fixtureinput;
    }
   
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void beginContact(Contact contact) {
        //System.out.println("Event Listener working");
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Fixture contactFix = null;
        Object opp = null;
        Enemy refereddOpp = null;

        if (fixtureA.getBody().getUserData() instanceof Kirby) {
            //System.out.println(" recognize Kirby");
             if (fixtureB.isSensor()) {
                opp = fixtureB.getBody().getUserData();
                refereddOpp = (UFO) opp;
                ((UFO) refereddOpp).setActive = true;
                
               System.out.println("setActive");
              }
        } else if (fixtureB.getBody().getUserData() instanceof Kirby){
            //System.out.println(" recognize Kirby");
            if (fixtureA.isSensor()) {
                opp = fixtureA.getBody().getUserData();
                refereddOpp = (UFO) opp;
               ((UFO) refereddOpp).setActive = true;
               System.out.println("setActive");
        
            }

    }
}

    @Override
    public void endContact(Contact contact) {
        // TODO Auto-generated method stub

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // TODO Auto-generated method stub

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO Auto-generated method stub

    }
     

}
