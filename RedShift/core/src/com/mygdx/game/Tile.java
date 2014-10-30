package com.mygdx.game;

public class Tile {
	int x;
	int y;
	Piece p = null;
	
	Tile(int x, int y, Piece p){
		this.x = x;
		this.y = y;
		this.p = p;
		if (p!= null){
			this.p.x = x;
			this.p.y = y;
		}
	}
	
	boolean empty(){
		return p == null;
	}

}