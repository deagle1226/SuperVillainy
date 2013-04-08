package com.supervillainy.game;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public class PowerSelectMenu extends MenuGameState {
	
	public static final String NAME = "power_menu";	

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void init(GameWindow window) throws IOException {
		options = PowerState.powers;
		
		bgPath = "res/bg.jpg";
		fontPath = "res/font.png";
		fontSize = 32;
		
		super.init(window);
	}

	@Override
	public void render(GameWindow window, int delta) {
		menuTitle = "Power Select";
		
		super.render(window, delta);
	}

	@Override
	public void update(GameWindow window, int delta) {
		super.update(window, delta);
	}
	
	@Override 
	public void selectOption(GameWindow window) {
		if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
			if (selected == PowerState.TECH) {
				PowerState.setState(PowerState.TECH);
			} else if (selected == PowerState.MUTATION) {
				PowerState.setState(PowerState.MUTATION);
			} else if (selected == PowerState.MAGIC) {
				PowerState.setState(PowerState.MAGIC);
			} else if (selected == PowerState.NATURAL) {
				PowerState.setState(PowerState.NATURAL);
			}
			window.changeToState(BattleState.NAME);
		}
	}

	@Override
	public void enter(GameWindow window) {
		super.enter(window);
	}

	@Override
	public void leave(GameWindow window) {
		super.leave(window);
	}

}
