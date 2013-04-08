package com.supervillainy.game;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public class StartMenu extends MenuGameState {
	
	public static final String NAME = "start_menu";
	private int START = 0;
	private int EXIT = 1;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void init(GameWindow window) throws IOException {
		options = new String[] {"Start Game", "Exit"};

		bgPath = "res/bg.jpg";
		fontPath = "res/font.png";
		fontSize = 32;
		
		super.init(window);
	}

	@Override
	public void render(GameWindow window, int delta) {
		menuTitle = "SUPER VILLAINY";
		
		super.render(window, delta);
	}

	@Override
	public void update(GameWindow window, int delta) {
		super.update(window, delta);
	}
	
	@Override
	public void selectOption(GameWindow window) {
		if (Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
			if (selected == START) {
				window.changeToState(PowerSelectMenu.NAME);
			} else if (selected == EXIT) {
				System.exit(0);
			}
		}
	}

	@Override
	public void enter(GameWindow window) {
	}

	@Override
	public void leave(GameWindow window) {
	}

}
