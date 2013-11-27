package com.sn.slide.component;

import com.artemis.Component;

public class PositionComponent extends Component {
	private float x;
	private float y;
	
	public PositionComponent() {
		
	}
	public PositionComponent(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
}
