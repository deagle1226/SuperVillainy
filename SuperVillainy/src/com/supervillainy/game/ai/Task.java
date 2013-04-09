package com.supervillainy.game.ai;

public interface Task {
	
	public void update();
	
	public boolean isActive();
	
	public String getName();
	
}
