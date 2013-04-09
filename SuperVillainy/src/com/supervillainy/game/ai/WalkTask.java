package com.supervillainy.game.ai;

public class WalkTask implements Task {
	
	public static final String NAME = "Walking";
	
	private int count = 0;
	
	private boolean active = true;

	@Override
	public void update() {
		count++;
		if(count > 300){
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
