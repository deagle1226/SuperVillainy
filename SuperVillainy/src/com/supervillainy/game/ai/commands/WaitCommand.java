package com.supervillainy.game.ai.commands;

import com.supervillainy.game.ai.tasks.Task;
import com.supervillainy.game.ai.tasks.WaitTask;

public class WaitCommand extends Command {
	
	public WaitCommand(){
		importance = 0;
	}

	@Override
	public Task parse(Task task) {
		return new WaitTask();
	}
	

}
