package com.tcg.flap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.tcg.flap.managers.*;

public class Game extends ApplicationAdapter {
	
	public static Vector2 SIZE, CENTER;
	
	public static Content res;
	
	private GameStateManager gsm;
	
	private float fpstime;
	private int frames;
	public static int fps;
	
	public static int SCORE, HIGH;
	
	private static Save s;
	
	@Override
	public void create () {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		SIZE = new Vector2();
		CENTER = new Vector2();
		SIZE.set(width, height);
		CENTER.set(width * .5f, height * .5f);
		
		fpstime = 0;
		frames = 0;
		fps = 0;
		
		res = new Content();
		res.loadBitmapFont("font", "flappy.TTF", "score", 56, Color.WHITE);
		res.loadBitmapFont("font", "flappy.TTF", "hscore", 24, Color.WHITE);
		res.loadBitmapFont("font", "flappy.TTF", "title", 46, Color.WHITE);
		res.loadBitmapFont("font", "flappy.TTF", "titems", 24, Color.WHITE);

		res.loadSound("sound", "ya.wav", "flap");
		res.loadSound("sound", "meow.wav", "meow");
		
		SCORE = 0;
		
		try {
			FileInputStream fileIn = new FileInputStream("data.sav");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			s = (Save) in.readObject();
			in.close();
			fileIn.close();
		} catch(Exception e) {
			e.printStackTrace();
			s = new Save();
			s.setHighScore(0);
		}
		
		HIGH = s.getHighScore(); 
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		Controllers.addListener(new MyControllerProcessor());
		
		gsm = new GameStateManager();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float dt = Gdx.graphics.getDeltaTime();
		fpstime += dt;
		frames++;
		if(fpstime >= 1) {
			fps = frames;
			frames = 0;
			fpstime = 0;
		}
		Gdx.graphics.setTitle("Flappy Berg | " + Game.fps + " fps");
		
		gsm.handleInput();
		gsm.update(dt);
		gsm.draw();
		
		MyInput.update();
	}

	@Override
	public void resize(int width, int height) {
		SIZE.set(width, height);
		CENTER.set(width * .5f, height * .5f);
		gsm.resize(Game.SIZE);
	}

	@Override
	public void dispose() {
		gsm.dispose();
		res.removeAll();
		s.setHighScore(Game.HIGH);
		try {
			FileOutputStream fileOut = new FileOutputStream("data.sav");
			 ObjectOutputStream out = new ObjectOutputStream(fileOut);
			 out.writeObject(s);
	         out.close();
	         fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
