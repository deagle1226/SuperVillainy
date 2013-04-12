package com.supervillainy.game.ai;

import com.supervillainy.game.ai.commands.Command;
import com.supervillainy.game.ai.minions.Minion;
import com.supervillainy.game.ai.minions.Squad;
import com.supervillainy.game.ai.tasks.*;

public class Decision {
	
	public Task task;
	private CommandQueue commands;
	private Squad squad;
	
	public Decision(CommandQueue c, Squad s){
		this(new WaitTask(), c, s);
	}
	
	public Decision(Task t, CommandQueue c, Squad s){
		this.task = t;
		this.commands = c;
		squad = s;
	}
	
	public void update() {
		task.update();
		if (!task.isActive()){
			changeTask();
		}
	}
	
	public void changeTask(){
		squad.addXp(task.getReward());
		if (commands.getCommands().size()==0){
			task = new WaitTask();
		} else {
			Command c = commands.pop();
			task = c.parse(task);
		}
	}

}
