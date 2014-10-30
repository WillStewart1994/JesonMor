package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Core extends ApplicationAdapter {
	static SpriteBatch batch;
	static ShapeRenderer sh;

	static Set<Tile> moveable = new HashSet<Tile>();
	static Tile selectedTile = null;
	static Grid grid = new Grid();

	static boolean turn = true;

	@Override
	public void create() {
		batch = new SpriteBatch();
		sh = new ShapeRenderer();
		MyInputProcessor inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		for (int j = 0; j < 8; j++) {
			grid.add(j, 1, new Pawn(1));
		}
		grid.add(7, 0, new Rook());
		grid.add(0, 0, new Rook());
		
		
		grid.add(2, 0, new Bishop());
		grid.add(5, 0, new Bishop());
		
		

		grid.add(1, 0, new Knight());
		grid.add(6, 0, new Knight());
		
		grid.add(3, 0, new Queen());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		sh.begin(ShapeType.Filled);
		int type = -1;
		for (int i = 0; i < 8; i++) {
			// swap
			for (int j = 0; j < 8; j++) {
				type = -1;
				// if in pathable, add transparent blue layer
				//
				if ((i + j) % 2 == 0) {
					sh.setColor(.075f, .075f, .075f, .5f);
				} else {
					sh.setColor(.92f, .92f, .92f, .5f);
				}
				
				if (moveable.contains(grid.get(i, j))) {

					sh.setColor(1, 0, 0, .5f);
				}

				sh.rect(i * 80, j * 80, 80, 80);
				

				// swap
			}
		}
		for (int i = 0; i < 8; i++) {
			// swap
			for (int j = 0; j < 8; j++) {
				type = -1;
				if (grid.get(i, j).p != null){
					type = grid.get(i, j).p.getType();
				}
				if (type == 0) {
					sh.setColor(0, 1, 0, 1);
					sh.rect(i * 80 + 10, j * 80 + 10, 60, 60);
				} else if (type == 1){
					sh.setColor(.5f, .5f, 0, 1);
					sh.rect(i * 80 + 10, j * 80 + 10, 60, 60);
				} else if (type == 2){
					sh.setColor(0, .5f, .5f, 1);
					sh.rect(i * 80 + 10, j * 80 + 10, 60, 60);
				} else if (type == 3){
					sh.setColor(1, .25f, .25f, 1);
					sh.rect(i * 80 + 10, j * 80 + 10, 60, 60);
				} else if (type == 4){
					sh.setColor(.5f, 0, .5f, 1);
					sh.rect(i * 80 + 10, j * 80 + 10, 60, 60);
				}
			}
		}

		sh.setColor(0, 0, 1, .5f);
		if (selectedTile != null) {
			sh.rect(selectedTile.x * 80, selectedTile.y * 80, 80, 80);
		}
		sh.end();
	}
}