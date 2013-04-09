package com.supervillainy.game.ai;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.supervillainy.game.ai.commands.Command;

public class CommandQueue {
	
	public static PriorityQueue<Command> commands;
	
	public CommandQueue(){
		commands = new PriorityQueue<Command>();
	}
	
	public static void add(Command c){
		commands.add(c);
	}
	
	public static Command pop(){
		return commands.poll();
	}

}
