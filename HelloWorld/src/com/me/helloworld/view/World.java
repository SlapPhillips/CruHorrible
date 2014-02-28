package com.me.helloworld.view;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.me.helloworld.HelloWorld;
import com.me.helloworld.model.Bullet;
import com.me.helloworld.model.Enemy;
import com.me.helloworld.model.Follower;
import com.me.helloworld.model.Player;

public class World {

	HelloWorld game;
	Player one, two;
	Array<Bullet> bullets = new Array<Bullet>();
	Array<Enemy> enemies = new Array<Enemy>();
	WorldRenderer wr;
	Iterator<Bullet> bIter;
	Iterator<Enemy> eIter;
	Bullet b;
	Enemy e;
	
	public World(HelloWorld game) {
		this.game = game;
		one = new Player(5f, 0, 64, 64, new Vector2(0, 0));
		two = new Player(5f, 0, 64, 64, new Vector2(1000, 0));
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Player getOne() {
		return one;
	}
	
	public Player getTwo() {
		return two;
	}
	
	public Array<Enemy> getEnemies() {
		return enemies;
	}
	
	public void update() {
		one.update();
		two.update();
		
		bIter = bullets.iterator();
		while (bIter.hasNext()) {
			b = bIter.next();
			b.update(one);
		}
		
		eIter = enemies.iterator();
		while (eIter.hasNext()) {
			e = eIter.next();
			e.advance(Gdx.graphics.getDeltaTime(), one);
			if(one.getBounds().overlaps(e.getBounds())) {
				Gdx.app.log(HelloWorld.LOG, "SHIIIIIIIIIIIT");
			}
		}
		
		bIter = bullets.iterator();
		while (bIter.hasNext()) {
			b = bIter.next();
			
			eIter = enemies.iterator();
			while (eIter.hasNext()) {
				e = eIter.next();

				if (e.getBounds().overlaps(b.getBounds())) {
					Gdx.app.log(HelloWorld.LOG, "GOTTAM");
					eIter.remove();
					bIter.remove();
				}
			}
		}
	}
	
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	
	public Array<Bullet> getBullets() {
		return bullets;
	}
	
	public WorldRenderer getRenderer() {
		return wr;
	}
	
	public void setRenderer(WorldRenderer wr) {
		this.wr = wr;
	}
	
	public void dispose() {
		
	}
	
}
