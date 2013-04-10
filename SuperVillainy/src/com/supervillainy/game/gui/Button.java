package com.supervillainy.game.gui;

import java.io.IOException;

import org.lwjgl.opengl.GL11;

import com.supervillainy.game.GameWindow;
import com.supervillainy.game.texture.Texture;
import com.supervillainy.game.texture.TextureLoader;

public abstract class Button {
	
	protected String text;
	protected int x, y, dimx, dimy;
	private BitmapFont font;
	
	public Button(String text, int x, int y, int dimx, int dimy){
		this.text = text;
		this.x = x;
		this.y = y;
		this.dimx = dimx;
		this.dimy = dimy;
		TextureLoader loader = new TextureLoader();
		Texture fontTex = null;
		try {
			fontTex = loader.getTexture("res/font.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		font = new BitmapFont(fontTex, 32, 32);
		
	}
	
	public void render(GameWindow window, int delta) {
		// Clear the screen and depth buffer
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		
		// turn blending on so characters are displayed above the
		// scene
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(1f,1f,1.0f);
		
		// draw quad
//		GL11.glBegin(GL11.GL_QUADS);
//			GL11.glVertex2i(x,y);
//		    GL11.glVertex2i(x+dimx,y);
//		    GL11.glVertex2i(x+dimx,y+dimy);
//		    GL11.glVertex2i(x,y+dimy);
//		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_QUADS);
	        GL11.glVertex2f(100,100);
			GL11.glVertex2f(100+200,100);
			GL11.glVertex2f(100+200,100+200);
			GL11.glVertex2f(100,100+200);
	    GL11.glEnd();
    
		// reset the blending
		GL11.glDisable(GL11.GL_BLEND);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		font.drawString(0, text, x, y);
	}
	
	public abstract void action(GameWindow window, int delta);

}
