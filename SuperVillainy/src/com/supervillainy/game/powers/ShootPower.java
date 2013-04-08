package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.PowerState;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.entity.Shot;
import com.supervillainy.game.texture.TextureLoader;

public abstract class ShootPower extends ActivePower {

	private int shotInterval = 250;
	
	private int shotTimeout;
	
	protected float r, g, b;

	@Override
	public void init(TextureLoader loader) throws IOException {
		texture = loader.getTexture("res/shot.png");
	}

	@Override
	public void render() {
	}

	@Override
	public void effect(EntityManager manager, int delta) {
		Shot shot = new Shot(texture, 
				 player.getX() + player.forwardX, 
				 player.getY() + player.forwardY, 
				 player.forwardX * player.SHOT_SPEED, 
				 player.forwardY * player.SHOT_SPEED,
				 r, g, b);
		manager.addEntity(shot);
		manager.shotFired();
	}

}
