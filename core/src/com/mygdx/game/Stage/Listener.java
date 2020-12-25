package com.mygdx.game.Stage;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.mygdx.game.Entities.Enemy;
import com.mygdx.game.Entities.Food;
import com.mygdx.game.Entities.Kirby;
import com.mygdx.game.Sensors.HitBox;
import com.mygdx.game.Sensors.SuckBox;
import com.mygdx.game.myGame;
import com.mygdx.game.setting;
import com.mygdx.game.Entities.Characters;

public class Listener implements ContactListener {   
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
                //destroy enemy
                ((Enemy) opp).HP = -1;
               Kirby.type = ((Characters) opp).type;
               Kirby.change = true;
                System.out.println(Kirby.type);
            }
        }

        //Kirby hurts Enemy when harm their bodies
        
        if (fixtureA.getBody().getUserData() instanceof Kirby) {
                if (fixtureB.isSensor()) {
        opp = fixtureB.getBody().getUserData();
        if (opp instanceof Enemy) {
        ((Enemy) opp).setActive = true;
        System.out.println("setActive");
        } else if (opp instanceof Food) {
            //play eat sound
            ((Food) opp).eaten = true;
        }
        }
        } else if (fixtureB.getBody().getUserData() instanceof Kirby){
            if (fixtureA.isSensor()) {
                opp = fixtureA.getBody().getUserData();
                if (opp instanceof Enemy) {
                    ((Enemy) opp).setActive = true;
                    System.out.println("setActive");
                } else if (opp instanceof Food) {
                    ((Food) opp).eaten = true;
                    if (myGame.kirby.HP < 50) {
                        //play eat sound
                        myGame.kirby.HP += 10;
                    }
                }
            }
        } else {
            if (fixtureA.getBody().getUserData() instanceof HitBox) {
                if ( !fixtureB.isSensor() && fixtureB.getBody().getType() == BodyDef.BodyType.DynamicBody) {
                    opp = fixtureB.getBody().getUserData();
                    if (!opp.equals(((HitBox)fixtureA.getBody().getUserData()).caller)) {
                    ((Characters) opp).HP -= setting.DP;
                    System.out.println("HP decreases");
                    }
                }
            }  else if (fixtureB.getBody().getUserData() instanceof HitBox) {
                if (!fixtureA.isSensor() && fixtureA.getBody().getType() == BodyDef.BodyType.DynamicBody)  {
                    opp = fixtureA.getBody().getUserData();
                    if (!opp.equals(((HitBox)fixtureB.getBody().getUserData()).caller)) {
                    ((Characters) opp).HP -= setting.DP;
                    System.out.println("HP decreases");
                    }
                }
             }
            else {}
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
