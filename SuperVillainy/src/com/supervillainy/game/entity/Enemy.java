package com.supervillainy.game.entity;

import org.lwjgl.opengl.GL11;

import com.supervillainy.game.model.ObjModel;
import com.supervillainy.game.texture.Texture;

public class Enemy extends AbstractEntity {
	
	/** The texture to apply to the model */
	private Texture texture;
	/** The model to be displayed for the player */
	private ObjModel model;
	
	protected int health;
	protected float scale;
	protected float rotation;
	
	public Enemy(Texture texture, ObjModel model, float x, float y) {
		this(texture, model, x, y, (float) (-4 + (Math.random() * 8)), (float) (-4 + (Math.random() * 8)));
	}
	
	public Enemy(Texture texture, ObjModel model, float x, float y, float vx, float vy){
		this.texture = texture;
		this.model = model;
		
		velocityX = vx;
		velocityY = vy;
		positionX = x;
		positionY = y;
	}
	
	@Override
	public void update(EntityManager manager, int delta){
		super.update(manager, delta);
		
		if (health <= 0){
			manager.enemyKilled();
			manager.removeEntity(this);
		}
	}

	@Override
	public void render() {
		// enable lighting over the rock model
		GL11.glEnable(GL11.GL_LIGHTING);
		
		// store the original matrix setup so we can modify it
		// without worrying about effecting things outside of this 
		// class
		GL11.glPushMatrix();

		// position the model based on the players currently game
		// location
		GL11.glTranslatef(positionX,positionY,0);

		// rotate the rock round to its current Z axis rotate
		GL11.glRotatef(rotation,0,0,1);
		
		// scale the model based on the size of rock we're representing
		GL11.glScalef(scale, scale, scale);
		
		// bind the texture we want to apply to our rock and then
		// draw the model 
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
		if (other instanceof Shot){
			health--;
		}
		velocityX = (getX() - other.getX());
		velocityY = (getY() - other.getY());
	}

}
