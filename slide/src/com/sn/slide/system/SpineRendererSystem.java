package com.sn.slide.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sn.slide.Spine;
import com.sn.slide.component.PositionComponent;
import com.sn.slide.component.SpineComponent;
import com.sn.slide.component.VelocityComponent;

public class SpineRendererSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<SpineComponent> sm;
	
	private SpriteBatch batch;
	
	@SuppressWarnings("unchecked")
	public SpineRendererSystem() {
		super(Aspect.getAspectForOne(SpineComponent.class));
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		batch = new SpriteBatch();
	}

	@Override
	protected void process(Entity e) {
		SpineComponent spineCmp = sm.get(e);
		Spine spine = spineCmp.getSpine();
		PositionComponent position = e.getComponent(PositionComponent.class);
		if (position != null) {
			spine.setX(position.getX());
		}
		spine.render(batch, Gdx.graphics.getDeltaTime());
	}

	@Override
	protected void begin() {
		batch.begin();
	}

	@Override
	protected void end() {
		batch.end();
	}
}
