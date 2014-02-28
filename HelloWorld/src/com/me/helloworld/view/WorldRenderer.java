package com.me.helloworld.view;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.me.helloworld.HelloWorld;
import com.me.helloworld.model.Bullet;
import com.me.helloworld.model.Enemy;
import com.me.helloworld.model.Player;

public class WorldRenderer {
	
	World world;
	static final int WIDTH = 1280;
	static final int HEIGHT = 720;
	SpriteBatch batch;
	Player one, two;
	OrthographicCamera cam;
	Texture playerTexture, followerTexture, bulletTexture;
	ShapeRenderer sr;
	Array<Bullet> bullets;
	Array<Enemy> enemies;
	Iterator<Bullet> bIter;
	Bullet b;
	Animation idleAnimationOne, idleAnimationTwo, walkAnimationOne, walkAnimationTwo, punchAnimationOne, punchAnimationTwo, gunAnimation, swordAnimation;
	TextureRegion currentFrameOne, currentFrameTwo;
	TextureRegion[] idleFramesOne, idleFramesTwo, walkFramesOne, walkFramesTwo, punchFramesOne, punchFramesTwo, gunFrames, swordFrames;
	float stateTime;
	
	public WorldRenderer(World world) {
		this.world = world;
		
		world.setRenderer(this);
		
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.position.set(0, 0, 0);		
		cam.setToOrtho(false, WIDTH, HEIGHT);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		playerTexture = new Texture("data/ohgodwhy.png");
		playerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		TextureRegion[][] tmp = TextureRegion.split(playerTexture, playerTexture.getWidth()/5, playerTexture.getHeight()/6);
		idleFramesOne = new TextureRegion[2];
		idleFramesTwo = new TextureRegion[2];
		walkFramesOne = new TextureRegion[5];
		walkFramesTwo = new TextureRegion[5];
		punchFramesOne = new TextureRegion[3];
		punchFramesTwo = new TextureRegion[3];
		gunFrames = new TextureRegion[3];
		swordFrames = new TextureRegion[3];
        int index = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 1; j++) {
                idleFramesOne[index++] = tmp[j][i];
            }
        }
        int index2 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 3; j < 4; j++) {
            	idleFramesTwo[index2++] = tmp[j][i];
            }
        }
        int index3 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 2; j++) {
            	walkFramesOne[index3++] = tmp[j][i];
            }
        }
        int index4 = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 4; j < 5; j++) {
            	walkFramesTwo[index4++] = tmp[j][i];
            }
        }
        int index5 = 0;
        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 1; j++) {
            	punchFramesOne[index5++] = tmp[j][i];
            }
        }
        int index6 = 0;
        for (int i = 2; i < 5; i++) {
            for (int j = 3; j < 4; j++) {
            	punchFramesTwo[index6++] = tmp[j][i];
            }
        }
        int index7 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 2; j < 3; j++) {
            	gunFrames[index7++] = tmp[j][i];
            }
        }
        int index8 = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 4; j < 5; j++) {
            	swordFrames[index8++] = tmp[j][i];
            }
        }
        idleAnimationOne = new Animation(0.5f,idleFramesOne);
        idleAnimationTwo = new Animation(0.5f,idleFramesTwo);
        walkAnimationOne = new Animation(0.2f,walkFramesOne);
        walkAnimationTwo = new Animation(0.2f,walkFramesTwo);
        punchAnimationOne = new Animation(0.5f,punchFramesOne);
        punchAnimationTwo = new Animation(0.5f,punchFramesTwo);
        gunAnimation = new Animation(0.5f,gunFrames);
        swordAnimation = new Animation(0.5f,swordFrames);

        stateTime = 0f;
        
        idleAnimationOne.setPlayMode(Animation.LOOP);
        idleAnimationTwo.setPlayMode(Animation.LOOP);
        walkAnimationOne.setPlayMode(Animation.LOOP);
        walkAnimationTwo.setPlayMode(Animation.LOOP);
        punchAnimationOne.setPlayMode(Animation.NORMAL);
        punchAnimationTwo.setPlayMode(Animation.NORMAL);
        gunAnimation.setPlayMode(Animation.NORMAL);
        swordAnimation.setPlayMode(Animation.NORMAL);
        
		followerTexture = new Texture("data/follower.png");
		followerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		bulletTexture = new Texture("data/bullet.png");
		bulletTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		sr = new ShapeRenderer();
	}
	
	public void render() {
		
		Gdx.gl20.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		one = world.getOne();
		two = world.getTwo();
		enemies = world.getEnemies();
		bullets = world.getBullets();
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		stateTime += Gdx.graphics.getDeltaTime();
		
		if(one.isActing() == false && one.isPunching() == false && one.getVelocity().x == 0 && one.getVelocity().y == 0) 
		{currentFrameOne = idleAnimationOne.getKeyFrame(stateTime);}
		if(two.isActing() == false && two.isPunching() == false && two.getVelocity().x == 0 && two.getVelocity().y == 0) 
		{currentFrameTwo = idleAnimationTwo.getKeyFrame(stateTime);}
		
		if(one.getVelocity().x != 0 || one.getVelocity().y != 0) {
			currentFrameOne = walkAnimationOne.getKeyFrame(stateTime);
		}
		
		if(two.getVelocity().x != 0 || two.getVelocity().y != 0) {
			currentFrameTwo = walkAnimationTwo.getKeyFrame(stateTime);
		}
		
		
		batch.begin();
		
		//batch.draw(region, x, y, originX, originY, width, height, scaleX, scaleY, rotation);
		//batch.draw(texture, x, y, originX, originY, width, height, scaleX, scaleY, rotation, srcX, srcY, srcWidth, srcHeight, flipX, flipY);
		
		batch.draw(currentFrameOne, one.getPosition().x, one.getPosition().y, one.getWidth()/2, one.getHeight()/2, one.getWidth(), one.getHeight(), 1, 1, one.getRotation());
		batch.draw(currentFrameTwo, two.getPosition().x, two.getPosition().y, two.getWidth()/2, two.getHeight()/2, two.getWidth(), two.getHeight(), 1, 1, two.getRotation());
		
		bIter = bullets.iterator();
		while(bIter.hasNext()) {
			b = bIter.next();
			batch.draw(bulletTexture, b.getPosition().x, b.getPosition().y, b.getWidth()/2, b.getWidth()/2, b.getWidth(), b.getHeight(), 1, 1, b.getRotation(), 0, 0, bulletTexture.getWidth(), bulletTexture.getHeight(), false, false);
		}
		
		batch.end();
		
		if (HelloWorld.DEBUG) {
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.CYAN);
		sr.rect(one.getBounds().x, one.getBounds().y, one.getBounds().width, one.getBounds().height);
		sr.rect(two.getBounds().x, two.getBounds().y, two.getBounds().width, two.getBounds().height);
		sr.setColor(Color.MAGENTA);
		while (bIter.hasNext()) {
			b = bIter.next();
			sr.rect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height);
		}
		sr.end();
		}
	}
	
	public OrthographicCamera getCamera() {
		return cam;
	}
	
	public void dispose() {
		batch.dispose();
		playerTexture.dispose();
		followerTexture.dispose();
		bulletTexture.dispose();
		sr.dispose();
	}
	
}
