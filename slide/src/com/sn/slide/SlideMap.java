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
import com.badlogic.gdx.utils.Array;

public class SlideMap {
	public static final String TAG = SlideMap.class.getName();
	
	private TiledMap map;
	private Array<TiledMapRenderer> renderers;
	private Array<OrthographicCamera> cameras;
	
	private TiledMapRenderer renderer;
	private TiledMapRenderer renderer1;
	private OrthographicCamera camera;
	private OrthographicCamera camera1;
	
	private final int[] bgLayer = {0};
	private final int[] farLayer = {1};
	private final int[] farLayer1 = {2};
	
	public SlideMap(String fileName) {
		int sw = Gdx.graphics.getWidth();
		int sh = Gdx.graphics.getHeight();
		
		map = new TmxMapLoader().load(fileName);
		MapLayers layers = map.getLayers();
		for (Iterator<MapLayer> it = layers.iterator(); it.hasNext();) {
			MapLayer layer = it.next();
			MapProperties properties = layer.getProperties();
			if (properties.containsKey("speed")) {
				Gdx.app.log(TAG, (String) properties.get("speed"));
			}
		}
		
		MapProperties properties = map.getProperties();
		for (Iterator<String> it = properties.getKeys(); it.hasNext();) {
			String keystr = it.next();
			Gdx.app.log(TAG, keystr);
		}
		
		/*
		camera = new OrthographicCamera(sw, sh);
		camera.translate(sw/2, sh/2);
		camera.update();
		camera1 = new OrthographicCamera(sw, sh);
		camera1.translate(sw/2, sh/2);
		camera1.update();
		
		
		renderer = new OrthogonalTiledMapRenderer(map);
		renderer.setView(camera);
		renderer1 = new OrthogonalTiledMapRenderer(map);
		renderer1.setView(camera1);
		*/
	}
	
	public void render() {
		//renderer.render();
		renderer.render(farLayer);
		renderer1.render(bgLayer);
	}
	
	public void dispose() {
		map.dispose();
	}
	
	public void translate(float x, float y) {
		camera.translate(x, y);
		camera.update();
		renderer.setView(camera);
		
		camera1.translate(x*0.5f, y*0.5f);
		camera1.update();
		renderer1.setView(camera1);
	}
}
