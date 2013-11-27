package com.sn.slide.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.sn.slide.component.PositionComponent;
import com.sn.slide.component.VelocityComponent;

public class MovementSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<PositionComponent> pm;
	@Mapper ComponentMapper<VelocityComponent> vm;
	
	@SuppressWarnings("unchecked")
	public MovementSystem() {
		super(Aspect.getAspectForAll(PositionComponent.class, VelocityComponent.class));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void process(Entity e) {
		/*
		// TODO Auto-generated method stub
		PositionComponent position = pm.get(e);
		VelocityComponent velocity = vm.get(e);
		
		float deltax = velocity.getXvelocity() * world.getDelta();
		float deltay = velocity.getYvelocity() * world.getDelta();
		position.setX(position.getX() + deltax);
		position.setY(position.getY() + deltay);
		*/
	}
	
}
