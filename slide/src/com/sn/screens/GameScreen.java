package com.sn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.sn.slide.EntityManager;
import com.sn.slide.PhysicsWorld;
import com.sn.slide.Sld;
import com.sn.slide.SlideInputProcessor;
import com.sn.slide.SlideMap;

public class gamescreen implements Screen {
	public static final String TAG = gamescreen.class.getName();
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		Sld.sldmap.render();
		EntityManager.GetInstance().update(delta);
		Sld.phyWorld.render(Sld.camera);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		Sld.sldmap = new SlideMap("map/skite.tmx");
		Sld.camera = Sld.sldmap.getGroundCamera();
		Sld.phyWorld = new PhysicsWorld();
		Gdx.input.setInputProcessor(new SlideInputProcessor(Sld.sldmap));
	}

	@Override
	public void hide() {
		Sld.sldmap.dispose();
		Sld.sldmap = null;
		
		Sld.phyWorld.dispose();
		Sld.phyWorld = null;
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
	
}
