package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.texture.TextureLoader;

public class MutationShot extends ShootPower {
	
public static final String NAME = "Mutation Shot";
	
	@Override
	public void init(TextureLoader loader) throws IOException {
		super.init(loader);
		
		hotkey = Keyboard.KEY_3;
		r = 0f;
		g = 1.0f;
		b = 0f;
		shotInterval = 150;
		speed = 40;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

}
