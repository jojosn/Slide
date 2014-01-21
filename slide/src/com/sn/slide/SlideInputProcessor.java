package com.sn.slide;

import com.badlogic.gdx.InputProcessor;


public class SlideInputProcessor implements InputProcessor {
	public static final String TAG = SlideInputProcessor.class.getName();
	private SlideMap map;
	private float oldScreenX;
	private float oldScreenY;
	
	public SlideInputProcessor(SlideMap map) {
		this.map = map;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		oldScreenX = screenX;
		oldScreenY = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		EntityManager.GetInstance().CreatePrefab("rambo");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float dx = screenX - oldScreenX;
		float dy = screenY - oldScreenY;
		oldScreenX = screenX;
		oldScreenY = screenY;
		map.translate(-dx, dy);
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
