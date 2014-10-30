package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
	
	//Delegate to a static method to allow the Queen's movement composed to be
	//decomposed into corresponding Bishop and Rook movement.
	static Set<Tile> getRookPathing(int x, int y){
		double cos;
		double sin;
		Set<Tile> s = new HashSet<Tile>();
		for (int i = 0; i < 4; i++){
			sin = Math.sin(i*Math.PI/2);
			cos = Math.cos(i*Math.PI/2);
			for (int dx = (int) cos, dy = (int) sin; true; dx+=cos,dy+=sin){
				if (x+dx < 0 || x + dx > 7 || y + dy < 0 || y + dy > 7){
					break;
				}
				if ((Core.grid.get(x+dx, y+dy)).empty() == false){
					break;
				}
				s.add(Core.grid.get(x+dx, y+dy));
			}
		}
		return s;
			
	}

	Set<Tile> getPathing() {
		return getRookPathing(this.x, this.y);
		
	}

	@Override
	int getType() {
		// TODO Auto-generated method stub
		return 2;
	}

}
