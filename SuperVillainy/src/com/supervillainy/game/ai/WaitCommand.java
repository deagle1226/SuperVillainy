package com.supervillainy.game.ai;

public class WaitCommand extends Command {
	
	public WaitCommand(){
		importance = 0;
	}

	@Override
	public Task parse(Task task) {
		return new WaitTask();
	}
	

}
