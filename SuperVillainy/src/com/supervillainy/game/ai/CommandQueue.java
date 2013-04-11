package com.supervillainy.game.ai;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.supervillainy.game.ai.commands.Command;

public class CommandQueue {
	
	private PriorityQueue<Command> commands;
	
	public CommandQueue(){
		commands = new PriorityQueue<Command>();
	}
	
	public void add(Command c){
		for (Command x : commands){
			if (x.toString().equalsIgnoreCase(c.toString())) return;
		}
		commands.add(c);
	}
	
	public Command pop(){
		return commands.poll();
	}
	
	public PriorityQueue<Command> getCommands(){
		return commands;
	}

}
