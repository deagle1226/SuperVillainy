package com.supervillainy.game.ai;

import com.supervillainy.game.ai.commands.Command;
import com.supervillainy.game.ai.tasks.Task;
import com.supervillainy.game.ai.tasks.WaitTask;

public class Decision {
	
	public Task task;
	
	public Decision(){
		this(new WaitTask());
	}
	
	public Decision(Task t){
		this.task = t;
	}
	
	public void update() {
		task.update();
		if (!task.isActive()){
			changeTask();
		}
	}
	
	public void changeTask(){
		if (CommandQueue.commands.size()==0){
			task = new WaitTask();
		} else {
			Command c = CommandQueue.pop();
			task = c.parse(task);
		}
	}

}
