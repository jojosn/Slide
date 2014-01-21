package com.sn.slide;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sn.screens.gamescreen;

public class Slide extends Game {

	@Override
	public void create() {
		setScreen(new gamescreen(this));
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
