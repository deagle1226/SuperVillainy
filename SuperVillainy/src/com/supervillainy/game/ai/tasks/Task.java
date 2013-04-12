package com.supervillainy.game.ai.tasks;

public interface Task {
	
	public void update();
	
	public boolean isActive();
	
	public String getName();
	
	public int getReward();
	
}
