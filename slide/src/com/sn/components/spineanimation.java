package com.sn.components;

import com.sn.slide.Spine;

public class spineanimation extends component {
	private Spine spine;
	
	public void setSpine(String path) {
		spine = new Spine(path);
		spine.playAnimationLoop("walk");
	}
	
	public void render(float delta) {
		spine.render(delta);
	}
	
	public void setxy(float x, float y) {
		spine.setX(x);
		spine.setY(y);
	}
	
	public void setx(float x) {
		spine.setX(x);
	}
	
	public void sety(float y) {
		spine.setY(y);
	}
	
	public void jump() {
		spine.playAnimation("jump", "walk");
	}
	
	public void kick() {
		spine.playAnimation("kick", "walk");
	}
	
	public void punch() {
		spine.playAnimation("punch", "walk");
	}
}
