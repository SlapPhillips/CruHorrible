package com.me.helloworld.screens;

import com.badlogic.gdx.Screen;
import com.me.helloworld.HelloWorld;
import com.me.helloworld.view.World;
import com.me.helloworld.view.WorldRenderer;

public class GameScreen implements Screen {

	HelloWorld game;
	World world;
	WorldRenderer render;
	
	public GameScreen (HelloWorld game) {
		this.game = game;
		world = new World(game);
		render = new WorldRenderer(world);
	}
	
	@Override
	public void render(float delta) {
		world.update();
		render.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
	public void dispose() {
		world.dispose();
		render.dispose();
	}

	
	
}
