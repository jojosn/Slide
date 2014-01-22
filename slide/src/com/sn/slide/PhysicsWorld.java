package com.sn.slide;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PhysicsWorld {
	private World world;
	private Box2DDebugRenderer box2dRenderer;
	Body groundBody;
	
	public PhysicsWorld() {
		world = new World(new Vector2(0, -100), true);
		box2dRenderer = new Box2DDebugRenderer();
		createWorld();
	}
	
	public Body createBody(BodyDef def) {
		return world.createBody(def);
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
	
	private void createWorld() {
		PolygonShape polyshape = new PolygonShape();
		// create map physics
		MapLayers layers = Sld.sldmap.map.getLayers();
		MapLayer layer = layers.get("physics");
		MapObjects objs = layer.getObjects();
		for (Iterator<MapObject> itr = objs.iterator(); itr.hasNext(); ) {
			RectangleMapObject obj = (RectangleMapObject) itr.next();
			Rectangle rect = obj.getRectangle();
			
			BodyDef bodydef = new BodyDef();
			bodydef.type = BodyType.StaticBody;
			bodydef.position.x = rect.x + rect.width/2;
			bodydef.position.y = rect.y + rect.height/2;
			Body body = world.createBody(bodydef);
			polyshape.setAsBox(rect.width/2, rect.height/2);
			body.createFixture(polyshape, 1);
		}
		polyshape.dispose();

		PolygonShape boxPoly = new PolygonShape();
		boxPoly.setAsBox(10, 10);

		// Next we create the 50 box bodies using the PolygonShape we just
		// defined. This process is similar to the one we used for the ground
		// body. Note that we reuse the polygon for each body fixture.
		for (int i = 0; i < 0; i++) {
			// Create the BodyDef, set a random position above the
			// ground and create a new body
			BodyDef boxBodyDef = new BodyDef();
			boxBodyDef.type = BodyType.DynamicBody;
			boxBodyDef.position.x = -240 + (float)(Math.random() * 480);
			boxBodyDef.position.y = 100 + (float)(Math.random() * 100);
			Body boxBody = world.createBody(boxBodyDef);

			boxBody.createFixture(boxPoly, 1);
		}

		// we are done, all that's left is disposing the boxPoly
		boxPoly.dispose();
	}
	
	public void render(Camera camera) {
		float delta = Gdx.graphics.getDeltaTime();
		float remaining = delta;
		while (remaining > 0) {
			float d = Math.min(0.016f, remaining);
			world.step(d, 8, 3);
			remaining -= d;
		}
		
		box2dRenderer.render(world, camera.combined);
	}
	
	public void dispose() {
		
	}
}
