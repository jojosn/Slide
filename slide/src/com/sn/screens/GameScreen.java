package com.sn.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.sn.slide.EntityManager;
import com.sn.slide.Slide;
import com.sn.slide.SlideInputProcessor;
import com.sn.slide.SlideMap;

public class gamescreen implements Screen {
	public static final String TAG = gamescreen.class.getName();
	//private Slide slide;
	private SlideMap map;
	
	public gamescreen(Slide slide) {
		//this.slide = slide;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		map.render();
		EntityManager.GetInstance().update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		map = new SlideMap("map/skite.tmx");
		Gdx.input.setInputProcessor(new SlideInputProcessor(map));
	}

	@Override
	public void hide() {
		map.dispose();
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
		// TODO Auto-generated method stub
		
	}
	
}
