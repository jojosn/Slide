package com.sn.slide.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sn.slide.component.AnimComponent;
import com.sn.slide.component.PositionComponent;

public class SpriteRendererSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<AnimComponent> am;
	
	private SpriteBatch batch;
	
	@SuppressWarnings("unchecked")
	public SpriteRendererSystem() {
		super(Aspect.getAspectForOne(AnimComponent.class));
	}

	@Override
	protected void begin() {
		batch.begin();
	}

	@Override
	protected void end() {
		batch.end();
	}

	@Override
	protected void initialize() {
		super.initialize();
		batch = new SpriteBatch();
	}

	@Override
	protected void process(Entity e) {
		AnimComponent anim = am.get(e);
		PositionComponent pm = e.getComponent(PositionComponent.class);
		anim.getAnimSprite().setPosition(pm.getX(), pm.getY());
		anim.getAnimSprite().draw(batch);
	}

}
