package com.supervillainy.game;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.ai.CommandQueue;
import com.supervillainy.game.ai.commands.BattleCommand;
import com.supervillainy.game.ai.commands.Command;
import com.supervillainy.game.ai.commands.WalkCommand;
import com.supervillainy.game.ai.minions.Goon;
import com.supervillainy.game.ai.minions.Minion;
import com.supervillainy.game.ai.minions.MinionManager;
import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public class HiveState implements GameState {
	
	public static final String NAME = "HiveState";
	
	private Texture background;
	private BitmapFont font;
	private int fontSize;
	
	private MinionManager manager;
	private CommandQueue commands;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void init(GameWindow window) throws IOException {
		TextureLoader loader = new TextureLoader();
		background = loader.getTexture("res/bg.png");
		
		fontSize = 32;
		Texture fontTexture = loader.getTexture("res/font.png");
		font = new BitmapFont(fontTexture, fontSize, fontSize);
		
		manager = new MinionManager();
		manager.add(new Goon());
		manager.add(new Goon());
		manager.add(new Goon());
		manager.add(new Goon());
		
		commands = new CommandQueue();
	}

	@Override
	public void render(GameWindow window, int delta) {
		GL11.glColor3f(0.2f,0.2f,0.3f);
		drawBackground(window);
		
		window.enterOrtho();

		GL11.glColor3f(1f,1f,1f);

		int i = 0;
		for (Minion m : manager.getMinions()){
			GL11.glColor3f(0.5f,0.5f,0);
			font.drawString(0, i + " " + m.toString(), GameWindow.WINDOW_WIDTH/2-200, GameWindow.WINDOW_HEIGHT/2-300+(i*50));
			i++;
		}
		i = 0;
		for (Command c : commands.commands) {
			GL11.glColor3f(0.5f,0.5f,0);
			font.drawString(0, i + " " + c.toString(), GameWindow.WINDOW_WIDTH/2-200, GameWindow.WINDOW_HEIGHT/2+(i*50));
			i++;
		}
		
		window.leaveOrtho();
	}
	
	private void drawBackground(GameWindow window) {
		window.enterOrtho();
		
		background.bind();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2i(0,0);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2i(0,GameWindow.WINDOW_HEIGHT);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2i(GameWindow.WINDOW_WIDTH,GameWindow.WINDOW_HEIGHT);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2i(GameWindow.WINDOW_WIDTH,0);
		GL11.glEnd();
		
		window.leaveOrtho();
	}

	@Override
	public void update(GameWindow window, int delta) {
		manager.update();
		while (Keyboard.next()){
			if (Keyboard.getEventKeyState()){
				if (Keyboard.getEventKey() == Keyboard.KEY_1){
					commands.add(new BattleCommand());
				} else if (Keyboard.getEventKey() == Keyboard.KEY_2){
					commands.add(new WalkCommand());
				} else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					window.changeToState(StartMenu.NAME);
				}
			}
		}
		//System.out.println(commands.commands.size());
	}

	@Override
	public void enter(GameWindow window) {
	}

	@Override
	public void leave(GameWindow window) {
	}

}
