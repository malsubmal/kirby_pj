package com.mygdx.game.Stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class OpenScreen implements Screen {

    private Stage stage;
    private Batch batch;
    private Texture image;

    public OpenScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
        Table table = new Table();
        stage.addActor(table);

        // table.setFillParent(true);
        table.setDebug(true);
        image = new Texture(Gdx.files.internal("EnemyDemo.png"));
        //ButtonStyle buttonStyle = new ButtonStyle();
     
        // TextButton newGame = new TextButton("text1", skin);
        Button preferences = new Button();
        Button exit = new Button();
        Button newGame = new Button();

        //preferences.add(skin);
        table.add(newGame).fillX().uniformX();
		table.row().pad(10, 0, 10, 0);
		table.add(preferences).fillX().uniformX();
		table.row();
        table.add(exit).fillX().uniformX();
        
        
		exit.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("click exit");				
			}
		});
		
		newGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("click new game");			
			}
		});
		
		preferences.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				System.out.println("click pref");					
			}
		});
		
        System.out.println("creating stage");

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        // clear the screen ready for next set of images to be drawn

		//Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// tell our stage to do actions and draw itself
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.begin();
        //batch.draw(image, preferens, y);
        batch.end();

		stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
    
}
