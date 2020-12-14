package com.mygdx.game.Stage;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.HelperClass.AnimateWrapper;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.Tools.Animator;

public abstract class TestStage extends WrapperStage {
    TestStage() {
        ArrayList<AnimateWrapper> tempArray = new ArrayList<>();
        AnimateWrapper kirbyGreeting = new AnimateWrapper("titleanimt.png", 325, 80);
        tempArray.add(kirbyGreeting);
        ArrayList<Animation<TextureRegion>> kirbyGreetingAnim = Animator.createAnim(tempArray);
    }

    @Override
    public void StageDraw() {
        // TODO Auto-generated method stub

    }

    @Override
    public void manageUI() {
        // TODO Auto-generated method stub

    }
}
