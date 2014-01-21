package com.sn.prefabs;

import com.badlogic.gdx.Gdx;

public class cat {
	public static final String TAG = cat.class.getName();
	
	public static entity create() {
		entity ent = new entity("cat");
		Gdx.app.debug(TAG, "a cat is created: " + ent.GetGUID());
		return ent;
	}
}
