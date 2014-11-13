package com.mygdx.game.Pieces;

import com.mygdx.game.Player;
import com.mygdx.game.Tile;

import java.util.HashSet;
import java.util.Set;

public abstract class Piece {

    public int x;
    public int y;
    public Player p;

    public abstract Set<Tile> getPathing();

    public abstract int getType();
}