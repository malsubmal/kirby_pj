package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class SuckBox extends HitBox {

    public SuckBox(boolean check) {
        super(check);
    }

    public SuckBox(Body mainBody, Vector2 spawnVector, float width, float height) {
        super(mainBody, spawnVector, width, height);
    }

}
