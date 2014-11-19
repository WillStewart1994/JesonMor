package com.mygdx.Platformer;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class CoreGame extends ApplicationAdapter {

	public static final Vector2 MAX_SPEED = new Vector2(350, 500);
	public static final int GRAVITY = -1550;
	public static final int HORIZONTAL_ACCELERATION = 2200;
	public static final int TILE_SIZE = 32;

	public static final int LEASH_RANGE = 232;
	public static final int SCREEN_HEIGHT = 704;
	public static final int SCREEN_WIDTH = (1280);
	public static final int PLAYER_SIZE = 62;
	public static final int SCREEN_MINIMUM = SCREEN_WIDTH / 2;
	public static int SCREEN_MAXIMUM = SCREEN_WIDTH / 2;
	public static final int PROJECTILE_DELAY = 400; // (ms)

	public static SpriteBatch batch;
	public static Sprite projectileSprite;
	public static Texture playerImage;
	public static Texture background;
	public static Texture barGray;
	public static Texture itemList;
	public static Texture itemBorder;
	
	public static OrthographicCamera c;
	public static Vector2 player;
	public static TiledMap map;
	public static OrthogonalTiledMapRenderer renderer;
	
	public static float delta;
	public static TiledMapTileLayer layer;
	public static MyInputProcessor inputProcessor;
	public static boolean[][] pathable;
	public static TiledMapTile transparentTile;
	
	public static int horizontalDirection = 0;
	public static boolean jumping = false;
	public static Vector2 velocity = new Vector2();
	public static float targetHorizontalSpeed = 0;
	
	public static int itemIndex = 0;
	public static Item[] items = new Item[10];
	

	@Override
	public void create() {
		map = new TmxMapLoader().load("colourMap.tmx");
		c = new OrthographicCamera();
		c.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
		batch = new SpriteBatch();
		
		playerImage = new Texture(Gdx.files.internal("PlayerImage.png"));
		background = new Texture(Gdx.files.internal("Background.png"));
		barGray = new Texture(Gdx.files.internal("barGray.png"));
		
		player = new Vector2(200, 200);
		renderer = new OrthogonalTiledMapRenderer(map, 1);
		inputProcessor = new MyInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
		
		
		layer = (TiledMapTileLayer) map.getLayers().get(0);
		pathable = new boolean[layer.getWidth()][layer.getHeight()];
		SCREEN_MAXIMUM = layer.getWidth() * TILE_SIZE - SCREEN_WIDTH / 2;
		String s;
		for (int i = 0; i < layer.getWidth(); i++){
			for (int j = 0; j < layer.getHeight(); j++){
				s = layer.getCell(i, j).getTile().getProperties().get("pathable").toString();
				pathable[i][j] = s.equals("true");
			}
		}
		transparentTile = map.getTileSets().getTile(0);
		

		itemList = new Texture(Gdx.files.internal("itemList.png"));

		itemBorder = new Texture(Gdx.files.internal("border.png"));
		
	}
	
	public static boolean isTilePathable(int x, int y) {
		return pathable[x][y];
	}

	public String getTileProperty(int x, int y, String property) {
		return layer.getCell(x, y).getTile().getProperties().get(property)
				.toString();
	}
	
	public static void destroyTile(int x, int y){
		pathable[x][y] = true;
		layer.getCell(x, y).setTile(transparentTile);
	}

	public void moveProjectiles() {
		Iterator<Projectile> it = Projectile.projectiles.iterator();
		while (it.hasNext()) {
			Projectile p = it.next();
			p.move(Gdx.graphics.getDeltaTime());
			int leftX = (int) (p.x / (TILE_SIZE));
			int rightX = (int) ((p.x + ((TILE_SIZE) - 1)) / ((TILE_SIZE)));
			int bottomY = (int) ((p.y) / ((TILE_SIZE)));
			int topY = (int) ((p.y + ((TILE_SIZE) - 1)) / ((TILE_SIZE)));

			if (!isTilePathable(rightX, topY)){
				destroyTile(rightX, topY);
				it.remove();
			}else if (!isTilePathable(rightX, bottomY)){
				destroyTile(rightX, bottomY);
				it.remove();
			} else if (!isTilePathable(leftX, topY)){
				destroyTile(leftX, topY);
				it.remove();
			} else if (!isTilePathable(leftX, bottomY)){
				destroyTile(leftX, bottomY);
				it.remove();
			}
		}
	}
	

	public void moveHorizontalEx() {

		if (Boost.ACTIVE){
			Boost.move(delta)
					
					;
		} else {
			velocity.x += HORIZONTAL_ACCELERATION * horizontalDirection * delta;
			if (Math.abs(velocity.x) > MAX_SPEED.x) {
				velocity.x = MAX_SPEED.x * horizontalDirection;
			}
			if (targetHorizontalSpeed == 0 && velocity.x < 0
					&& horizontalDirection == -1) {
				velocity.x = 0;
				horizontalDirection = 0;
			} else if (targetHorizontalSpeed == 0 && velocity.x > 0
					&& horizontalDirection == 1) {
				velocity.x = 0;
				horizontalDirection = 0;
			}
			player.x += velocity.x * delta;
		}

		if (velocity.x > 0 || Boost. direction == 1) {
			int rightX = (int) (player.x + PLAYER_SIZE) / (TILE_SIZE);
			int bottomY = (int) (player.y  / TILE_SIZE);
			if (!isTilePathable(rightX, bottomY) || !isTilePathable(rightX, bottomY + 1)
			|| (player.y + PLAYER_SIZE > bottomY*TILE_SIZE + TILE_SIZE*2 && !isTilePathable(rightX, bottomY + 2))) {
				velocity.x = 0;
				horizontalDirection = 0;
				player.x = rightX * TILE_SIZE - PLAYER_SIZE;

				if (Boost.ACTIVE){
					Boost.end();
				}
			}
		} else {
			int leftX = (int) (player.x / TILE_SIZE);
			int bottomY = (int) player.y / TILE_SIZE;
			if (!isTilePathable(leftX, bottomY) || !isTilePathable(leftX, bottomY + 1)
			|| (player.y + PLAYER_SIZE > bottomY*TILE_SIZE + TILE_SIZE*2 && !isTilePathable(leftX, bottomY + 2))) {
				velocity.x = 0;
				horizontalDirection = 0;
				player.x = leftX * TILE_SIZE + TILE_SIZE;

				if (Boost.ACTIVE){
					Boost.end();
				}
			} 
		}

		if (Gdx.input.isKeyPressed(Keys.D) && !(Gdx.input.isKeyPressed(Keys.A))) {
			inputProcessor.moveRight();
		} else if (Gdx.input.isKeyPressed(Keys.A)
				&& !(Gdx.input.isKeyPressed(Keys.D))) {
			inputProcessor.moveLeft();
		}

		c.position.x = Math.max(c.position.x, player.x
				- (SCREEN_WIDTH / 2 - LEASH_RANGE - PLAYER_SIZE));
		c.position.x = Math.min(c.position.x, player.x
				+ (SCREEN_WIDTH / 2 - LEASH_RANGE));
		c.position.x = Math.max(SCREEN_MINIMUM, c.position.x);
		c.position.x = Math.min(SCREEN_MAXIMUM, c.position.x);
	}

	public void moveVerticalEx() {
		if (!Boost.ACTIVE){
			if (jumping) {
				velocity.y += GRAVITY * delta;
				player.y += velocity.y * delta;
	
				if (velocity.y > 0){
					int leftX = (int) ((player.x) / (TILE_SIZE));
					int topY = (int) ((player.y + PLAYER_SIZE) / TILE_SIZE);
					if (!isTilePathable(leftX, topY) || 
							(!isTilePathable(leftX + 1, topY)) || 
							(player.x + PLAYER_SIZE > leftX*TILE_SIZE + 2*TILE_SIZE && !isTilePathable(leftX + 2, topY))) {
						velocity.y = 0;
						player.y = topY * TILE_SIZE - PLAYER_SIZE;
					}
						
				} else {
					int leftX = (int) ((player.x) / (TILE_SIZE));
					int bottomY  =  (int) ((player.y) / TILE_SIZE);
					if (!isTilePathable(leftX, bottomY) || 
							(!isTilePathable( leftX + 1, (int) bottomY)) || 
							(player.x + PLAYER_SIZE > leftX*TILE_SIZE + 2*TILE_SIZE && !isTilePathable(leftX + 2, bottomY))) {
						velocity.y = 0;
						jumping = false;
						player.y = bottomY * TILE_SIZE + TILE_SIZE;
					}
				}
	
			} else {
				if (! ((!isTilePathable((int) ((player.x) / (TILE_SIZE)), (int) ((player.y-1) / TILE_SIZE))) || 
						(!isTilePathable( (int) ((player.x) / (TILE_SIZE)) + 1, (int) ((player.y-1) / TILE_SIZE))) 
						|| 
						(player.x + PLAYER_SIZE > ((int) (player.x) / (TILE_SIZE))*TILE_SIZE + 2*TILE_SIZE && 
								!isTilePathable((int) ((player.x) / (TILE_SIZE)) + 2, (int) ((player.y-1) / TILE_SIZE))))){
					jumping = true;
						
					}
				//check if platform below is empty
			}
		}
	}

	public void render() {
		// layer = (TiledMapTileLayer) map.getLayers().get(0);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(c.combined);
		//f.log();


		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();
		
		renderer.setView(c);
		renderer.render();
		
		
		batch.begin();
		for (Projectile p : Projectile.projectiles) {
			projectileSprite = new Sprite(p.image);
			projectileSprite.setPosition(p.x, p.y);
			projectileSprite.setRotation(p.rotation);
			projectileSprite.draw(batch);
		}

		if (!Boost.ACTIVE) {
			batch.draw(playerImage, (int) player.x, (int) player.y,
					PLAYER_SIZE, PLAYER_SIZE);
		} else {
			batch.draw(Boost.image, (int) player.x, (int) player.y,
					PLAYER_SIZE, PLAYER_SIZE);
		}
		batch.draw(itemList, SCREEN_WIDTH - 150     
				- (CoreGame.SCREEN_WIDTH/2) + CoreGame.c.position.x, (SCREEN_HEIGHT- 450)/2);
		batch.draw(itemBorder, SCREEN_WIDTH - 150 - (CoreGame.SCREEN_WIDTH/2) + CoreGame.c.position.x, (SCREEN_HEIGHT- 450)/2 + 10 + 110*itemIndex);
		batch.end();

		delta = Gdx.graphics.getDeltaTime();
		moveHorizontalEx();
		moveVerticalEx();
		moveProjectiles();

		if (Projectile.delayCount != PROJECTILE_DELAY) {
			Projectile.delayCount -= 1000 * delta;
			if (Projectile.delayCount <= 0) {
				Projectile.delayCount = PROJECTILE_DELAY;
			}
		}
		c.update();
	}
}