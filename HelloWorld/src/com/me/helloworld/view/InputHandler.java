package com.me.helloworld.view;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.me.helloworld.model.Bullet;
import com.me.helloworld.model.Player;

public class InputHandler implements InputProcessor{
	
	World world;
	Player one, two;
	Vector3 touch = new Vector3();
	Vector2 vec2Touch = new Vector2();
	
	public InputHandler(World world) {
		this.world = world;
	}

	@Override
	public boolean keyDown(int keycode) {
		one = world.getOne();
		switch(keycode){
			case Keys.W:
				one.getVelocity().y = 20;
				break;
			case Keys.S:
				one.getVelocity().y = -20;
				break;
			case Keys.A:
				one.getVelocity().x = -20;
				break;
			case Keys.D:
				one.getVelocity().x = 20;
				break;
			case Keys.F:
				one.setActing(true);
			case Keys.E:
				one.setPunching(true);
			default:
				break;
		}
		two = world.getTwo();
		switch(keycode){
			case Keys.UP:
				two.getVelocity().y = 20;
				break;
			case Keys.DOWN:
				two.getVelocity().y = -20;
				break;
			case Keys.LEFT:
				two.getVelocity().x = -20;
				break;
			case Keys.RIGHT:
				two.getVelocity().x = 20;
				break;
			case Keys.ENTER:
				two.setActing(true);
			case Keys.COMMA:
				two.setPunching(true);
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		one = world.getOne();
		switch(keycode){
			case Keys.W:
				if(one.getVelocity().y == 20)
					one.getVelocity().y = 0;
				break;
			case Keys.S:
				if(one.getVelocity().y == -20)
					one.getVelocity().y = 0;
				break;
			case Keys.A:
				if(one.getVelocity().x == -20)
					one.getVelocity().x = 0;
				break;
			case Keys.D:
				if(one.getVelocity().x == 20)
					one.getVelocity().x = 0;
				break;
			case Keys.F:
				one.setActing(false);
			case Keys.E:
				one.setPunching(false);
			default:
				break;
		}
		two = world.getTwo();
		switch(keycode){
			case Keys.UP:
				if(two.getVelocity().y == 20)
					two.getVelocity().y = 0;
				break;
			case Keys.DOWN:
				if(two.getVelocity().y == -20)
					two.getVelocity().y = 0;
				break;
			case Keys.LEFT:
				if(two.getVelocity().x == -20)
					two.getVelocity().x = 0;
				break;
			case Keys.RIGHT:
				if(two.getVelocity().x == 20)
					two.getVelocity().x = 0;
				break;
			case Keys.ENTER:
				two.setActing(false);
			case Keys.COMMA:
				two.setPunching(false);
			default:
				break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}