/**
 * 
 */
package com.me.helloworld.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Follower extends Enemy {

	float ROTATION_SPEED = 500;

	public Follower(float SPEED, float rotation, float width, float height,
			Vector2 position) {
		super(SPEED, rotation, width, height, position);
	}

	@Override
	public void advance(float delta, Player ship) {
		position.lerp(ship.getPosition(), delta);
		
		rotation += delta * ROTATION_SPEED;
		
		if(rotation > 360)
			rotation -= 360;
		
		super.update(ship);		
	}

}