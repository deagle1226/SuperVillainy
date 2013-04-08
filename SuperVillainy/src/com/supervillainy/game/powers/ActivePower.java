package com.supervillainy.game.powers;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.PowerState;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.entity.Player;
import com.supervillainy.game.texture.Texture;

public abstract class ActivePower implements Power {
	
	protected int hotkey;
	protected Texture texture;
	
	protected Player player;
	
	public ActivePower(){
	}
	
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
