package com.sn.slide.screen;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sn.slide.ParallaxMap;
import com.sn.slide.Slide;
import com.sn.slide.component.PositionComponent;
import com.sn.slide.component.SpineComponent;
import com.sn.slide.component.VelocityComponent;
import com.sn.slide.system.MotionSystem;
import com.sn.slide.system.MovementSystem;
import com.sn.slide.system.SpriteRendererSystem;
import com.sn.slide.system.SpineRendererSystem;

public class GameScreen implements Screen {
	private BitmapFont font;
	private SpriteBatch batch;
	private World world;
	private ParallaxMap map;
	
	public GameScreen(Slide slide) {
		font = new BitmapFont();
		batch = new SpriteBatch();

		world = new World();
		world.setSystem(new MovementSystem());
		world.setSystem(new SpriteRendererSystem());
		world.setSystem(new SpineRendererSystem());
		world.setSystem(new MotionSystem());
		world.initialize();
		
		Entity ent = world.createEntity();
		ent.addComponent(new PositionComponent(50, 95));
		ent.addComponent(new VelocityComponent(100, 0));
		ent.addComponent(new SpineComponent("spineboy"));
		ent.addToWorld();
		
		map = new ParallaxMap(ent);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.55f, 0.55f, 0.55f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		map.render();
		world.setDelta(delta);
		world.process();
		
		batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20); 
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
	}
}
