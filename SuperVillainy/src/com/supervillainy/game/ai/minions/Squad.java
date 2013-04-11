package com.supervillainy.game.ai.minions;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.supervillainy.game.ai.CommandQueue;
import com.supervillainy.game.ai.Decision;
import com.supervillainy.game.ai.commands.Command;
import com.supervillainy.game.ai.tasks.Task;
import com.supervillainy.game.ai.tasks.WaitTask;

public class Squad {
	
	public static final int SIZE = 3;
	
	private MinionManager manager;
	private Decision decision;
	private CommandQueue commands;
	
	public Squad() {
		manager = new MinionManager();
		commands = new CommandQueue();
		decision = new Decision(commands);
	}
	
	public void add(Minion m){
		if (manager.getMinions().size() < SIZE){
			manager.add(m);
		} else {
			System.out.println("squad is full");
		}
	}
	
	public PriorityQueue<Minion> getMinions() {
		return manager.getMinions();
	}
	
	public void update() {
		manager.update(decision);
	}
	
	public void command(Command c){
		commands.add(c);
	}
	
	public CommandQueue getCommands(){
		return commands;
	}

}
