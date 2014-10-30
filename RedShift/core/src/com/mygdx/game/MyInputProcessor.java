package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		int dx = (int) (x / 80);
		int dy = (int) ((640 - y) / 80);
		if (Core.turn && (Core.grid.get(dx, dy).empty() == false && 
				(Core.moveable.contains(Core.grid.get(dx, dy)) == false || Core.grid.get(dx, dy) == Core.selectedTile) )) {
			if (Core.selectedTile == Core.grid.get(dx, dy)){
				Core.selectedTile = null;
				Core.moveable.clear();
				//Also need to check if user is clicking on a tile to move there.
			} else{
				Core.selectedTile = Core.grid.get(dx, dy);
				Core.moveable = Core.selectedTile.p.getPathing();
			}
		} else {
			if (Core.moveable.contains(Core.grid.get(dx, dy)) && Core.grid.get(dx, dy) != Core.selectedTile
					&& Core.grid.get(dx, dy).empty()){
				Core.grid.movePiece(Core.selectedTile.p, dx, dy);
				Core.selectedTile = null;
				Core.moveable.clear();
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}