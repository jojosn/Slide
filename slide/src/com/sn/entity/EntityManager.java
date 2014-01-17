package com.sn.entity;

import com.badlogic.gdx.utils.IntMap;

public class EntityManager {
	public static final String TAG = EntityManager.class.getName();
	
	private static EntityManager TheEntityManager = new EntityManager();
	private IntMap<Entity> ents = new IntMap<Entity>();
	
	private EntityManager() {}
	
	
}
