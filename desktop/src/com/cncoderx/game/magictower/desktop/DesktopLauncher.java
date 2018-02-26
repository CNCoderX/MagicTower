package com.cncoderx.game.magictower.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cncoderx.game.magictower.GameContext;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 800;
//		new LwjglApplication(new TestApplication(), config);
		new LwjglApplication(new GameContext(), config);
	}
}
