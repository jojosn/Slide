package com.sn.components;

import com.badlogic.gdx.math.Vector2;
import com.sn.slide.Sld;
import com.sn.slide.Spine;

public class spineanimation extends component {
	private Spine spine;
	private Vector2 vec = new Vector2();
	
	public void setSpine(String path) {
		spine = new Spine(path);
		spine.playAnimationLoop("idle");
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
	
	public void idle() {
		spine.playAnimationLoop("idle");
	}
	
	public void walk() {
		spine.playAnimationLoop("walk");
	}
	
	public void jump() {
		spine.playAnimation("jump", "idle");
	}
	
	public void kick() {
		spine.playAnimation("kick", "idle");
	}
	
	public void punch() {
		spine.playAnimation("punch", "idle");
	}
	
	public Vector2 getPosition() {
		vec.set(spine.getX(), spine.getY());
		return vec;
	}
}
