package com.sn.prefabs;

import com.badlogic.gdx.Gdx;
import com.sn.components.spineanimation;

public class rambo {
	public static final String TAG = rambo.class.getName();
	
	public static entity create() {
		entity ent = new entity("rambo");
		
		spineanimation spineanim = (spineanimation) ent.AddComponent("spineanimation");
		spineanim.setSpine("spine/spineboy");
		spineanim.setxy((float) (50+Math.random()*200), 95);
		
		
		
		Gdx.app.debug(TAG, "rambo is created: " + ent.GetGUID());
		return ent;
	}
}
