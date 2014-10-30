package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
	
	Set<Tile> getPathing() {
		Set<Tile> s = new HashSet<Tile>();
		
		s.addAll(Rook.getRookPathing(this.x, this.y));
		s.addAll(Bishop.getBishopPathing(this.x, this.y));
		return s;
		
	}

	int getType() {
		// TODO Auto-generated method stub
		return 3;
	}

}
