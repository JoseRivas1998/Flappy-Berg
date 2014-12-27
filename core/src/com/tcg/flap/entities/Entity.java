package com.tcg.flap.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

	public Rectangle bounds;
	
	public Entity() {
		bounds = new Rectangle();
	}
	
	public abstract void draw(SpriteBatch sb);

	public abstract void dispose();
	

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public float getX() {
		return bounds.x;
	}

	public void setX(float x) {
		bounds.x = x;
	}

	public float getY() {
		return bounds.y;
	}

	public void setY(float y) {
		bounds.y = y;
	}

	public float getWidth() {
		return bounds.width;
	}

	public void setWidth(float width) {
		bounds.width = width;
	}

	public float getHeight() {
		return bounds.height;
	}

	public void setHeight(float height) {
		bounds.height = height;
	}
	
	public boolean collidingWith(Entity e) {
		return this.bounds.overlaps(e.getBounds());
	}
	
	public boolean collidingWith(Rectangle r) {
		return this.bounds.overlaps(r);
	}
}
