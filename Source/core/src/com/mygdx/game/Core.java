package com.mygdx.game;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.Pieces.*;

public class Core extends ApplicationAdapter {
    static SpriteBatch batch;
    static ShapeRenderer sh;

    public static Set<Tile> moveable = new HashSet<Tile>();
    public static Tile selectedTile = null;
    public static Grid grid = new Grid();

    public static Player turn = Player.PLAYER_1;
    public static final int BOARD_SIZE = 8;
    public static final int TILE_SIZE = 80;

    public void createStartingPieces(Player p){
        int pawnRow = 1;
        int faceRow = 0;
        int direction = 1;
        if (p == Player.PLAYER_2){
            pawnRow = BOARD_SIZE - 2;
            faceRow = BOARD_SIZE - 1;
            direction = -1;
        }
        for (int j = 0; j < BOARD_SIZE; j++) {
            grid.add(j, pawnRow, new Pawn(p, direction));
        }
        grid.add(7, faceRow, new Rook(p));
        grid.add(0, faceRow, new Rook(p));

        grid.add(2, faceRow, new Bishop(p));
        grid.add(5, faceRow, new Bishop(p));


        grid.add(1, faceRow, new Knight(p));
        grid.add(6, faceRow, new Knight(p));

        grid.add(3, faceRow, new Queen(p));
    }

    public void create() {
        batch = new SpriteBatch();
        sh = new ShapeRenderer();
        MyInputProcessor inputProcessor = new MyInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        createStartingPieces(Player.PLAYER_1);
        createStartingPieces(Player.PLAYER_2);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        sh.begin(ShapeType.Filled);
        int type = -1;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) {
                    sh.setColor(new Color((255f/255f), (206f/255f), (158f/255f), 1));
                } else {
                    sh.setColor(new Color((208f/255f), (140f/255f), (71f/255f), 1));
                }
                if (moveable.contains(grid.get(i, j))) {
                    sh.setColor(1, 0, 0, .5f);
                }
                sh.rect(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        sh.setColor(0, 0, 1, .5f);
        if (selectedTile != null) {
            sh.rect(selectedTile.x * TILE_SIZE, selectedTile.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                type = -1;
                if (grid.get(i, j).p != null){
                    type = grid.get(i, j).p.getType();
                    if (type == 0) {
                        sh.setColor(0, 1, 0, 1);
                    } else if (type == 1){
                        sh.setColor(.5f, .5f, 0, 1);
                    } else if (type == 2){
                        sh.setColor(0, .5f, .5f, 1);
                    } else if (type == 3){
                        sh.setColor(1, .25f, .25f, 1);
                    } else if (type == 4){
                        sh.setColor(.5f, 0, .5f, 1);
                    }
                    sh.rect(i * TILE_SIZE + 20, j * TILE_SIZE + 20, 40, 40);
                }
            }
        }
        sh.end();
    }
}