package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.*;

public class Listener implements ContactListener {

    public Fixture kirbyFixture;

    public void setFixture(Fixture fixtureinput) {
        kirbyFixture = fixtureinput;
    }
   
    //LOOK INTO CONTACT FILTER
    //also do some thing abt this mess bruh

    @Override
    public void beginContact(Contact contact) {
        //System.out.println("Event Listener working");
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Fixture contactFix = null;
        Object opp = null;
        Enemy refereddOpp = null;

        if (fixtureA.getBody().getUserData() instanceof SuckBox) {
            if (fixtureB.getBody().getUserData() instanceof Enemy) {
                opp = fixtureB.getBody().getUserData();
               Kirby.type = ((Characters) opp).type;
               Kirby.change = true;
                System.out.println(Kirby.type);
            }
        }

        if (fixtureA.getBody().getUserData() instanceof HitBox) {
            if (fixtureB.getBody().getUserData() instanceof Characters) {
                opp = fixtureB.getBody().getUserData();
                if (!opp.equals(((HitBox)fixtureA.getBody().getUserData()).caller)) {
                ((Characters) opp).HP -= 10;
                System.out.println("HP decreases");
                }
            }
        }  else if (fixtureB.getBody().getUserData() instanceof HitBox) {
            if (fixtureA.getBody().getUserData() instanceof Characters) {
                opp = fixtureA.getBody().getUserData();
                if (!opp.equals(((HitBox)fixtureB.getBody().getUserData()).caller)) {
                ((Characters) opp).HP -= 10;
                System.out.println("HP decreases");
                }
            }
         }
         else {
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
