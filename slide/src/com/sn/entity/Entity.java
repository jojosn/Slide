package com.sn.entity;

import com.badlogic.gdx.utils.ArrayMap;

public class Entity {//abstract 
	private static int GUID_COUNTER = 1;
	
	private int GUID;
	private String name;
	private boolean valid;
	private ArrayMap<String, Boolean> tags;
	//private Array<Component> components;
	//private StateGraph stateGraph;
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
	
	public void SetStateGraph() {
		
	}
	
	public void AddComponent() {
		
	}
}
