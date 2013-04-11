package com.supervillainy.game.gui;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.supervillainy.game.GameWindow;
import com.supervillainy.game.HiveState;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public abstract class Button {
	
	protected String text;
	protected int x, y, dimx, dimy;
	private BitmapFont font;
	private Texture background;
	private float color;
	
	public Button(String text, int x, int y, int dimx, int dimy){
		this.text = text;
		this.x = x;
		this.y = y;
		this.dimx = dimx;
		this.dimy = dimy;
		color = 1f;
	}
	
	public void init(BitmapFont font, TextureLoader loader) throws IOException {
		background = loader.getTexture("res/white.png");
		this.font = font;
	}
	
	public void render(GameWindow window, int delta) {	
		
		
		// draw quad
		background.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1f,1f,1f);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2i(x,y);
			GL11.glTexCoord2f(1,1);
		    GL11.glVertex2i(x+dimx,y);
		    GL11.glTexCoord2f(1,0);
		    GL11.glVertex2i(x+dimx,y+dimy);
		    GL11.glTexCoord2f(0,0);
		    GL11.glVertex2i(x,y+dimy);
		GL11.glEnd();
		
		GL11.glColor3f(color,color,color);
		font.drawString(0, text, x, y);
		
	}
	
	public void update(GameWindow window, int delta) {
		int mouseX = Mouse.getX();
		int mouseY = window.WINDOW_HEIGHT-Mouse.getY();
		//System.out.println(mouseX + " " + mouseY);
		color = 1f;
		if (mouseX > x && mouseX < x+dimx){
			if(mouseY > y && mouseY < y+dimy){
				color = 0.7f;
				if (Mouse.next()){
					if(Mouse.getEventButtonState()){
						if(Mouse.getEventButton() == 0){
							action(window, delta);
						}
					}
				}
			}
		}
	}
	
	public abstract void action(GameWindow window, int delta);

}
