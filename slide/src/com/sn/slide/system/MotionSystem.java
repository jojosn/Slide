package com.sn.slide.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.sn.slide.component.SpineComponent;
import com.sn.slide.component.VelocityComponent;

public class MotionSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<VelocityComponent> vm;
	
	private int direction;
	private static final int DIR_LEFT = 0;
	private static final int DIR_RIGHT = 1;
	
	@SuppressWarnings("unchecked")
	public MotionSystem() {
		super(Aspect.getAspectForOne(VelocityComponent.class));
		direction = DIR_RIGHT;
	}

	@Override
	protected void process(Entity e) {
		/*
		VelocityComponent velocity = vm.get(e);
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			float xv = velocity.getXvelocity();
			xv = Math.abs(xv) * -1;
			velocity.setXvelocity(-100);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			float xv = velocity.getXvelocity();
			xv = Math.abs(xv);
			velocity.setXvelocity(100);
		} else {
			velocity.setXvelocity(0);
		}
		*/
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			SpineComponent spine = e.getComponent(SpineComponent.class);
			if (spine != null) {
				spine.getSpine().playAnimation("jump");
			}
		}
	}
}
