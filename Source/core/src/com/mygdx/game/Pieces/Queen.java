package com.mygdx.game.Pieces;

import com.mygdx.game.Player;
import com.mygdx.game.Tile;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    public Set<Tile> getPathing() {
        Set<Tile> s = new HashSet<Tile>();

        s.addAll(Rook.getRookPathing(this.x, this.y, this));
        s.addAll(Bishop.getBishopPathing(this.x, this.y, this));
        return s;

    }

    public int getType() {
        // TODO Auto-generated method stub
        return 3;
    }

    public Queen(Player p){
        this.p = p;
    }

}