package com.sn.slide.component;

import com.artemis.Component;

public class VelocityComponent extends Component {
	private float xvelocity;
	private float yvelocity;
	
	public VelocityComponent(float xv, float yv) {
		this.xvelocity = xv;
		this.yvelocity = yv;
	}

	public float getXvelocity() {
		return xvelocity;
	}

	public void setXvelocity(float xvelocity) {
		this.xvelocity = xvelocity;
	}

	public float getYvelocity() {
		return yvelocity;
	}

	public void setYvelocity(float yvelocity) {
		this.yvelocity = yvelocity;
	}

	
}
