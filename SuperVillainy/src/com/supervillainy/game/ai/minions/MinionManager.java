package com.supervillainy.game.ai.minions;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinionManager {
	
	private PriorityQueue<Minion> minions;
	private int count = 0;
	
	public MinionManager(){
		minions = new PriorityQueue<Minion>();
	}
	
	public void add(Minion m){
		minions.add(m);
	}
	
	public void update(){
		for (Minion m : minions){
			m.update();
		}
		count++;
		if (count > 60){
			count = 0;
		}
	}
	
	public PriorityQueue<Minion> getMinions(){
		return minions;
	}

}
