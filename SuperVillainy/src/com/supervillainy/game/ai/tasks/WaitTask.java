package com.supervillainy.game.ai.tasks;

public class WaitTask implements Task {
	
	public static final String NAME = "Waiting";
	
	private int count;
	
	private boolean active = true;

	@Override
	public void update() {
		count++;
		if (count < 5){
			active = false;
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
