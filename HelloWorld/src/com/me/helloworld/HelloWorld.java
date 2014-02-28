package com.me.helloworld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.me.helloworld.screens.GameScreen;
import com.me.helloworld.screens.MainMenu;
import com.me.helloworld.view.HelloAudio;

public class HelloWorld extends Game {
	
	public static final String VERSION = "prototype";
	public static final String LOG = "helloworld";
	public static final boolean DEBUG = true;
	FPSLogger log;
	
	@Override
	public void create() {		
		log = new FPSLogger();
		//HelloAudio.playMusic(true);
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		//HelloAudio.dispose();
	}

	@Override
	public void render() {		
		super.render();
		log.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
