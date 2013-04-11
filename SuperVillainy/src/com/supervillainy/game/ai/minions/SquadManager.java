package com.supervillainy.game.ai.minions;

import java.util.ArrayList;

public class SquadManager {
	
	private ArrayList<Squad> squads;
	
	public SquadManager(){
		squads = new ArrayList<Squad>();
	}
	
	public void add(Squad q){
		squads.add(q);
	}
	
	public void update() {
		for (Squad s : squads){
			s.update();
		}
	}
	
	public ArrayList<Squad> getSquads(){
		return squads;
	}

}
