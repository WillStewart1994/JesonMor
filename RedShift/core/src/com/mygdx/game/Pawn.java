package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
	
	//Up = 1, down = -1
	
	int direction;
	boolean firstMove = true;

	@Override
	Set<Tile> getPathing() {
		Set<Tile> s = new HashSet<Tile>();
		if (firstMove){
			s.add(Core.grid.get(x, y+direction));
			s.add(Core.grid.get(x, y+2*direction));
		} else {
			if (y+direction >= 0 && y+direction < 8){
				s.add(Core.grid.get(x, y+direction));
			}
		}
		
		// TODO Auto-generated method stub
		return s;
	}
	
	Pawn(int direction){
		this.direction = direction;
	}

	@Override
	int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

}