package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

public class Grid {
	
	private Tile[][] data = new Tile[8][8];
	
	void add(int x, int y, Piece p){
		this.data[x][y].p = p;
		p.x = x;
		p.y = y;
	}
	
	void remove(int x, int y){
		this.data[x][y].p = null;
	}
	
	Tile get(int x, int y){
		return this.data[x][y];
	}
	
	boolean inGrid(int x, int y){
		return (x >= 0 && x <= 8 && y >= 0 && y <= 8);
	}
	
	void movePiece(Piece p, int x, int y){
		Pawn s;
		data[x][y].p = p;
		data[p.x][p.y].p = null;
		p.x = x;
		p.y = y;
		if (p.getType() == 0){
			s = (Pawn)p;
			s.firstMove = false;
		}
	}
	
	Set<Tile> toSet(){
		Set<Tile> s = new HashSet<Tile>();
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; i++){
				
			}
		}
		return s;
	}
	
	Grid(){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				this.data[i][j] = new Tile(i, j, null);
			}
		}
	}

}
