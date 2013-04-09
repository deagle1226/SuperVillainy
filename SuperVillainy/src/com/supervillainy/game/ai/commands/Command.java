package com.supervillainy.game.ai.commands;

import com.supervillainy.game.ai.tasks.Task;

public abstract class Command implements Comparable<Command> {
	
	protected int importance;

	public int compareTo(Command o) {
		return Double.compare(importance(), o.importance());
	}

	public abstract Task parse(Task task);
	
	public int importance(){
		return importance;
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
}
