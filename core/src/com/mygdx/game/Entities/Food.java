package com.mygdx.game.Entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.myGame;
import com.mygdx.game.Entities.Characters;
import com.mygdx.game.HelperClass.Updatable;
import com.mygdx.game.Stage.GameStage;

public class Food {
    public boolean eaten = false;
    public Body body;
    public BodyDef bodyDef = new BodyDef();
    public Texture img;

    //round hitbox
    public Food(Body mainBody, Vector2 spawnVector) {
        this.img = new Texture(Gdx.files.internal("iceCream.png"));
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
        fixtureDef.shape.setRadius(10);
        Fixture fixture = this.body.createFixture(fixtureDef);
        
    }
}
