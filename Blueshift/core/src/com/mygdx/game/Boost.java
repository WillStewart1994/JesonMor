package com.mygdx.Platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Boost {
	static boolean ACTIVE = false;
	static float DURATION = 0.35f;
	static float SPEED = 475;
	static int direction = 0;
	static Texture image = new Texture(Gdx.files.internal("PlayerImageEx.png"));
	static float count = 0;
	
	static void move(float delta){
		CoreGame.player.x+=delta*SPEED* direction;
		count+=delta;
		if (count >= DURATION){
			end();
		}
	}
	
	static void end(){
		ACTIVE = false;
		count = 0;
		CoreGame.velocity.y = 0;
		direction = 0;
	}

}