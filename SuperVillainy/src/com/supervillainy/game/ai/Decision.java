package com.supervillainy.game.ai;

import com.supervillainy.game.ai.commands.Command;
import com.supervillainy.game.ai.tasks.*;

public class Decision {
	
	public Task task;
	private CommandQueue commands;
	
	public Decision(CommandQueue c){
		this(new WaitTask(), c);
	}
	
	public Decision(Task t, CommandQueue c){
		this.task = t;
		this.commands = c;
	}
	
	public void update() {
		task.update();
		if (!task.isActive()){
			changeTask();
		}
	}
	
	public void changeTask(){
		if (commands.getCommands().size()==0){
			task = new WaitTask();
		} else {
			Command c = commands.pop();
			task = c.parse(task);
		}
	}

}
