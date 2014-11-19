package com.mygdx.game.Pieces;

import com.mygdx.game.Core;
import com.mygdx.game.Player;
import com.mygdx.game.Tile;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {

    //Delegate to a static method to allow the Queen's movement composed to be
    //decomposed into corresponding Bishop and Rook movement.
    static Set<Tile> getRookPathing(int x, int y, Piece t){
        double cos;
        double sin;
        Set<Tile> s = new HashSet<Tile>();
        for (int i = 0; i < 4; i++){
            sin = Math.sin(i*Math.PI/2);
            cos = Math.cos(i*Math.PI/2);
            for (int dx = (int) cos, dy = (int) sin; true; dx+=cos,dy+=sin){
                if (x+dx < 0 || x + dx >= Core.BOARD_SIZE || y + dy < 0 || y + dy >= Core.BOARD_SIZE){
                    break;
                }
                if (!((Core.grid.get(x+dx, y+dy)).empty())){
                    if (Core.grid.get(x+dx, y+dy).p.p != t.p){
                        s.add(Core.grid.get(x+dx, y+dy));
                    }
                    break;
                }
                s.add(Core.grid.get(x+dx, y+dy));
            }
        }
        return s;

    }

    public Set<Tile> getPathing() {
        return getRookPathing(this.x, this.y, this);
    }

    @Override
    public int getType() {
        // TODO Auto-generated method stub
        return 2;
    }

    public Rook(Player p){
        this.p = p;
    }

}