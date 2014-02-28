package com.me.helloworld.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends MoveableEntity {

	public static float speed = 15;
	
	public Bullet(float speed, float rotation, float width, float height, Vector2 position, Vector2 velocity) {
		super(speed, rotation, width, height, position);
		this.velocity = velocity;
	}
	
	@Override
	public void update(Player ship) {
		position.add(velocity.cpy().scl(Gdx.graphics.getDeltaTime()*super.speed));
		rotation = velocity.angle()-90;
		super.update(ship);
	}

}
