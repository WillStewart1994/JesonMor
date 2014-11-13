package com.mygdx.game;


import com.mygdx.game.Pieces.*;

import java.util.HashSet;
import java.util.Set;

public class Grid {
	
	private Tile[][] data = new Tile[Core.BOARD_SIZE][Core.BOARD_SIZE];
	
	void add(int x, int y, Piece p){
		this.data[x][y].p = p;
		p.x = x;
		p.y = y;
	}
	
	void remove(int x, int y){
		this.data[x][y].p = null;
	}
	
	public Tile get(int x, int y){
		return this.data[x][y];
	}
	
	boolean inGrid(int x, int y){
		return (x >= 0 && x <= Core.BOARD_SIZE && y >= 0 && y <= Core.BOARD_SIZE);
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
		for (int i = 0; i < Core.BOARD_SIZE; i++){
			for (int j = 0; j < Core.BOARD_SIZE; i++){
				
			}
		}
		return s;
	}
	
	Grid(){
		for (int i = 0; i < Core.BOARD_SIZE; i++){
			for (int j = 0; j < Core.BOARD_SIZE; j++){
				this.data[i][j] = new Tile(i, j, null);
			}
		}
	}

}
