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
import com.supervillainy.game.ai.minions.Squad;
import com.supervillainy.game.ai.minions.SquadManager;
import com.supervillainy.game.gui.BitmapFont;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public class HiveState implements GameState {
	
	public static final String NAME = "HiveState";
	
	private Texture background;
	private BitmapFont font;
	private int fontSize;
	
	private SquadManager manager;
	
	private int selected;

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
		
		manager = new SquadManager();
		selected = 0;
	}

	@Override
	public void render(GameWindow window, int delta) {
		GL11.glColor3f(0.2f,0.2f,0.3f);
		drawBackground(window);
		
		window.enterOrtho();

		GL11.glColor3f(1f,1f,1f);

		int i = 0;
		for (Squad s : manager.getSquads()){
			String str = "Squad" + i + ": " + s.getDecision().task.getName() + " -> ";
			for (Minion m : s.getMinions()){
				str += m.toString() + " ";
			}
			if (i==selected) GL11.glColor3f(1f,1f,1f);
			else GL11.glColor3f(0.7f,0.7f,0.7f);
			font.drawString(0, str, 10, manager.getSquads().size()*50-(i*50));
			int z = 0;
			for(Command c : s.getCommands().getCommands()){
				font.drawString(0, i + " " + c.toString(), 10, window.WINDOW_HEIGHT/2+(i*100)+(z*30));
				z++;
			}
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
					manager.getSquads().get(selected).command(new BattleCommand());
				} else if (Keyboard.getEventKey() == Keyboard.KEY_2){
					manager.getSquads().get(selected).command(new WalkCommand());
				} else if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					window.changeToState(StartMenu.NAME);
				} else if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
					selected++;
					if (selected > manager.getSquads().size()-1){
						selected = 0;
					}
				} else if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
					selected--;
					if (selected<0){
						selected = manager.getSquads().size()-1;
					}
				}
			}
		}
		//System.out.println(commands.commands.size());
	}

	@Override
	public void enter(GameWindow window) {
		Squad s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		manager.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		s.add(new Goon());
		manager.add(s);
		s = new Squad();
		s.add(new Goon());
		s.add(new Goon());
		manager.add(s);
	}

	@Override
	public void leave(GameWindow window) {
		manager.clear();
	}

}
