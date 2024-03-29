package com.supervillainy.game;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.entity.Enemy;
import com.supervillainy.game.entity.Entity;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.entity.RockEnemy;
import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.model.ObjLoader;
import com.supervillainy.game.model.ObjModel;
import com.supervillainy.game.powers.ActivePower;
import com.supervillainy.game.powers.MagicShot;
import com.supervillainy.game.powers.MutationShot;
import com.supervillainy.game.powers.NaturalShot;
import com.supervillainy.game.powers.PassivePower;
import com.supervillainy.game.powers.Power;
import com.supervillainy.game.powers.ShootPower;
import com.supervillainy.game.powers.SpeedPassive;
import com.supervillainy.game.powers.TechShot;
import com.supervillainy.game.sound.Sound;
import com.supervillainy.game.sound.SoundLoader;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public class BattleState implements GameState, EntityManager {
	
	/** The unique name of this state */
	public static final String NAME = "battle";
	
	/** The texture for the back drop */
	private Texture background;
	/** The texture for the particles in the shot */
	private Texture shotTexture;
	/** The texture for the ship */
	private Texture playerTexture;
	/** The model of the player's ship */
	private ObjModel playerModel;
	/** The texture applied to the asteroids */
	private Texture rockTexture;
	/** The model rendered for the asteroids */
	private ObjModel rockModel;
	
	/** The font used to draw the text to the screen */
	private BitmapFont font;
	
	/** The entity representing the player */
	private Player player;
	/** The entities in the game */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	/** The list of entities to be added at the next opportunity */
	private ArrayList<Entity> addList = new ArrayList<Entity>();
	/** The list of entities to be removed at the next opportunity */
	private ArrayList<Entity> removeList = new ArrayList<Entity>();

	/** The OpenGL material properties applied to everything in the game */
	private FloatBuffer material;
	
	/** The number of lifes left */
	private int life = 4;
	/** True if the game is over */
	private boolean gameOver;
	
	/** The sound effect to play when shooting */
	private Sound shoot;
	
	/** The current level of play */
	private int level;
	/** The timeout for the game over message before resetting to the menu */
	private int gameOverTimeout = 2000; // ms
	
	private int num_enemies;
	
	/**
	 * Create a new game state
	 */
	public BattleState(int n) {
		num_enemies = n;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	/**
	 * Defint the light setup to view the scene
	 */
	private void defineLight() {
		FloatBuffer buffer;
		
		buffer = BufferUtils.createFloatBuffer(4);
		buffer.put(1).put(1).put(1).put(1); 
		buffer.flip();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, buffer);
		
		buffer = BufferUtils.createFloatBuffer(4);
		buffer.put(1).put(1).put(1).put(1);
		buffer.flip();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, buffer);
		
		// setup the ambient light 
		buffer = BufferUtils.createFloatBuffer(4);
		buffer.put(0.8f).put(0.8f).put(0.8f).put(0.8f);
		buffer.flip();
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, buffer);
		GL11.glLightModeli(GL11.GL_LIGHT_MODEL_TWO_SIDE, GL11.GL_TRUE);
		
		// set up the position of the light
		buffer = BufferUtils.createFloatBuffer(4);
		buffer.put(10).put(10).put(5).put(0);
		buffer.flip();
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, buffer);
		
		GL11.glEnable(GL11.GL_LIGHT0);
		
		material = BufferUtils.createFloatBuffer(4);
	}

	@Override
	public void init(GameWindow window) throws IOException {
		defineLight();
		
		TextureLoader loader = new TextureLoader();
		background = loader.getTexture("res/bg.png");
		shotTexture = loader.getTexture("res/shot.png");
		
		playerTexture = loader.getTexture("res/ship.jpg");
		playerModel = ObjLoader.loadObj("res/sphere.obj");
		
		rockTexture = loader.getTexture("res/rock.jpg");
		rockModel = ObjLoader.loadObj("res/rock.obj");
		
		Texture fontTexture = loader.getTexture("res/font.png");
		font = new BitmapFont(fontTexture, 32, 32);
		
		shoot = SoundLoader.get().getOgg("res/hit.ogg");
		
		PowerState.powers.add(new NaturalShot());
		PowerState.powers.add(new MagicShot());
		PowerState.powers.add(new MutationShot());
		PowerState.powers.add(new TechShot());
		PowerState.powers.add(new SpeedPassive());
		
		for (Power p : PowerState.powers){
			p.init(loader);
		}
	}

	@Override
	public void render(GameWindow window, int delta) {
		// reset the view transformation matrix back to the empty
		// state. 
		GL11.glLoadIdentity();

		material.put(1).put(1).put(1).put(1); 
		material.flip();
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, material);
		GL11.glMaterial(GL11.GL_BACK, GL11.GL_DIFFUSE, material);
		
		// draw our background image
		GL11.glDisable(GL11.GL_LIGHTING);
		drawBackground(window);
		
		// position the view a way back from the models so we
		// can see them
		GL11.glTranslatef(0,0,-50);
		GL11.glEnable(GL11.GL_LIGHTING);
		// loop through all entities in the game rendering them
		for (Entity e : entities){
			e.render();
		}
		drawGUI(window);	
	}
	
	/**
	 * Draw the overlay for score and lifes
	 * 
	 * @param window The window in which the GUI is displayed 
	 */
	private void drawGUI(GameWindow window) {
		window.enterOrtho();
		
		GL11.glColor3f(1,1,0);
		font.drawString(1, "SCORE:" + ScoreState.score, 5, 5);
		
		GL11.glColor3f(1,0,0);
		String lifeString = "";
		for (int i=0;i<life;i++) {
			lifeString += "O";
		}
		font.drawString(0, lifeString, 795 - (lifeString.length() * 27), 5);

		GL11.glColor3f(1,1,1);

		if (gameOver) {
			font.drawString(1, "GAME OVER", 280, 286);
		}
		window.leaveOrtho();
	}
	
	/**
	 * Draw the background image
	 * 
	 * @param window The window to display the background in 
	 */
	private void drawBackground(GameWindow window) {
		window.enterOrtho();
		
		background.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2i(0,0);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2i(0,GameWindow.WINDOW_HEIGHT);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2i(GameWindow.WINDOW_WIDTH,GameWindow.WINDOW_HEIGHT);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2i(GameWindow.WINDOW_WIDTH,0);
		GL11.glEnd();
		
		window.leaveOrtho();
	}

	@Override
	public void update(GameWindow window, int delta) {
		while (Keyboard.next()){
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE){
					window.changeToState(StartMenu.NAME);
				}
			}
		}
		if (gameOver) {
			gameOverTimeout -= delta;
			if (gameOverTimeout < 0) {
				window.changeToState(StartMenu.NAME);
			}
		}
		
		for (int i=0;i<entities.size();i++) {
			Entity entity = (Entity) entities.get(i);
			float firstSize = entity.getSize();
			
			for (int j=i+1;j<entities.size();j++) {
				Entity other = (Entity) entities.get(j);
				
				if (entity.collides(other)) {
					entity.collide(this, other);
					other.collide(this, entity);
				}
			}
		}
		
		entities.removeAll(removeList);
		entities.addAll(addList);
		
		removeList.clear();
		addList.clear();
		
		for (Entity e : entities) {
			e.update(this, delta);
		}
		for (Power p : PowerState.powers){
			p.update(this, delta);
			if (p instanceof PassivePower){
				p.effect(this, delta);
			}
		}
	}

	@Override
	public void enter(GameWindow window) {
		entities.clear();
		
		player = new Player(playerTexture, playerModel, shotTexture);
		entities.add(player);
		
		Enemy enemy;
		for (int i = 0; i < num_enemies; i++) {
			enemy = new RockEnemy(rockTexture, rockModel, -20+i*2, 10);
			entities.add(enemy);
		}
		
		life = 4;
		ScoreState.score = 0;
		level = 5;
		gameOver = false;
		
		for (Power p : PowerState.powers){
			if (p instanceof ActivePower){
				((ActivePower) p).setPlayer(player);
			}
		}

	}

	@Override
	public void leave(GameWindow window) {
	}

	@Override
	public void removeEntity(Entity entity) {
		removeList.add(entity);
	}

	@Override
	public void addEntity(Entity entity) {
		addList.add(entity);
	}

	@Override
	public void enemyKilled() {
		ScoreState.score++;
		if (ScoreState.score == num_enemies){
			gameOver = true;
		}
	}

	@Override
	public void playerHit() {
		life--;
		if (life < 0) {
			gameOver = true;
			gameOverTimeout = 6000;
			removeEntity(player);
		}
	}

	@Override
	public void shotFired() {
		//shoot.play(1.0f,0.5f);
		PowerState.active = false;
	}

}
