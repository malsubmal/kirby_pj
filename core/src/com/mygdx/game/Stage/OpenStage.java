package com.mygdx.game.Stage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.myGame;
import com.mygdx.game.HelperClass.AnimateWrapper;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.Tools.Animator;

public class OpenStage extends WrapperStage {
    
 
    Texture bg;
    float width, height;

    public OpenStage() {
        bg = new Texture(Gdx.files.internal("open.jpg"));
        width = bg.getWidth();
        height = bg.getHeight();
        System.out.println("finish loading OpenStage");
        loaded = true;
    
    }

   

    @Override
    public void StageDraw() {
        // TODO Auto-generated method stub
        myGame.getBatch().begin();
        
        myGame.getCamera().position.set(0,0,0);
        myGame.getBatch().draw(bg,-width/2,-height/2);

        myGame.getBatch().end();
        manageUI();

    }

    @Override
    public void manageUI() {
        // TODO Auto-generated method stub
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            myGame.bufferStage = new GameStage("testavail.tmx");
            System.out.println("loading GameStage");
        }

    }
}