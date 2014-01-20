package com.sn.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;

public class EntityManager {
	public static final String TAG = EntityManager.class.getName();
	
	private static EntityManager TheEntityMgr = new EntityManager();
	private IntMap<Entity> ents = new IntMap<Entity>();
	private static ArrayMap<String, Method> prefabs = new ArrayMap<String, Method>();
	
	private EntityManager() {}
	
	public static EntityManager GetInstance() {
		return TheEntityMgr;
	}
	
	public Entity CreatePrefab(String name) {
		try {
			name = "com.sn.prefabs." + name;
			Method prefab = prefabs.get(name);
			if (prefab == null) {
				prefab = ClassReflection.getMethod(Class.forName(name), "create");
				prefabs.put(name, prefab);
			}
			
			Entity ent = (Entity) (prefab.invoke(null));
			if (ents.containsKey(ent.GetGUID())) {
				Gdx.app.error(TAG, "entity guid already exist(this should never be happened): " + ent.GetGUID());
			}
			ents.put(ent.GetGUID(), ent);
			return ent;
		} catch (Exception e) {
			Gdx.app.error(TAG, "create prefab failed: " + name);
		}
		return null;
	}
}
