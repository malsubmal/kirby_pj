package com.mygdx.game.Stage;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.myGame;
import com.mygdx.game.HelperClass.AnimateWrapper;
import com.mygdx.game.HelperClass.WrapperStage;
import com.mygdx.game.Tools.Animator;

public class TestStage extends WrapperStage {
    //public OrthogonalTiledMapRenderer tilemaprenderer;

    //START SCREEN TESTING

    //PAUSE SCREEN TESTING
    
    ArrayList<Animation<TextureRegion>> kirbyGreetingAnim;
    ArrayList<Animation<TextureRegion>> extraAnims;

    public TestStage() {
      
        
        
    }

   

    @Override
    public void StageDraw() {
        // TODO Auto-generated method stub
        myGame.getBatch().begin();
        myGame.getBatch().draw(extraAnims.get(0).getKeyFrame(myGame.stateTime,true),0,0);
        myGame.getBatch().end();

    }

    @Override
    public void manageUI() {
        // TODO Auto-generated method stub
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {}

    }
}
