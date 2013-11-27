package com.sn.slide;

import com.artemis.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.sn.slide.component.PositionComponent;

public class ParallaxMap {
	
	class ParallaxCamera extends OrthographicCamera {
		Matrix4 parallaxView = new Matrix4();
		Matrix4 parallaxCombined = new Matrix4();
		Vector3 tmp = new Vector3();
		Vector3 tmp2 = new Vector3();

		public ParallaxCamera (float viewportWidth, float viewportHeight) {
			super(viewportWidth, viewportHeight);
		}

		public Matrix4 calculateParallaxMatrix (float parallaxX, float parallaxY) {
			update();
			tmp.set(position);
			tmp.x *= parallaxX;
			tmp.y *= parallaxY;

			parallaxView.setToLookAt(tmp, tmp2.set(tmp).add(direction), up);
			parallaxCombined.set(projection);
			Matrix4.mul(parallaxCombined.val, parallaxView.val);
			
			return parallaxCombined;
		}
	}
	
	class OrthoCamController extends InputAdapter {
		final OrthographicCamera camera;
		final Vector3 curr = new Vector3();
		final Vector3 last = new Vector3(-1, -1, -1);
		final Vector3 delta = new Vector3();

		public OrthoCamController (OrthographicCamera camera) {
			this.camera = camera;
		}

		@Override
		public boolean touchDragged (int x, int y, int pointer) {
			System.out.println("x = "+x+", y="+y);
			camera.unproject(curr.set(x, y, 0));
			if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
				camera.unproject(delta.set(last.x, last.y, 0));
				delta.sub(curr);
				camera.position.add(delta.x, delta.y, 0);
			}
			last.set(x, y, 0);
			return false;
		}

		@Override
		public boolean touchUp (int x, int y, int pointer, int button) {
			last.set(-1, -1, -1);
			return false;
		}
	}
	
	private TiledMap map;
	private TiledMapRenderer renderer; 
	private ParallaxCamera camera;
	//private SpriteBatch batch;
	private static final String tiledMapPath = "map/skite.tmx";
	private OrthoCamController controller;
	
	private final int[] bgLayer = {0};
	private final int[] farLayer = {1};
	private final int[] nearLayer = {2};
	private final int[] groundLayer = {3};
	private Entity entity;
	
	float width;
	float height;
	
	public ParallaxMap(Entity ent) {
		this.entity = ent;
		float viewwidth = Gdx.graphics.getWidth();
		float viewheight = Gdx.graphics.getHeight();
		camera = new ParallaxCamera(viewwidth, viewheight);
		camera.setToOrtho(false);
		controller = new OrthoCamController(camera);
		Gdx.input.setInputProcessor(controller);
		map = new TmxMapLoader().load(tiledMapPath);
		renderer = new OrthogonalTiledMapRenderer(map);
		renderer.setView(camera);
		//batch = new SpriteBatch();
		
		
		width = camera.viewportWidth * camera.zoom;
		height = camera.viewportHeight * camera.zoom;
		//viewBounds.set(camera.position.x - width / 2, camera.position.y - height / 2, width, height);
	}
	
	public void render() {
		float dt = Gdx.graphics.getDeltaTime();
		//camera.position.x += (100*dt);
		//adjustCamera(dt);
		//camera.translate(1,0);
		
		
		//camera.update();
		renderer.setView(camera.calculateParallaxMatrix(0, 0), camera.position.x - width / 2, camera.position.y - height / 2, width, height);
		camera.update();
		renderer.render(bgLayer);
		
		renderer.setView(camera.calculateParallaxMatrix(0.5f, 0), camera.position.x - width / 2, camera.position.y - height / 2, width, height);
		camera.update();
		renderer.render(farLayer);
		/*
		camera.translate(1, 0);
		camera.update();
		renderer.setView(camera);
		renderer.render(farLayer);
		
		camera.translate(2, 0);
		camera.update();
		renderer.setView(camera);
		renderer.render(nearLayer);
		
		camera.translate(3, 0);
		camera.update();
		renderer.setView(camera);
		renderer.render(groundLayer);
		*/
		
		//PositionComponent position = entity.getComponent(PositionComponent.class);
		//camera.lookAt(position.getX(), position.getY(), 0);
		//camera.position.x = position.getX();
		//camera.update();
		//renderer.setView(camera);
		
		//batch.setProjectionMatrix(camera.calculateParallaxMatrix(0, 0));
		//batch.disableBlending();
		//batch.begin();
		///renderer.render(bgLayer);
		//batch.end();
		//batch.enableBlending();
		
		//batch.setProjectionMatrix(camera.calculateParallaxMatrix(0.3f, 0));
		//batch.begin();
		//renderer.render(farLayer);
		//batch.end();
		
		//batch.setProjectionMatrix(camera.calculateParallaxMatrix(0.5f, 0));
		//batch.begin();
		//renderer.render(nearLayer);
		//batch.end();
		
		//batch.setProjectionMatrix(camera.calculateParallaxMatrix(1f, 0));
		//batch.begin();
		//renderer.render(groundLayer);
		//batch.end();
	}
	
	public void dispose() {
		//batch.dispose();
		map.dispose();
	}
	
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	public void adjustCamera(float dt) {
		float oldx = camera.position.x;
		float y = camera.position.y;
		float x = oldx + (100*dt);
		System.out.println("newx = " + x);
		camera.translate(x, y);
		/*
		camera.unproject(curr.set(x, y, 0));
		if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
			camera.unproject(delta.set(last.x, last.y, 0));
			delta.sub(curr);
			camera.position.add(delta.x, delta.y, 0);
		}
		last.set(x, y, 0);
		*/
	}
}
