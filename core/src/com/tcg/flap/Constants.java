package com.tcg.flap;

public class Constants {

	public static final float groundToBack = (56.0f / 256.0f);
	
	public static float heightExcludeGround() {
		return Game.SIZE.y - (Game.SIZE.y * groundToBack);
	}
	
}
