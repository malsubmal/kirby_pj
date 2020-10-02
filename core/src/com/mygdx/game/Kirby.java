package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Kirby extends Characters{
	public Texture defsprite;
    public Rectangle bod;
    public Texture walksheet;
    public ArrayList<Animation<TextureRegion>> Anims;
    public Animation<TextureRegion> animHolder;
    public TextureRegion[] walkFrames;
    public TextureRegion[][] tmp;
    public TextureRegion currentFrame;
    private int movementVector = 30;
    private float frameD = 0.1f;
    private int counter = 0;
    private String sourceAnim;

    public void create(){
    bod = new Rectangle();
    bod.x = 0; // center the bucket horizontally
    bod.y = 0; // bottom left corner of the bucket is 20 pixels above the bottom screen edge
    bod.width = 32;
    bod.height = 32;

    //sprite
    defsprite = new Texture(Gdx.files.internal("Sprite-0001.gif"));
   
    //Anim
    Anims = new ArrayList<Animation<TextureRegion>>();
    Anims.add(animHolder);
    Anims.add(animHolder);
    for (Animation<TextureRegion> currAnim : Anims) {
    switch (counter) {
    	case 0:
    		walksheet = new Texture(Gdx.files.internal("Sprite-0010.png"));
    		break;
    	case 1:
    		walksheet = new Texture(Gdx.files.internal("Sprite-0011.png"));
    		break;
    }
    
    tmp = TextureRegion.split(walksheet,32,32);
 	walkFrames = new TextureRegion[10];
 	int index = 0;
 			for (int j = 0; j < 10; j++) {
 				walkFrames[index++] = tmp[0][j];
 			}
 	currAnim =  new Animation<TextureRegion>(frameD,walkFrames);
 	Anims.set(counter,currAnim); 
 	counter++;
    }
    }
    
    void movement(int keyPressed){
        switch (keyPressed) {
            case Keys.RIGHT:
                bod.x += movementVector * Gdx.graphics.getDeltaTime();
                currentFrame  = Anims.get(0).getKeyFrame(myGame.stateTime, true);
                System.out.print("input right received");
                break;
            case Keys.LEFT:
                bod.x -= movementVector * Gdx.graphics.getDeltaTime();
                currentFrame = Anims.get(1).getKeyFrame(myGame.stateTime, true);
                System.out.print("input right received");
                break;
            default:
                break;
        }
        
    }
}
