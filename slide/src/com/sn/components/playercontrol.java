package com.sn.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class playercontrol extends component {
	private boolean enabled = true;
	
	public void setEnable(boolean enable) {
		this.enabled = enable;
	}
	
	public void update(float delta) {
		if (!enabled) {
			return;
		}
		
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			
		}
	}
}
