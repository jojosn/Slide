package com.sn.prefabs;

import com.badlogic.gdx.Gdx;
import com.sn.components.locomotor;
import com.sn.components.physics;
import com.sn.components.spineanimation;
import com.sn.slide.Sld;

public class rambo {
	public static final String TAG = rambo.class.getName();
	
	public static entity create() {
		entity ent = new entity("rambo");
		
		// add skeleton animation
		spineanimation spineanim = (spineanimation) ent.AddComponent("spineanimation");
		spineanim.setSpine("spine/comicboy");
		
		// add physics
		physics phy = (physics) ent.AddComponent("physics");
		phy.setSpineAnim(spineanim);
		phy.setxy((float) (500+Math.random()*200), 300);
		
		// add locomotor
		ent.AddComponent("locomotor");
		ent.AddComponent("playercontrol");
		//locomotor motor = (locomotor) ent.AddComponent("locomotor");
		
		Sld.player = ent;
		Gdx.app.debug(TAG, "rambo is created: " + ent.GetGUID());
		return ent;
	}
}
