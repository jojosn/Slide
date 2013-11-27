package com.sn.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;

public class Spine {
	private SkeletonRenderer renderer;
	//private SkeletonRendererDebug debugRenderer;
	
	private TextureAtlas atlas;
	private Skeleton skeleton;
	private AnimationState animState;
	
	//private Array<Event> events = new Array<Event>();
	//private Animation animation;
	//float time;
	
	public Spine(String spinePath) {
		renderer = new SkeletonRenderer();
		//debugRenderer = new SkeletonRendererDebug();
		
		atlas = new TextureAtlas(Gdx.files.internal(spinePath+".atlas"));
		SkeletonJson json = new SkeletonJson(atlas);
		json.setScale(.3f);
		SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(spinePath+".json"));
		AnimationStateData stateData = new AnimationStateData(skeletonData);
		stateData.setMix("walk", "jump", 0.2f);
		stateData.setMix("jump", "walk", 0.4f);
		stateData.setMix("jump", "jump", 0.2f);
		animState = new AnimationState(stateData);
		animState.setAnimation(0, "walk", true);
		//animState.addAnimation(0, "jump", false, 0);
		//animState.addAnimation(0, "walk", true, 0);
		//animation = skeletonData.findAnimation("walk");
		
		skeleton = new Skeleton(skeletonData);
		skeleton.setToSetupPose();
		skeleton.setX(50);
		skeleton.setY(95);
	}
	
	public void playAnimation(String animationName) {
		animState.setAnimation(0, animationName, false);
		animState.addAnimation(0, "walk", true, 0);
	}
	
	public void render(SpriteBatch batch, float dt) {
		/*
		float lastTime = time;
		time += dt;
		events.clear();
		animation.apply(skeleton, lastTime, time, true, events);
		if (events.size > 0) System.out.println(events);
		*/
	
		skeleton.update(dt);
		skeleton.updateWorldTransform();
		animState.update(dt);
		animState.apply(skeleton);
		renderer.draw(batch, skeleton);
		//debugRenderer.draw(skeleton);
	}
	
	public void setX(float x) {
		skeleton.setX(x);
	}
	
	public void setY(float y) {
		skeleton.setY(y);
	}
	
	public void dispose() {
		atlas.dispose();
	}
}
