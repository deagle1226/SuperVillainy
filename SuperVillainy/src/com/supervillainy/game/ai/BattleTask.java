package com.supervillainy.game.ai;

public class BattleTask implements Task {
	
	public static final String NAME = "Fighting";
	
	private int count = 0;
	
	private boolean active = true;

	@Override
	public void update() {
		count++;
		if(count > 600){
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
