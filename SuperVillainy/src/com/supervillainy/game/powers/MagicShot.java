package com.supervillainy.game.powers;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.supervillainy.game.texture.TextureLoader;

public class MagicShot extends ShootPower {
	
public static final String NAME = "Magic Shot";
	
	@Override
	public void init(TextureLoader loader) throws IOException {
		super.init(loader);
		
		hotkey = Keyboard.KEY_2;
		r = 0f;
		g = 0f;
		b = 1f;
	}
	
	@Override
	public String getName() {
		return NAME;
	}

}
