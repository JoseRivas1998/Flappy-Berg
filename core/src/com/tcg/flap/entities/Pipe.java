package com.tcg.flap.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.tcg.flap.Constants;
import com.tcg.flap.Game;
import com.tcg.flap.MyCamera;

public class Pipe extends Entity {

	private Texture down, up;
	
	private Rectangle center, top, bottom;
	
	private boolean nInView;
	
	private boolean firstScene;
	
	public Pipe(float spawn) {
		super();

		down = new Texture("pipeDown.png");
		up = new Texture("pipeUp.png");
		
		center = new Rectangle();
		top = new Rectangle();
		bottom = new Rectangle();
		
		resetBounds(spawn);
		
	}
	
	public void update(MyCamera cam, float spawn) {
		float right = bounds.x + bounds.width;
		
		nInView = !(cam.inView(right, Game.CENTER.y));
 
		if(!nInView && !firstScene) {
			firstScene = true;
		}
		
		if(nInView && firstScene) {
			resetBounds(spawn);
		}
		
	}
	
	private void resetBounds(float spawn) {
		firstScene = false;
		
		top.width = down.getWidth() * (Game.SIZE.y / 256);
		center.width = top.width;
		bounds.width = top.width;
		bottom.width = top.width;
		
		top.height = down.getHeight() * (Game.SIZE.y / 256);
		center.height = 150;
		bottom.height = up.getHeight() * (Game.SIZE.y / 256);
		bounds.height = top.height + center.height + bottom.height;
		
		bounds.x = spawn;
		center.x = bounds.x;
		top.x = bounds.x;
		bottom.x = bounds.x;

		top.y = MathUtils.random(((Game.SIZE.y - Constants.heightExcludeGround()) + (Constants.heightExcludeGround() * .325f)), Game.SIZE.y * .85f);
		center.y = top.y - center.height;
		bottom.y = center.y - bottom.height;
		bounds.y = bottom.y;
	}

	@Override
	public void draw(SpriteBatch sb) {
		sb.draw(up, bottom.x, bottom.y, bottom.width, bottom.height);
		sb.draw(down, top.x, top.y, top.width, top.height);

	}

	@Override
	public void dispose() {
		down.dispose();
		up.dispose();
	}

	public Rectangle getCenter() {
		return center;
	}

	public void setCenter(Rectangle center) {
		this.center = center;
	}

	public Rectangle getTop() {
		return top;
	}

	public void setTop(Rectangle top) {
		this.top = top;
	}

	public Rectangle getBottom() {
		return bottom;
	}

	public void setBottom(Rectangle bottom) {
		this.bottom = bottom;
	}

}
