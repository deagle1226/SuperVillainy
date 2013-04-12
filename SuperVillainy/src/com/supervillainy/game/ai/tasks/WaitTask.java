package com.supervillainy.game.ai.tasks;

import com.supervillainy.game.GameWindow;

public class WaitTask implements Task {
	
	public static final String NAME = "Waiting";
	
	private int count;
	
	private boolean active = true;

	@Override
	public void update() {
		count++;
		if (count > 1 * GameWindow.FRAME_CAP){
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

	@Override
	public int getReward() {
		return 0;
	}

}
