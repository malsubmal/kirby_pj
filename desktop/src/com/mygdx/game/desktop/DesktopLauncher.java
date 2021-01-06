package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.myGame;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DesktopLauncher {
	public static LwjglApplicationConfiguration config;
	public static void main (String[] arg) {
		config = new LwjglApplicationConfiguration();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		config.title = "myGame";
		config.width = (int) screenSize.getWidth();
		config.height = (int) screenSize.getHeight();
		new LwjglApplication(new myGame(), config);
	}
}
