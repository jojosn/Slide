package com.sn.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.sn.components.Component;
import com.sn.stategraphs.StateGraph;

public class Entity { 
	public static final String TAG = Entity.class.getName();
	
	private static ArrayMap<String, Class<?>> Components = new ArrayMap<String, Class<?>>();
	private static ArrayMap<String, Class<?>> StateGraphs = new ArrayMap<String, Class<?>>();
	
	private static int GUID_COUNTER = 1;
	private int GUID;
	private String name;
	private boolean valid;
	private ArrayMap<String, Boolean> tags;
	private ArrayMap<String, Component> components;
	private StateGraph sg;
	//private EventList eventListener;
	//private Array<Entity> children;
	
	public Entity(String name) {
		this.name = name;
		GUID = GUID_COUNTER++;
		tags = new ArrayMap<String, Boolean>();
	}
	
	public int GetGUID() {
		return GUID;
	}
	
	public String GetName() {
		return name;
	}
	
	public void AddTag(String tag) {
		tags.put(tag, true);
	}
	
	public void RemoveTag(String tag) {
		tags.removeKey(tag);
	}
	
	public boolean HasTag(String tag) {
		return tags.containsKey(tag);
	}
	
	public boolean IsValid() {
		return valid;
	}
	
	public void SetValid(boolean valid) {
		this.valid = valid;
	}
	
	public void PushEvent(String event) {
		
	}
	
	public void SetStateGraph(String name) {
		name = "com.sn.stategraphs." + name;
		try {
			Class<?> c = StateGraphs.get(name);
			if (c == null) {
				c = Class.forName(name);
				StateGraphs.put(name, c);
			}
			this.sg = (StateGraph) c.newInstance();
			this.sg.Go2FirstState();
		} catch (Exception e) {
			Gdx.app.error(TAG, "stategraph is not well defined: " + name);
		}
	}
	
	public void AddComponent(String name) {
		name = "com.sn.components." + name;
		if (components.containsKey(name)) {
			Gdx.app.error(TAG, "component already added: " + name);
			return;
		}
		
		try {
			Class<?> c = Components.get(name);
			if (c == null) {
				c = Class.forName(name);
				Components.put(name, c);
			}
			Component comp = (Component) c.newInstance();
			components.put(name, comp);
		} catch (Exception e) {
			Gdx.app.error(TAG, "component is not well defined: " + name);
		}
		
		/*
		try {
			name = "com.sn.components." + name;
			Class<?> comp = Class.forName(name);
			
			Method prefab = prefabs.get(name);
			if (prefab == null) {
				prefab = ClassReflection.getMethod(Class.forName(name), "create");
				prefabs.put(name, prefab);
			}
			
			Entity ent = (Entity) (prefab.invoke(null));
			if (ents.containsKey(ent.GetGUID())) {
				Gdx.app.error(TAG, "entity guid already exist(this should never be happened): " + ent.GetGUID());
				throw new Exception();
			}
			ents.put(ent.GetGUID(), ent);
			return ent;
		} catch (Exception e) {
			Gdx.app.error(TAG, "prefab is not well defined: " + name);
			e.printStackTrace();
		}
		return null;
		*/
		
	}
}
