package com.tcg.flap.gamestates;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.tcg.flap.Game;
import com.tcg.flap.MyCamera;
import com.tcg.flap.managers.GameStateManager;
import com.tcg.flap.managers.MyInput;

public class TitleState extends GameState {

	private String title, space, high;
	
	private float tX, tY, sX, sY, hX, hY;
	
	private MyCamera cam;
	
	public TitleState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	protected void init() {
		title = "Flappy Berg";
		space = "Press Space";
		high = "High Score: " + Game.HIGH;
		
		cam = new MyCamera(Game.SIZE, true);

	}

	@Override
	public void handleInput() {
		if(MyInput.isPressed(MyInput.SPACE)) {
			gsm.setState(gsm.PLAY);
		}

	}

	@Override
	public void update(float dt) {
		tX = Game.CENTER.x - (Game.res.getWidth("title", title) * .5f);
		tY = (Game.SIZE.y * .75f) + (Game.res.getHeight("title", title) * .5f);
		
		sX = Game.CENTER.x - (Game.res.getWidth("titems", space) * .5f);
		sY = (Game.CENTER.y) + (Game.res.getHeight("titems", space) * .5f);
		
		hX = Game.CENTER.x - (Game.res.getWidth("titems", high) * .5f);
		hY = (Game.SIZE.y * .25f) + (Game.res.getHeight("titems", high) * .5f);
	}

	@Override
	public void draw(SpriteBatch sb, ShapeRenderer sr) {
		sb.begin();
		sb.setProjectionMatrix(cam.combined);
		Game.res.getFont("title").setColor(Color.BLACK);
		for(int i = 0; i < 4; i++) {
			Game.res.getFont("title").draw(sb, title, tX + i, tY - i); 
		}
		Game.res.getFont("title").setColor(Color.WHITE);
		Game.res.getFont("title").draw(sb, title, tX, tY);
		
		Game.res.getFont("titems").setColor(Color.BLACK);
		for(int i = 0; i < 4; i++) {
			Game.res.getFont("titems").draw(sb, space, sX + i, sY - i); 
			Game.res.getFont("titems").draw(sb, high, hX + i, hY - i); 
		}
		Game.res.getFont("titems").setColor(Color.WHITE);
		Game.res.getFont("titems").draw(sb, space, sX, sY);
		Game.res.getFont("titems").draw(sb, high, hX, hY);
		sb.end();

	}

	@Override
	public void resize(Vector2 size) {
		cam.resize(size, true);

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
