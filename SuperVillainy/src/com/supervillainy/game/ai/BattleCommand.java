package com.supervillainy.game.ai;

public class BattleCommand extends Command {
	
	public BattleCommand(){
		importance = 1;
	}

	@Override
	public Task parse(Task task) {
		return new BattleTask();
	}
}
