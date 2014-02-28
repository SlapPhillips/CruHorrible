package com.me.helloworld.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Player extends MoveableEntity {

	boolean acting, punching;
	
	public Player(float speed, float rotation, float width, float height, Vector2 position) {
		super(speed, rotation, width, height, position);
	}

	public void update() {
		position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()*super.speed));
		bounds.x = position.x;
		bounds.y = position.y;
	}

	public boolean isActing() {
		return acting;
	}

	public void setActing(boolean acting) {
		this.acting = acting;
	}

	public boolean isPunching() {
		return punching;
	}

	public void setPunching(boolean punching) {
		this.punching = punching;
	}
	
}
