package com.sn.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.sn.slide.PhysicsWorld;
import com.sn.slide.Sld;

public class physics extends component {
	public static final String TAG = physics.class.getName();
	
	private spineanimation spineanim;
	private Vector2 motorVelocity = new Vector2();
	private Body spinebody;
	private Vector2 tmpVec = new Vector2();
	
	public physics() {
		// create spinebody
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		spinebody = Sld.phyWorld.createBody(bodydef);
		spinebody.setFixedRotation(true);
		
		PolygonShape boxpoly = new PolygonShape();
		Vector2[] vecs = new Vector2[2];
		vecs[0] = new Vector2(0,0);
		vecs[1] = new Vector2(0.5f,0);
		//boxpoly.set(vecs);
		boxpoly.setAsBox(0.1f, 0.05f);
		spinebody.createFixture(boxpoly, 1);
		boxpoly.dispose();
	}
	
	public void setSpineAnim(spineanimation spineanim) {
		this.spineanim = spineanim;
	}
	
	public void update(float delta) {
		if (spineanim != null) {
			Vector2 pos = spinebody.getPosition();
			
			Vector2 oldAnimPos = spineanim.getPosition();
			float oldx = oldAnimPos.x;
			float oldy = oldAnimPos.y;
			
			spineanim.setxy(pos.x*PhysicsWorld.scale, pos.y*PhysicsWorld.scale);
			Vector2 curAnimPos = spineanim.getPosition();
			
			if (Math.abs(curAnimPos.x-Sld.camera.position.x) < 2 || Math.abs(oldx-Sld.camera.position.x) < 8) {
				float dx = curAnimPos.x - oldx;
				float dy = curAnimPos.y - oldy;
				if (dx != 0 || dy != 0) {
					Sld.sldmap.translate(dx, dy);
				}
			}
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
		spinebody.setTransform(x/PhysicsWorld.scale, y/PhysicsWorld.scale, spinebody.getAngle());
		spineanim.setxy(x, y);
	}
	
	public void applyImpulse(Vector2 vec) {
		Gdx.app.log(TAG, "apply impulse x="+vec.x+" y="+vec.y);
		spinebody.applyLinearImpulse(vec, spinebody.getWorldCenter(), true);
	}
	
	public void applyForce(Vector2 vec) {
		spinebody.applyForceToCenter(vec, true);
	}
	
	public void setLinerVelocity(Vector2 vec) {
		spinebody.setLinearVelocity(vec);
	}
	
	public void setSpeedX(float x) {
		Vector2 vec = spinebody.getLinearVelocity();
		vec.set(x, vec.y);
		spinebody.setLinearVelocity(vec);
	}
	
	public void setSpeedY(float y) {
		Vector2 vec = spinebody.getLinearVelocity();
		vec.set(vec.x, y);
		spinebody.setLinearVelocity(vec);
	}
	
	public Vector2 getPosition() {
		Vector2 pos = spinebody.getPosition();
		tmpVec.set(pos.x*PhysicsWorld.scale, pos.y*PhysicsWorld.scale);
		return tmpVec;
	}
}
