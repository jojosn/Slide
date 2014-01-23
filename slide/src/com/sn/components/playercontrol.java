package com.sn.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.sn.slide.Sld;

public class playercontrol extends component {
	private boolean enabled = true;
	
	public void setEnable(boolean enable) {
		this.enabled = enable;
	}
	
	public void update(float delta) {
		if (!enabled) {
			return;
		}
		
		/*
		if (Gdx.input.isKeyPressed(Keys.D)) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.walkForward();
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			locomotor motor = (locomotor) Sld.player.getComponent("locomotor");
			motor.walkBackward();
		}
		*/
		
	}
}
