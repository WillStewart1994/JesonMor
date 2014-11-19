package com.mygdx.game.Pieces;

import com.mygdx.game.Core;
import com.mygdx.game.Player;
import com.mygdx.game.Tile;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {


    //Delegate to a static method to allow the Queen's movement composed to be
    //decomposed into corresponding Bishop and Rook movement.
    static Set<Tile> getBishopPathing(int x, int y, Piece t){
        double cos;
        double sin;
        Set<Tile> s = new HashSet<Tile>();
        for (int i = 0; i < 4; i++){
            sin = Math.sin(i*Math.PI/2 + Math.PI/4) * Math.sqrt(2);
            cos = Math.cos(i*Math.PI/2 + Math.PI/4) * Math.sqrt(2);

            sin = (int)(sin*1.01);
            cos = (int)(cos*1.01);

            for (int dx = (int) (cos), dy = (int) (sin); true; dx+=cos,dy+=sin){
                if (x+dx < 0 || x + dx > 7 || y + dy < 0 || y + dy > 7){
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
        return getBishopPathing(this.x, this.y, this);
    }

    @Override
    public int getType() {
        // TODO Auto-generated method stub
        return 1;
    }

    public Bishop(Player p){
        this.p = p;
    }


}
