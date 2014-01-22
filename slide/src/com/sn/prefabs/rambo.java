package com.sn.prefabs;

import com.badlogic.gdx.Gdx;
import com.sn.components.physics;
import com.sn.components.spineanimation;

public class rambo {
	public static final String TAG = rambo.class.getName();
	
	public static entity create() {
		entity ent = new entity("rambo");
		
		// add skeleton animation
		spineanimation spineanim = (spineanimation) ent.AddComponent("spineanimation");
		spineanim.setSpine("spine/spineboy");
		
		// add physics
		physics phy = (physics) ent.AddComponent("physics");
		phy.setSpineAnim(spineanim);
		phy.setxy((float) (50+Math.random()*200), 500);
		
		Gdx.app.debug(TAG, "rambo is created: " + ent.GetGUID());
		return ent;
	}
}
