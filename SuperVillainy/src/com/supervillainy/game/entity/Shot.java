package com.supervillainy.game.entity;

import org.lwjgl.opengl.GL11;

import com.supervillainy.game.PowerState;
import com.supervillainy.game.particles.ParticleGroup;
import com.supervillainy.game.texture.Texture;

/**
 * An entity representing a single shot fired by the player
 * 
 * @author Kevin Glass
 */
public class Shot extends EffectEntity implements Entity {
	/** The texture to applied to the particles building up the shot */
	private Texture texture;
	/** The size of the initial particles building up this shot */
	private float size = 0.65f;
	/** The amount of time the shot exists for before disappearing */
	private int life = 2000;
	/** The particle engine used to render the shot */
	private ParticleGroup particles;

	/**
	 * Create a new shot at a specified location and with a specified velocity.
	 * 
	 * @param texture
	 *            The texture to apply to the particles building up shot.
	 * @param x
	 *            The initial x position of the shot
	 * @param y
	 *            The initial y position of the shot
	 * @param vx
	 *            The x component of the initial velocity of the shot
	 * @param vy
	 *            The y component of the initial velocity of the shot
	 */
	public Shot(Texture texture, float x, float y, float vx, float vy) {
		positionX = x;
		positionY = y;
		velocityX = vx;
		velocityY = vy;
		this.texture = texture;

		switch (PowerState.state) {
		case 0:
			particles = new ParticleGroup(100, 200, 1, 0, 0);
			break;
		case 1:
			particles = new ParticleGroup(100, 200, 0, 1, 0);
			break;
		case 2:
			particles = new ParticleGroup(100, 200, 0, 0, 1);
			break;
		case 3:
			particles = new ParticleGroup(100, 200, 0, 1, 1);
			break;
		}
	}

	/**
	 * @see org.newdawn.asteroids.entity.Entity#update(org.newdawn.asteroids.entity.EntityManager,
	 *      int)
	 */
	@Override
	public void update(EntityManager manager, int delta) {
		// cause the particle to move by calling the abstract super
		// class's implementation of update
		super.update(manager, delta);

		// update the amount of time left for this shot to exist
		life -= delta;
		if (life < 0) {
			// if the life time has run out then remove the shot
			// entity from the game
			manager.removeEntity(this);
		} else {
			// otherwise add another particle to the engine at the
			// current position and update the particle engine to
			// cause existing particles to fade out
			particles.addParticle(getX(), getY(), size, 200);
			particles.update(delta);
		}
	}

	/**
	 * @see org.newdawn.asteroids.entity.Entity#render()
	 */
	@Override
	public void render() {
		// disable lighting for the shot (its going to glow)
		GL11.glDisable(GL11.GL_LIGHTING);

		// setup the blending for the particle engine. We want
		// all the particles to blend into one big lump. Note we're using
		// a blending function with GL_ONE here to cause the alpha values
		// to max out and give that "glowing" loop
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);

		// bind to the texture for the particles and render the
		// particle engine representing the shot
		texture.bind();
		particles.render();

		// reset blending mode
		GL11.glDisable(GL11.GL_BLEND);
	}

	/**
	 * @see org.newdawn.asteroids.entity.Entity#getSize()
	 */
	@Override
	public float getSize() {
		// the size of our shot
		return 0.6f;
	}

	/**
	 * @see org.newdawn.asteroids.entity.Entity#collide(org.newdawn.asteroids.entity.EntityManager,
	 *      org.newdawn.asteroids.entity.Entity)
	 */
	@Override
	public void collide(EntityManager manager, Entity other) {
		// if the shot hits a rock then we've scored! The rock
		// needs to split apart and then this shot has been used up
		// so remove it.
		if (other instanceof Enemy) {
			// manager.removeEntity(other);
			manager.removeEntity(this);
		}
	}

}
