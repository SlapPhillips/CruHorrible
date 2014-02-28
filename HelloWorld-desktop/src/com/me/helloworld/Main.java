package com.me.helloworld;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "test";
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.resizable = false;
		cfg.vSyncEnabled = false;
		//cfg.addIcon(path, fileType);
		new LwjglApplication(new HelloWorld(), cfg);
	}
}
