package com.sn.slide;

import com.badlogic.gdx.Gdx;
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
	private SkeletonRenderer skeletonRenderer;
	private SkeletonRendererDebug debugRenderer;
	
	private TextureAtlas atlas;
	private Skeleton skeleton;
	AnimationState animstate;
	Animation animation;
	float time;
	//private AnimationState animState;
	private SpriteBatch batch;
	private Array<Event> events = new Array<Event>();
	
	/*
	static class Box2dAttachment extends RegionAttachment {
		Body body;

		public Box2dAttachment (String name) {
			super(name);
		}
	}
	*/
	public Spine(String spinePath) {
		skeletonRenderer = new SkeletonRenderer();
		batch = new SpriteBatch();
		debugRenderer = new SkeletonRendererDebug();
		//debugRenderer.setRegionAttachments(false);
		//debugRenderer.setBones(false);
		
		atlas = new TextureAtlas(Gdx.files.internal(spinePath+".atlas"));
		/*
		AtlasAttachmentLoader atlasLoader = new AtlasAttachmentLoader(atlas) {
			public RegionAttachment newRegionAttachment (Skin skin, String name, String path) {
				Box2dAttachment attachment = new Box2dAttachment(name);
				AtlasRegion region = atlas.findRegion(attachment.getName());
				if (region == null) throw new RuntimeException("Region not found in atlas: " + attachment);
				attachment.setRegion(region);
				return attachment;
			}
		};
		*/
		
		SkeletonJson json = new SkeletonJson(atlas);
		json.setScale(0.25f);
		SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(spinePath+".json"));
		animation = skeletonData.findAnimation("walk");
		skeleton = new Skeleton(skeletonData);
		
		skeleton = new Skeleton(skeletonData);
		AnimationStateData stateData = new AnimationStateData(skeletonData);
		stateData.setMix("walk", "jump", 0.1f);
		stateData.setMix("jump", "walk", 0.1f);
		stateData.setMix("kick", "walk", 0.1f);
		stateData.setMix("walk", "kick", 0.1f);
		stateData.setMix("punch", "walk", 0.1f);
		stateData.setMix("walk", "punch", 0.1f);
		animstate = new AnimationState(stateData);
	}
	
	public void playAnimationLoop(String name) {
		animstate.setAnimation(0, name, true);
		//animation = skeletonData.findAnimation(name);
	}
	
	public void playAnimation(String name1, String name2) {
		animstate.setAnimation(0, name1, false);
		animstate.addAnimation(0, name2, true, 0);
	}
	
	public void render(float delta) {		
		batch.begin();
		batch.setProjectionMatrix(Sld.camera.combined);
		animstate.update(delta);
		animstate.apply(skeleton);
		skeleton.updateWorldTransform();
		skeletonRenderer.draw(batch, skeleton);
		
		/*
		batch.setProjectionMatrix(Sld.camera.combined);
		events.clear();
		animation.apply(skeleton, lastTime, time, true, events);
		skeleton.updateWorldTransform();
		if (events.size > 0) System.out.println(events);
		skeletonRenderer.draw(batch, skeleton);
		*/
		batch.end();

		//debugRenderer.draw(skeleton);
	}
	
	public void setX(float x) {
		skeleton.setX(x);
	}
	
	public void setY(float y) {
		skeleton.setY(y);
	}
	
	public float getX() {
		return skeleton.getX();
	}
	
	public float getY() {
		return skeleton.getY();
	}
	
	public void dispose() {
		atlas.dispose();
		batch.dispose();
	}
}
