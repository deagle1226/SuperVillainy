package com.supervillainy.game.ai.commands;

import com.supervillainy.game.ai.tasks.Task;
import com.supervillainy.game.ai.tasks.WalkTask;

public class WalkCommand extends Command {
	
	public WalkCommand(){
		importance = 1;
	}

	@Override
	public Task parse(Task task) {
		return new WalkTask();
	}

}
