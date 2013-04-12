package com.supervillainy.game.ai.minions;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.supervillainy.game.ai.Decision;

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
		levelUp();
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
	
	public void levelUp(){
		String str = "";
		PriorityQueue<Minion> temp = new PriorityQueue<Minion>();
		for (Minion m : minions){
			str += m.experience + " ";
			if (m.experience >= m.getXpCap()){
				temp.add(m.nextRank());
			} else {
				temp.add(m);
			}
		}
		System.out.println(str);
		minions = temp;
	}

}
