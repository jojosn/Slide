package com.sn.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Animation;
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
		json.setScale(0.4f);
		SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(spinePath+".json"));
		animation = skeletonData.findAnimation("walk");
		skeleton = new Skeleton(skeletonData);
	}

	public void render(float delta) {
		float lastTime = time;
		time += delta;
		
		batch.begin();
		
		batch.setProjectionMatrix(Sld.camera.combined);
		events.clear();
		animation.apply(skeleton, lastTime, time, true, events);
		skeleton.updateWorldTransform();
		if (events.size > 0) System.out.println(events);
		skeletonRenderer.draw(batch, skeleton);
		
		batch.end();

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
		batch.dispose();
	}
}
