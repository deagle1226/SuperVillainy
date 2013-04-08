package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.PowerState;
import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.texture.TextureLoader;

public class SpeedPassive extends PassivePower {
	
	public static final String NAME = "Speed Boost";

	@Override
	public void init(TextureLoader loader) throws IOException {
		hotkey = Keyboard.KEY_5;
	}

	@Override
	public void render() {
		
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void effect(EntityManager manager, int delta) {
		if (PowerState.selected.getName().equalsIgnoreCase(NAME)) {
			player.SPEED = 20;
		} else {
			player.SPEED = 10;
		}
		
	}

}
