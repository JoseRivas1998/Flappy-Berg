package com.tcg.flap.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tcg.flap.Constants;
import com.tcg.flap.Game;
import com.tcg.flap.MyCamera;

public class Ground extends Entity {

	private Texture tex;
	
	public Ground() {
		super();
		tex = new Texture("ground.png");
	}

	public void update(MyCamera cam) {
		bounds.set(cam.getLeft(), 0, Game.SIZE.x, Game.SIZE.y  * Constants.groundToBack);
	}

	@Override
	public void draw(SpriteBatch sb) {
		sb.draw(tex, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void dispose() {
		tex.dispose();
	}

}
