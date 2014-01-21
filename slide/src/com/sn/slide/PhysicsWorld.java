package com.sn.slide;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsWorld {
	private World world;
	private Box2DDebugRenderer box2dRenderer;
	
	public PhysicsWorld() {
		world = new World(new Vector2(0, -10), true);
		box2dRenderer = new Box2DDebugRenderer();
	}
	
	public void createbody() {
		/*
			BodyDef
			body = world.createBody(BodyDef)
			
			shape
			FixtureDef
			fixtureDef.shape = shape
			body.createFixture(fixtureDef)
			shape.dispose()
		 */
	}
}
