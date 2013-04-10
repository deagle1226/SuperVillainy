package com.supervillainy.game.gui;

import com.supervillainy.game.GameWindow;

public class StartMenuButton extends Button {
	
	private String target;

	public StartMenuButton(String text, int x, int y, int dimx, int dimy, String target) {
		super(text, x, y, dimx, dimy);
		this.target = target;
	}

	@Override
	public void action(GameWindow window, int delta) {
		window.changeToState(target);
	}

}
