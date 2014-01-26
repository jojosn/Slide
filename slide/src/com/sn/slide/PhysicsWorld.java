package com.sn.slide;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class PhysicsWorld {
	private World world;
	private Box2DDebugRenderer box2dRenderer;
	private OrthographicCamera camera;
	public static final float scale = 40.0f;
	private Vector2 worldSize;
	private Vector2 cameraSize;
	private float worldw;
	private float worldh;
	
	Body groundBody;
	
	public PhysicsWorld() {
		world = new World(new Vector2(0, -20f), true);
		box2dRenderer = new Box2DDebugRenderer();
		worldSize = new Vector2();
		cameraSize = new Vector2();
		createWorld();
	}
	
	public Body createBody(BodyDef def) {
		return world.createBody(def);
	}
	
	private void createWorld() {
		PolygonShape polyshape = new PolygonShape();
		ChainShape chainshape = new ChainShape();
		
		float mapw = Sld.sldmap.getWidth();
		float maph = Sld.sldmap.getHeight();
		worldSize.set(mapw/scale, maph/scale);
		
		float screenw = Gdx.graphics.getWidth();
		float screenh = Gdx.graphics.getHeight();
		cameraSize.set(screenw/scale, screenh/scale);
		
		camera = new OrthographicCamera(cameraSize.x, cameraSize.y);
		camera.translate(cameraSize.x/2f, cameraSize.y/2f);
		camera.update();
		
		Vector2[] vecs = new Vector2[5];
		vecs[0] = new Vector2(0, 0);
		vecs[1] = new Vector2(0, worldSize.y);
		vecs[2] = new Vector2(worldSize.x, worldSize.y);
		vecs[3] = new Vector2(worldSize.x,0);
		vecs[4] = new Vector2(0,0);
		chainshape.createChain(vecs);
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.StaticBody;
		Body bd = world.createBody(bodydef);
		bd.createFixture(chainshape, 1);

		
		/*
		polyshape.setAsBox(0.2f, 0.2f);
		for (int i = 0; i < 20; i++) {
			bodydef = new BodyDef();
			bodydef.type = BodyType.DynamicBody;
			bodydef.position.set((float) (5*Math.random()*5), 8f);
			world.createBody(bodydef).createFixture(polyshape, 1);
		}
		*/
		
		// create map physics
		MapLayers layers = Sld.sldmap.map.getLayers();
		MapLayer layer = layers.get("physics");
		MapObjects objs = layer.getObjects();
		for (Iterator<MapObject> itr = objs.iterator(); itr.hasNext(); ) {
			RectangleMapObject obj = (RectangleMapObject) itr.next();
			Rectangle rect = obj.getRectangle();
			
			bodydef = new BodyDef();
			bodydef.type = BodyType.StaticBody;
			bodydef.position.x = (rect.x + rect.width/2f)/scale;
			bodydef.position.y = (rect.y + rect.height/2f)/scale;
			Body body = world.createBody(bodydef);
			polyshape.setAsBox(rect.width/2f/scale, rect.height/2f/scale);
			
			FixtureDef fixdef = new FixtureDef();
			fixdef.shape = polyshape;
			fixdef.density = 1;
			fixdef.friction = 0;
			body.createFixture(fixdef);
		}
		polyshape.dispose();
	}
	
	public void render() {
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
		world.dispose();
	}
	
	public void translate(Vector2 vec) {
		vec.x = vec.x / scale;
		vec.y = vec.y / scale;
		camera.translate(vec);
		camera.update();
	}
}
