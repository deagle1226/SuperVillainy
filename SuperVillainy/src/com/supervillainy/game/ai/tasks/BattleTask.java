package com.supervillainy.game.ai.tasks;

import com.supervillainy.game.GameWindow;

public class BattleTask implements Task {
	
	public static final String NAME = "Fighting";
	
	private int count = 0;
	
	private boolean active = true;

	@Override
	public void update() {
		count++;
		if(count > 20 * GameWindow.FRAME_CAP){
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
		return 100;
	}

}
