package com.mygdx.Platformer;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
	
	static Set<Projectile> projectiles = new HashSet<Projectile>();
	
	Texture image;
	float x;
	float y;
	float rotation = 0;
	Vector2 velocity;
	Vector2 acceleration = Vector2.Zero;
 	boolean bounce = false;
	static float delayCount = CoreGame.PROJECTILE_DELAY;
	
	void move(float delta){
		velocity.add(acceleration.cpy().scl(delta));
		this.x += velocity.x * delta;
		this.y += velocity.y * delta;
		if (!this.acceleration.isZero()){			
			this.rotation = (float) Math.toDegrees(Math.atan2(velocity.y, velocity.x));
		}
	}
	
	
	Projectile(String imageSource, float x, float y, Vector2 velocity, float angle, Vector2 acceleration){
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.image = new Texture(Gdx.files.internal(imageSource));
		this.rotation = angle;
		this.acceleration = acceleration;
		projectiles.add(this);
	}
	
	//No acceleration required, probably more comman than having acceleration?

}
