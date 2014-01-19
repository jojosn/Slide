package com.sn.prefabs;

import com.sn.entity.Entity;

public class cat {
	public static final String TAG = cat.class.getName();
	private static cat instance = null;
	
	private cat() {}
	public cat getInstance() {
		if (instance == null) {
			instance = new cat();
		}
		return instance;
	}
	
	public static Entity create() {
		Entity ent = new Entity("cat");
		return ent;
	}
}
