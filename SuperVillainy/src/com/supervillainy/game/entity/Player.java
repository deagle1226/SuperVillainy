package com.supervillainy.game.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.GameWindow;
import com.supervillainy.game.model.ObjModel;
import com.supervillainy.game.texture.Texture;

public class Player extends AbstractEntity {
	
	public final static int SPEED = 10;
	public final static float SCALE = 0.02f;
	
	/** The texture to apply to the model */
	private Texture texture;
	/** The model to be displayed for the player */
	private ObjModel model;
	
	/** The X component of the forward vector - used to place shots */
	private float forwardX = 0;
	/** The Y component of the forward vector - used to place shots */
	private float forwardY = 1;
	
	/** The timeout that counts down until the player can shoot again */
	private int shotTimeout;
	/** The interval in milliseconds between player shots */
	private int shotInterval = 250;
	
	/** The texture applied to the particles that build up the player's shots */
	private Texture shotTexture;
	
	public Player(Texture texture, ObjModel model, Texture shotTexture) {
		this.texture = texture;
		this.model = model;
		this.shotTexture = shotTexture;
		positionX = 0;
		positionY = 0;
	}
	
	private void fire(EntityManager manager){
		// create a new shot based on our current settings
		Shot shot = new Shot(shotTexture, 
							 getX() + forwardX, 
							 getY() + forwardY, 
							 forwardX * 30, 
							 forwardY * 30);
		
		// add the entity to the game and notify it that a 
		// shot has been fired (just in case it needs to do 
		// anything)
		manager.addEntity(shot);
		manager.shotFired();
	}
	
	@Override
	public void update(EntityManager manager, int delta) {
		updateKeys(manager, delta);
		updateMouse(manager, delta);
		
		// call the update the abstract class to cause our generic
		// movement and anything else the abstract implementation 
		// provides for us
		super.update(manager, delta);
	}
	
	public void updateKeys(EntityManager manager, int delta) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			velocityY = SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			velocityY = -SPEED;
		} else {
			velocityY = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			velocityX = SPEED;
		} else if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			velocityX = -SPEED;
		}
		 else {
			velocityX = 0;
		}
	}
	
	public void updateMouse(EntityManager manager, int delta) {
		int mouseX = Mouse.getX() - GameWindow.WINDOW_WIDTH/2;
		int mouseY = Mouse.getY() - GameWindow.WINDOW_HEIGHT/2;
		
		float dX = mouseX - (positionX*GameWindow.WINDOW_RATIO);
		float dY = mouseY - (positionY*GameWindow.WINDOW_RATIO);
		
		float mag = (float) Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
		forwardX = dX/mag;
		forwardY = dY/mag;
		
		rotationZ = (float) ((float) -Math.atan2(forwardX, forwardY) * 180 / Math.PI)+180;

		shotTimeout -= delta;
		if (shotTimeout <= 0){
			if (Mouse.isButtonDown(0)){
				fire(manager);
				shotTimeout = shotInterval;
			}
		}
		
	}

	@Override
	public void render() {
		// enable lighting for the player's model
		GL11.glEnable(GL11.GL_LIGHTING);
		
		// store the original matrix setup so we can modify it
		// without worrying about effecting things outside of this 
		// class
		GL11.glPushMatrix();

		// position the model based on the players currently game
		// location
		//GL11.glTranslatef(positionX,positionY,0);
		GL11.glTranslatef(positionX,positionY,0);
		
		// rotate the ship round to our current orientation for shooting
		GL11.glRotatef(rotationZ,0,0,1);
		
		// setup the matrix to draw the model for our player
		// rotate the ship to the right orientation for rendering. Our
		// ship model is modelled on a different axis to the one we're
		// using so we'd like to rotate it round
		GL11.glRotatef(90,1,0,0);
		
		// scale the model down because its way to big by default
		GL11.glScalef(SCALE, SCALE, SCALE);
		
		// bind to the texture for our model then render it. This 
		// actually draws the geometry to the screen
		texture.bind();
		model.render();
		
		// restore the model matrix so we leave this method
		// in the same state we entered
		GL11.glPopMatrix();
	}

	@Override
	public float getSize() {
		return 1;
	}

	@Override
	public void collide(EntityManager manager, Entity other) {
		// TODO Auto-generated method stub
		
	}

}
