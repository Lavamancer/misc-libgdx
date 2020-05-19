package com.lavamancer.misc;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Main extends ApplicationAdapter {

	SpriteBatch spriteBatch;
	TextureRegion[][] textures;
	OrthographicCamera camera;
	Animation<TextureRegion> animationDown;
	Animation<TextureRegion> animationUp;
	Animation<TextureRegion> animationLeft;
	Animation<TextureRegion> animationRight;
	int direction = 0;
	float stateTime;
	Sound sound;


	@Override
	public void create () {
		spriteBatch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		camera.position.set(19, 19, 0);
		camera.zoom = 0.25f;

		loadAnimation(1);
		sound = Gdx.audio.newSound(Gdx.files.internal("bow.mp3"));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.8f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();

		TextureRegion currentFrame;
		switch (direction) {
			case 0: currentFrame = animationDown.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), true); break;
			case 1: currentFrame = animationUp.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), true); break;
			case 2: currentFrame = animationRight.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), true); break;
			case 3: default: currentFrame = animationLeft.getKeyFrame(stateTime += Gdx.graphics.getDeltaTime(), true);
		}

		spriteBatch.draw(currentFrame, 0, 0);

		spriteBatch.end();

		camera();
		input();
	}

	private void input() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) direction = 2;
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) direction = 0;
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) direction = 1;
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) direction = 3;

		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) loadAnimation(1);
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) loadAnimation(2);
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) loadAnimation(3);




		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			long soundId = sound.play();
			sound.setPan(soundId, 0, 1);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			long soundId = sound.play();
			sound.setPan(soundId, 1f, 1);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
			long soundId = sound.play();
			sound.setPan(soundId, 0, 0.5f);
			sound.setPitch(soundId, 0.95f);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			long soundId = sound.play();
			sound.setPan(soundId, -1f, 1);
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			long soundId = sound.play();
			sound.setPan(soundId, 0, 1);
			long soundId2 = sound.play();
			sound.setPan(soundId2, 1f, 1);
			long soundId3 = sound.play();
			sound.setPan(soundId3, 0, 0.5f);
			sound.setPitch(soundId3, 0.95f);
			long soundId4 = sound.play();
			sound.setPan(soundId4, -1f, 1);
		}

	}

	private void camera() {
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
	}

	private void loadAnimation(int index) {
		Texture texture;
		switch (index) {
			case 1: texture = new Texture("owlbear.png"); break;
			case 2: texture = new Texture("kobold.png"); break;
			case 3: default: texture = new Texture("beholder.png");
		}

		textures = TextureRegion.split(texture, texture.getWidth() / 3, texture.getHeight() / 4);
		animationDown = new Animation<>(0.225f, textures[0]);
		animationDown.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		animationUp = new Animation<>(0.225f, textures[1]);
		animationUp.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		animationLeft = new Animation<>(0.225f, textures[2]);
		animationLeft.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		animationRight = new Animation<>(0.225f, textures[3]);
		animationRight.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
	}



	
}
