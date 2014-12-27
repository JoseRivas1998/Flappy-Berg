package com.tcg.flap.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.tcg.flap.Game;
import com.tcg.flap.MyCamera;
import com.tcg.flap.entities.*;
import com.tcg.flap.managers.GameStateManager;

public class PlayState extends GameState {

	private MyCamera main, hud;
	
	private Array<Pipe> pipes;
	
	private int numPipes;
	
	private Sheinberg berg;
	
	private float sX, sY, hsX, hsY;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	private Ground g;

	@Override
	protected void init() {
		main = new MyCamera(Game.SIZE, true);
		hud = new MyCamera(Game.SIZE, true);
		
		g = new Ground();
		pipes = new Array<Pipe>();
		
		numPipes = 2;
		
		for(int i = 0; i < numPipes; i++) {
			float spawn = 0 + (main.viewportWidth * (2));
			pipes.add(new Pipe(spawn + (200 * i)));
		}
		
		berg = new Sheinberg(main);
		
		Game.SCORE = 0;
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub

	}
	
	private void setScoreText() {
		sX = (Game.CENTER.x) - (Game.res.getWidth("score", "" + Game.SCORE) * .5f);
		sY = (Game.SIZE.y * .75f) + (Game.res.getHeight("score", "High Score" + Game.HIGH) * .5f);
		
		hsX = 0;
		hsY = Game.SIZE.y;
	}

	@Override
	public void update(float dt) {
		main.position.x += 2;
		
		for(Pipe p : pipes) {
			float spawn = main.getLeft() + (main.viewportWidth);
			p.update(main, spawn);
		}
		g.update(main);
		
		berg.update(main, g, pipes);
		
		if(berg.isShouldEnd()) {
			gsm.setState(gsm.TITLE);
		}
		
		if(Game.SCORE > Game.HIGH) {
			Game.HIGH = Game.SCORE;
		}
		
		setScoreText();
		
		main.update();
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr) {
		// TODO Auto-generated method stub

		sb.begin();
		
		//main
		sb.setProjectionMatrix(main.combined);
		for(Pipe p : pipes) {
			p.draw(sb);
		}
		g.draw(sb);
		berg.draw(sb);
		
		//HUD
		sb.setProjectionMatrix(hud.combined);
		Game.res.getFont("score").setColor(Color.BLACK);
		for(int i = 0; i < 4; i++) {
			Game.res.getFont("score").draw(sb, "" + Game.SCORE, sX + i, sY - i); 
		}
		Game.res.getFont("score").setColor(Color.WHITE);
		Game.res.getFont("score").draw(sb, "" + Game.SCORE, sX, sY); 
		
		Game.res.getFont("hscore").setColor(Color.BLACK);
		for(int i = 0; i < 4; i++) {
			Game.res.getFont("hscore").draw(sb, "High Score: " + Game.HIGH, hsX + i, hsY - i); 
		}
		Game.res.getFont("hscore").setColor(Color.WHITE);
		Game.res.getFont("hscore").draw(sb, "High Score: " + Game.HIGH, hsX, hsY); 
		
		sb.end();
	}

	@Override
	public void resize(Vector2 size) {
		main.resize(size, false);
		hud.resize(size, true);
		
		main.position.set(main.position.x, size.y * .5f, 0);
	}

	@Override
	public void dispose() {
		for(Pipe p : pipes) {
			p.dispose();
		}
		pipes.clear();
		berg.dispose();
		g.dispose();
	}

}
