package com.sn.slide.component;

import com.artemis.Component;
import com.sn.slide.Spine;

public class SpineComponent extends Component {
	private Spine spine;
	
	public SpineComponent(String spinePath) {
		spine = new Spine(spinePath);
	}

	public Spine getSpine() {
		return spine;
	}

	public void setSpine(Spine spine) {
		this.spine = spine;
	}
}
