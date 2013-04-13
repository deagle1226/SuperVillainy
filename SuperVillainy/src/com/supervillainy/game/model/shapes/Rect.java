package com.supervillainy.game.model.shapes;

import org.lwjgl.opengl.GL11;

import com.supervillainy.game.GameWindow;

public class Rect {
	
	private int x, y, dimx, dimy;
	private float r, g, b, a;
	
	public Rect(int x, int y, int dimx, int dimy, float r, float g, float b, float a){
		this.x = x;
		this.y = y;
		this.dimx = dimx;
		this.dimy = dimy;
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public void render(GameWindow window){
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		window.enterOrtho();
		
		GL11.glColor4f(r, g, b, a);
		
		
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(x,y);
		    GL11.glVertex2f(x+dimx,y);
		    GL11.glVertex2f(x+dimx,y+dimy);
		    GL11.glVertex2f(x,y+dimy);
	    GL11.glEnd();
		
		window.leaveOrtho();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	}

}
