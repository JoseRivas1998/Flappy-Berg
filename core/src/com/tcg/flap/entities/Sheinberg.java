package com.tcg.flap.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.tcg.flap.Game;
import com.tcg.flap.MyCamera;
import com.tcg.flap.managers.MyInput;

public class Sheinberg extends Entity {

	private float yVel;
	private Texture berg;
	
	private boolean shouldEnd;
	
	private boolean pipeCollide, center, pCenter;
	
	private Rectangle back;
	
	public Sheinberg(MyCamera cam) {
		super();
		back = new Rectangle();
		berg = new Texture("sheinberg.png");
		bounds.width = berg.getWidth() * .15f;
		bounds.height = berg.getHeight() * .15f;
		bounds.y = Game.CENTER.y;
		bounds.x = (cam.position.x * .5f) - (getWidth() * .5f);
		shouldEnd = false;
		pipeCollide = false;
	}

	public void update(MyCamera cam, Ground g, Array<Pipe> pipe) {
		
		bounds.x = (cam.getLeft() + (cam.viewportWidth * .25f)) - (getWidth() * .5f);
		
		back.x = bounds.x;
		back.y = bounds.y;
		back.width = 2;
		back.height = bounds.height;
		
		if(MyInput.isPressed(MyInput.SPACE)) {
			yVel = 7;
			Game.res.getSound("flap").play();
		}
		yVel -= .3f;
		
		for(Pipe p : pipe) {
			if(collidingWith(p.getBottom()) || collidingWith(p.getTop())) {
				pipeCollide = true;
				break;
			}
			if(back.overlaps(p.getCenter())) {
				center = true;
				break;
			} else {
				center = false;
			}
		}
		
		
		if(center && !pCenter) {
			Game.SCORE++;
			Game.res.getSound("meow").play();
		}
		
		shouldEnd = collidingWith(g) || pipeCollide;
		
		pCenter = center;
		
		bounds.y += yVel;
		
		if(bounds.y > Game.SIZE.y) {
			bounds.y = Game.SIZE.y;
		}
		
	}
	
	
	
	@Override
	public void draw(SpriteBatch sb) {
		sb.draw(berg, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void dispose() {
		berg.dispose();
	}

	public boolean isShouldEnd() {
		return shouldEnd;
	}

	public void setShouldEnd(boolean shouldEnd) {
		this.shouldEnd = shouldEnd;
	}

}
