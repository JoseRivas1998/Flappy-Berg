package com.tcg.flap.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tcg.flap.Game;
import com.tcg.flap.MyCamera;
import com.tcg.flap.gamestates.*;

public class GameStateManager {

	private SpriteBatch sb;
	private ShapeRenderer sr;
	
	private MyCamera back;
	
	private Texture bg;
	
	private GameState gamestate;

	public final int TITLE = 1;
	public final int PLAY = 2;
	
	public GameStateManager() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		back = new MyCamera(Game.SIZE, true);
		bg = new Texture("background.png");
		setState(TITLE);
	}
	
	public void setState(int state) {
		if(state == TITLE) {
			gamestate = new TitleState(this);
		}
		if(state == PLAY) {
			gamestate = new PlayState(this);
		}
	}
	
	public void handleInput() {
		gamestate.handleInput();
	}
	
	public void update(float dt) {
		back.position.x++;
		
		if(back.position.x > Game.SIZE.x + Game.CENTER.x) {
			back.position.x = Game.CENTER.x;
		}
		back.update();
		gamestate.update(dt);
	}
	
	public void draw() {
		sb.begin();
		//Background
		sb.setProjectionMatrix(back.combined);
		sb.draw(bg, 0, 0, Game.SIZE.x, Game.SIZE.y);
		sb.draw(bg, Game.SIZE.x, 0, Game.SIZE.x, Game.SIZE.y);
		sb.end();
		gamestate.draw(sb, sr);
	}
	
	public void resize(Vector2 size) {
		gamestate.resize(size);
		back.resize(size, false);
		back.position.set(back.position.x, size.y * .5f, 0);
	}
	
	public void dispose() {
		gamestate.dispose();
		bg.dispose();
		sb.dispose();
		sr.dispose();
	}
	
}
