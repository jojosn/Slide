package com.sn.slide;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SlideMap {
	public static final String TAG = SlideMap.class.getName();
	
	private TiledMap map;
	private Array<TiledMapRenderer> renderers;
	private Array<OrthographicCamera> cameras;
	private Array<Vector2> speeds;
	private Array<int[]> layerIndexs;
	private int layerCount;
	private OrthographicCamera groundCamera;
	
	private int width;
	private int height;
	private int[] limits = {0,0,0,0};
	private Vector2 adjustedxy;
	
	public SlideMap(String fileName) {
		adjustedxy = new Vector2();
		
		renderers = new Array<TiledMapRenderer>();
		cameras = new Array<OrthographicCamera>();
		speeds = new Array<Vector2>();
		layerIndexs = new Array<int[]>();
		
		int sw = Gdx.graphics.getWidth();
		int sh = Gdx.graphics.getHeight();
		
		TiledMapRenderer renderer;
		OrthographicCamera camera;
		
		map = new TmxMapLoader().load(fileName);
		MapLayers layers = map.getLayers();
		layerCount = layers.getCount();
		int idx = 0;
		for (Iterator<MapLayer> it = layers.iterator(); it.hasNext();) {
			MapLayer layer = it.next();
			MapProperties properties = layer.getProperties();
			float speedx = 1f;
			float speedy = 1f;
			if (properties.containsKey("speed")) {
				speedx = speedy = Float.parseFloat( (String) properties.get("speed") );
			}
			if (properties.containsKey("speedx")) {
				speedx = Float.parseFloat( (String) properties.get("speedx") );
			}
			if (properties.containsKey("speedy")) {
				speedy = Float.parseFloat( (String) properties.get("speedy") );
			}
			speeds.add(new Vector2(speedx, speedy));
			
			camera = new OrthographicCamera(sw, sh);
			camera.translate(sw/2, sh/2);
			camera.update();
			cameras.add(camera);
			
			renderer = new OrthogonalTiledMapRenderer(map);
			renderer.setView(camera);
			renderers.add(renderer);
			
			layerIndexs.add(new int[]{idx++});
			
			if (properties.containsKey("ground")) {
				groundCamera = camera;
			}
		}
		
		// calc map limits
		MapProperties properties = map.getProperties();
		if (properties.containsKey("width")) {
			int mw = (Integer) properties.get("width");
			int tw = (Integer) properties.get("tilewidth");
			width = mw * tw;
		}
		if (properties.containsKey("height")) {
			int mh = (Integer) properties.get("height");
			int th = (Integer) properties.get("tileheight");
			height = mh * th;
		}
		limits[0] = sw/2;
		limits[1] = width - sw/2;
		limits[2] = sh/2;
		limits[3] = height - sh/2;
	}
	
	public void render() {
		for (int i = 0; i < layerCount; i++) {
			renderers.get(i).render(layerIndexs.get(i));
		}
	}
	
	public void dispose() {
		map.dispose();
	}
	
	private void adjustxy(float x, float y) {
		float gx = groundCamera.position.x;
		float gy = groundCamera.position.y;
		if (x+gx < limits[0]) {
			x = limits[0] - gx;
		}
		if (x+gx > limits[1]) {
			x = limits[1] - gx;
		}
		if (y+gy < limits[2]) {
			y = limits[2] - gy;
		}
		if (y+gy > limits[3]) {
			y = limits[3] - gy;
		}
		adjustedxy.set(x, y);
	}
	
	public void translate(float x, float y) {
		adjustxy(x, y);
		x = adjustedxy.x;
		y = adjustedxy.y;
		for (int i = 0; i < layerCount; i++) {
			Vector2 speed = speeds.get(i);
			OrthographicCamera camera = cameras.get(i);
			camera.translate(x*speed.x, y*speed.y);
			camera.update();
			renderers.get(i).setView(camera);
		}
	}
}
