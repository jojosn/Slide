package com.sn.slide;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class SlideMap {
	public static final String TAG = SlideMap.class.getName();
	
	public TiledMap map;
	private Array<TiledMapRenderer> renderers;
	private Array<OrthographicCamera> cameras;
	private Array<Vector2> speeds;
	private Array<int[]> layerIndexs;
	private OrthographicCamera groundCamera;
	
	private float width;
	private float height;
	private float[] limits = {0,0,0,0};
	private Vector2 adjustedvec;
	
	public SlideMap(String fileName) {
		adjustedvec = new Vector2();
		
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
		int idx = 0;
		for (Iterator<MapLayer> it = layers.iterator(); it.hasNext();) {
			MapLayer layer = it.next();
			MapProperties properties = layer.getProperties();
			if (layer.getName().equals("physics")) {
				continue;
			} else {
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
		for (int i = 0; i < layerIndexs.size; i++) {
			renderers.get(i).render(layerIndexs.get(i));
		}
	}
	
	public void dispose() {
		map.dispose();
	}
	
	private void adjustxy(float x, float y) {
		/*
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
		*/
		adjustedvec.set(x, y);
	}
	
	public void translate(float x, float y) {
		adjustxy(x, y);
		x = adjustedvec.x;
		y = adjustedvec.y;
		for (int i = 0; i < renderers.size; i++) {
			Vector2 speed = speeds.get(i);
			OrthographicCamera camera = cameras.get(i);
			camera.translate(x*speed.x, y*speed.y);
			camera.update();
			renderers.get(i).setView(camera);
		}
		Sld.phyWorld.translate(adjustedvec);
	}
	
	public OrthographicCamera getGroundCamera() {
		return groundCamera;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
