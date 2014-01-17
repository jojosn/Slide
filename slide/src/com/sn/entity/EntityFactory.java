package com.sn.entity;

import com.badlogic.gdx.utils.IntMap;

public class EntityFactory {
	public static final String TAG = EntityFactory.class.getName();
	
	private static EntityFactory TheEntityFactory = new EntityFactory();
	private IntMap<Entity> ents = new IntMap<Entity>();
	
	private EntityFactory() {
		
	}
	
	public static EntityFactory GetInstance() {
		return TheEntityFactory;
	}
	
	public Entity CreatePrefab(String name) {
		Entity ent = new Entity(name);
		ents.put(ent.GetGUID(), ent);
		return ent;
	}
}
