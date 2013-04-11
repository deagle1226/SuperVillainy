package com.supervillainy.game.ai.commands;

import com.supervillainy.game.ai.tasks.BattleTask;
import com.supervillainy.game.ai.tasks.Task;

public class BattleCommand extends Command {
	
	public BattleCommand(){
		importance = 2;
	}

	@Override
	public Task parse(Task task) {
		return new BattleTask();
	}
}
