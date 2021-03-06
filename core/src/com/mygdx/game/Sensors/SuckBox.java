package com.mygdx.game.Sensors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SuckBox extends HitBox {

    //create Suckbox separate from HitBox

    public SuckBox(boolean check) {
        super(check);
        isSuckBox = true;
    }

    public SuckBox(Body mainBody, Vector2 spawnVector, float width, float height) {
        super(mainBody, spawnVector, width, height);        
        isSuckBox = true;
    }

}
