package com.sn.slide;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sn.slide.screen.SampleScreen;

public class Slide extends Game {

	@Override
	public void create() {
		setScreen(new SampleScreen(this));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
