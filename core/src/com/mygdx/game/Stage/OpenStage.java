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
    
    ArrayList<Animation<TextureRegion>> kirbyGreetingAnim;
    String source = "titleseq.png";
    Texture bg;
    float width, height;

    public OpenStage() {
        Sound bgSong = Gdx.audio.newSound(Gdx.files.internal("Hyrule.mp3"));
        bgSong.loop();
        bg = new Texture(Gdx.files.internal("open.jpg"));
        width = bg.getWidth();
        height = bg.getHeight();
        kirbyGreetingAnim = Animator.createAnim(source);
        System.out.println("finish loading OpenStage");
    }

   

    @Override
    public void StageDraw() {
        // TODO Auto-generated method stub
        myGame.getBatch().begin();
        myGame.getBatch().draw(bg,-width/2,-height/2);
        myGame.getBatch().draw(kirbyGreetingAnim.get(0).getKeyFrame(myGame.stateTime,true),-myGame.getCamera().viewportWidth/2,-myGame.getCamera().viewportHeight/2);
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