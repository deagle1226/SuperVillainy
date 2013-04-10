package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Shot;
import com.supervillainy.game.texture.TextureLoader;

public class TechShot extends ShootPower {
	
	public static final String NAME = "Tech Shot";
	private float defl1 = 0.3f;
	private float defl2 = (float) (Math.PI*2 - defl1);
	
	@Override
	public void init(TextureLoader loader) throws IOException {
		super.init(loader);
		
		hotkey = Keyboard.KEY_4;
		r = 1.0f;
		g = 0f;
		b = 0f;
		shotInterval = 50;
		speed = 30;
	}

	@Override
	public String getName() {
		return NAME;
	}
	
	@Override
	public void effect(EntityManager manager, int delta){
		super.effect(manager, delta);
		Shot shot = new Shot(texture, 
				 player.getX() + player.forwardX, 
				 player.getY() + player.forwardY, 
				 (float) ((player.forwardX*Math.cos(defl2) - player.forwardY*Math.sin(defl2)) * player.SHOT_SPEED), 
				 (float) ((player.forwardX*Math.sin(defl2) + player.forwardY*Math.cos(defl2)) * player.SHOT_SPEED),
				 r, g, b);
		manager.addEntity(shot);
		shot = new Shot(texture, 
				 player.getX() + player.forwardX, 
				 player.getY() + player.forwardY, 
				 (float) ((player.forwardX*Math.cos(defl1) - player.forwardY*Math.sin(defl1))  * player.SHOT_SPEED), 
				 (float) ((player.forwardX*Math.sin(defl1) + player.forwardY*Math.cos(defl1)) * player.SHOT_SPEED),
				 r, g, b);
		manager.addEntity(shot);
	}

}
