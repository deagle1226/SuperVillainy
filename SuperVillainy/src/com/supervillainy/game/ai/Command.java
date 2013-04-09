package com.supervillainy.game.ai;

public abstract class Command implements Comparable<Command> {
	
	protected int importance;

	public int compareTo(Command o) {
		return Double.compare(importance(), o.importance());
	}

	public abstract Task parse(Task task);
	
	public int importance(){
		return importance;
	}
	
}
