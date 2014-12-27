package com.tcg.flap.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tcg.flap.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		int width, height;
		height = 600;
		width = (int) (height * .5625);
		config.height = height;
		config.width = width;
		config.resizable = false;
		new LwjglApplication(new Game(), config);
	}
}
