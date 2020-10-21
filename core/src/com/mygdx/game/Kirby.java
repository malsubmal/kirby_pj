package com.mygdx.game;

public abstract class Kirby extends Characters {

    protected boolean aug = false;

    public Kirby(){
        if (!aug) {
        myGame.kirby = new KirbyDefault();
        }
    }

    public Kirby(Boolean check){
        super();
    }

    public void movement(int key){}
}
