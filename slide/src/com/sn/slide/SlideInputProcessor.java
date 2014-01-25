package com.sn.slide;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.sn.components.locomotor;


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
		if (keycode == Keys.W) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.jump();
		} else if (keycode == Keys.A) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.walkBackward();
		} else if (keycode == Keys.D) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.walkForward();
		} else if (keycode == Keys.S) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.kick();
		} else if (keycode == Keys.SPACE) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.punch();
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.W) {
			
		} else if (keycode == Keys.A || keycode == Keys.D) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.stopMoving();
		}
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
