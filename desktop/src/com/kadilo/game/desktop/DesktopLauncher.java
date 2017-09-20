package com.kadilo.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kadilo.game.KadiloGame;
import com.kadilo.game.screens.tmp.TmpScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//  float aspect = 4f / 3f;
		 float aspect = 16f / 9f;
		config.height = 800;
		config.width = (int)(config.height * aspect);
		new LwjglApplication(new KadiloGame(), config);
		//new LwjglApplication( new TmpScreen(), config);
	}
}
