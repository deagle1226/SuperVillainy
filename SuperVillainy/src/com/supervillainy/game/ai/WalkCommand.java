package com.supervillainy.game.ai;

public class WalkCommand extends Command {
	
	public WalkCommand(){
		importance = 2;
	}

	@Override
	public Task parse(Task task) {
		return new WalkTask();
	}

}
