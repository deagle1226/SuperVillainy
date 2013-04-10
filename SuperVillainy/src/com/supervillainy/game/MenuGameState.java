package com.supervillainy.game;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public abstract class MenuGameState implements GameState {
	
	protected Texture background;
	protected BitmapFont font;
	protected String[] options;
	protected int selected;
	
	protected String bgPath;
	protected String fontPath;
	protected int fontSize;
	
	protected String menuTitle;

	@Override
	public void init(GameWindow window) throws IOException {
		TextureLoader loader = new TextureLoader();
		background = loader.getTexture(bgPath);
		
		Texture fontTexture = loader.getTexture(fontPath);
		font = new BitmapFont(fontTexture, fontSize, fontSize);
	}

	@Override
	public void render(GameWindow window, int delta) {
		GL11.glColor3f(0.8f,0.8f,0.8f);
		drawBackground(window);
		
		window.enterOrtho();

		GL11.glColor3f(1f,1f,1f);
		font.drawString(1, menuTitle, GameWindow.WINDOW_WIDTH/2-200,  GameWindow.WINDOW_HEIGHT/2-200);
		
		for (int i=0;i<options.length;i++) {
			GL11.glColor3f(0.5f,0.5f,0);
			if (selected == i) {
				GL11.glColor3f(1,1,0.3f);
			}
			font.drawString(0, options[i], GameWindow.WINDOW_WIDTH/2-200, GameWindow.WINDOW_HEIGHT/2-100+(i*50));
		}
		
		window.leaveOrtho();
	}
	
	private void drawBackground(GameWindow window) {
		window.enterOrtho();
		
		float scale = 1f;
		background.bind();
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(0,0);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(0,GameWindow.WINDOW_HEIGHT*scale);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(GameWindow.WINDOW_WIDTH*scale,GameWindow.WINDOW_HEIGHT*scale);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(GameWindow.WINDOW_WIDTH*scale,0);
		GL11.glEnd();
		
		window.leaveOrtho();
	}

	@Override
	public void update(GameWindow window, int delta) {
		while (Keyboard.next()){
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
					selected--;
					if (selected < 0) {
						selected = options.length - 1;
					}
				} else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
					selected++;
					if (selected >= options.length) {
						selected = 0;
					}
				} else {
					selectOption(window);
				}
			}
		}
	}
	
	public abstract void selectOption(GameWindow window);

	@Override
	public void enter(GameWindow window) {
	}

	@Override
	public void leave(GameWindow window) {
	}

}
