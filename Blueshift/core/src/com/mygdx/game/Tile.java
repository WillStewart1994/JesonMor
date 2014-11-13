package com.mygdx.game;

import com.mygdx.game.Pieces.Piece;

public class Tile {
    public int x;
    public int y;
	public Piece p = null;
	
	Tile(int x, int y, Piece p){
		this.x = x;
		this.y = y;
		this.p = p;
		if (p!= null){
			this.p.x = x;
			this.p.y = y;
		}
	}
	
	public boolean empty(){
		return p == null;
	}

}