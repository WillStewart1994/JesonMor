package com.mygdx.game.Pieces;

import com.mygdx.game.Core;
import com.mygdx.game.Player;
import com.mygdx.game.Tile;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
	
	//Up = 1, down = -1

    public int direction;
	public boolean firstMove = true;

	@Override
	public Set<Tile> getPathing() {
		Set<Tile> s = new HashSet<Tile>();
		if (firstMove){
			s.add(Core.grid.get(x, y+direction));
			s.add(Core.grid.get(x, y+2*direction));
		} else {
			if (y+direction >= 0 && y+direction < Core.BOARD_SIZE){
				s.add(Core.grid.get(x, y+direction));
			}
		}
		return s;
	}

	@Override
	public int getType() {
		return 0;
	}

    public Pawn(Player p, int direction){
        this.p = p;
        this.direction = direction;
    }


}