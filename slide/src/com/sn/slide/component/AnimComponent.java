package com.sn.slide.component;

import com.artemis.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class AnimComponent extends Component {
	private Sprite animSprite;
	
	public AnimComponent(String pngName) {
		Texture tex = new Texture(Gdx.files.internal(pngName));
		animSprite = new Sprite(tex);
		animSprite.setScale(0.3f);
		animSprite.setRotation(30);
	}

	public Sprite getAnimSprite() {
		return animSprite;
	}

	public void setAnimSprite(Sprite animSprite) {
		this.animSprite = animSprite;
	}
}
