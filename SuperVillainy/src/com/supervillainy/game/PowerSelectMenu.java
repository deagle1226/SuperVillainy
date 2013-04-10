package com.supervillainy.game;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.powers.Power;
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
		options = new String[PowerState.powers.size()];
		for (int i = 0; i < PowerState.powers.size(); i++){
			options[i] = PowerState.powers.get(i).getName();
		}
		
		bgPath = "res/bg.png";
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
			PowerState.selected = PowerState.powers.get(selected);
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
