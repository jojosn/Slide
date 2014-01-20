package com.sn.prefabs;

import com.badlogic.gdx.Gdx;
import com.sn.slide.Entity;

public class cat {
	public static final String TAG = cat.class.getName();
	
	public static Entity create() {
		Entity ent = new Entity("cat");
		Gdx.app.debug(TAG, "a cat is created: " + ent.GetGUID());
		return ent;
	}
}
