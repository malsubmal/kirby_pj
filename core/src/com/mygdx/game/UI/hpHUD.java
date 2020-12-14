package com.mygdx.game.UI;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
//add these to menu screen
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
 
//for developing
public class hpHUD extends Actor{
    static ArrayList<Vector2> barPos = new ArrayList<Vector2>();
    Sprite blackbar;
    public hpHUD(){
      blackbar = new Sprite(new Texture(Gdx.files.internal("blackbar.png")));
      TiledMap tilemap = new TmxMapLoader().load("HUDmap.tmx");
		  //tilemaprenderer = new OrthogonalTiledMapRenderer(tilemap);

		  MapObjects objects = tilemap.getLayers().get("object").getObjects();
    	for (MapObject object: objects) {
      Rectangle rectangle = ((RectangleMapObject)object).getRectangle();
      //take posY
			Vector2 center = new Vector2();
      rectangle.getCenter(center);
      //take posX
      for (int i = 13; i>= 0;i--){
        barPos.add(new Vector2(rectangle.getWidth()*(2*i+1),center.y));
      }
      
		}
        }

  @Override
  public void draw(Batch batch, float parentAlpha) {
	blackbar.draw(batch);
  }
}
