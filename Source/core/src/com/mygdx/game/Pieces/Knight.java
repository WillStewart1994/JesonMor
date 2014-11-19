package com.mygdx.game.Pieces;

import com.mygdx.game.Core;
import com.mygdx.game.Pieces.Bishop;
import com.mygdx.game.Player;
import com.mygdx.game.Tile;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {

	void checkSet(int dx, int dy, Set<Tile> s){
		if (this.x + dx >= 0 && this.x + dx < 8 && this.y + dy >= 0 && this.y + dy < 8){
			if ((Core.grid.get(this.x+dx, this.y+dy)).empty() ||
                    (Core.grid.get(this.x+dx, this.y+dy).p.p != this.p)){
				s.add(Core.grid.get(this.x+dx, this.y+dy));
			}
		}
	}
	public Set<Tile> getPathing() {
		Set<Tile> s = new HashSet<Tile>();
		checkSet(1, 2, s);
		checkSet(-1, 2, s);
		checkSet(1, -2, s);
		checkSet(-1, -2, s);
		
		checkSet(2, 1, s);
		checkSet(-2, 1, s);
		checkSet(2, -1, s);
		checkSet(-2, -1, s);
		return s;
	}

    public int getType() {
		// TODO Auto-generated method stub
		return 4;
	}

    public Knight(Player p){
        this.p = p;
    }
	

}
