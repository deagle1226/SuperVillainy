package com.supervillainy.game.ai;

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
