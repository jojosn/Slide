package com.sn.components;

import com.badlogic.gdx.math.Vector2;

public class locomotor extends component {
	
	private Vector2 vec = new Vector2();
	
	public void jump() {
		vec.set(0, 0.6f);
		ent.phy.applyImpulse(vec);
	}
	
	public void walkForward() {
		ent.phy.setSpeedX(3);
	}
	
	public void walkBackward() {
		ent.phy.setSpeedX(-3);
	}
	
	public void stopMoving() {
		ent.phy.setSpeedX(0);
	}
	
	public void runForward() {}
	public void flyForward() {}
}
