package com.sn.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sn.slide.Sld;

public class physics extends component {
	private spineanimation spineanim;
	private Vector2 motorVelocity = new Vector2();
	private Body spinebody;
	
	public physics() {
		// create spinebody
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		spinebody = Sld.phyWorld.createBody(bodydef);
		
		PolygonShape boxpoly = new PolygonShape();
		boxpoly.setAsBox(20, 2);
		spinebody.createFixture(boxpoly, 1);
		boxpoly.dispose();
	}
	
	public void setSpineAnim(spineanimation spineanim) {
		this.spineanim = spineanim;
	}
	
	public void update(float delta) {
		if (spineanim != null) {
			Vector2 pos = spinebody.getPosition();
			spineanim.setxy(pos.x, pos.y);
		}
	}
	
	public void stop() {
		motorVelocity.set(0, 0);
	}
	
	public void setMotorVel(float speedx, float speedy) {
		motorVelocity.set(speedx, speedy);
	}
	
	public Vector2 getMotorVel() {
		return motorVelocity;
	}
	
	public void setxy(float x, float y) {
		spinebody.setTransform(x, y, spinebody.getAngle());
	}
}
