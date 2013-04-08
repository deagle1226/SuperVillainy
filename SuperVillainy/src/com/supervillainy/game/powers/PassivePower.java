package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.PowerState;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.texture.TextureLoader;

public abstract class PassivePower implements Power {
	
	protected Player player;
	protected int hotkey;

	@Override
	public void update(EntityManager manager, int delta) {
		if (Keyboard.isKeyDown(hotkey)){
			PowerState.selected = this;
		}
	}
	
	public void setPlayer(Player p){
		player = p;
	}

}
