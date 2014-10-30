package com.mygdx.game;

import java.util.Set;

public abstract class Piece {
	
	int x;
	int y;
	int player;
	
	abstract Set<Tile> getPathing();
	
	abstract int getType();



}
