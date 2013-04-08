package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.texture.TextureLoader;

public class NaturalShot extends ShootPower {
	
	public static final String NAME = "Natural Shot";
	
	@Override
	public void init(TextureLoader loader) throws IOException {
		super.init(loader);
		
		hotkey = Keyboard.KEY_1;
		r = 0f;
		g = 1.0f;
		b = 1.0f;
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}
