package com.sn.slide;

import com.badlogic.gdx.Game;
import com.sn.slide.screen.GameScreen;
import com.sn.slide.screen.SplashScreen;

public class Slide extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

}
