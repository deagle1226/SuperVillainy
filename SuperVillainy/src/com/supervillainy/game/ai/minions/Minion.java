package com.supervillainy.game.ai.minions;

import java.util.ArrayList;

import com.supervillainy.game.ai.AI;
import com.supervillainy.game.ai.Decision;
import com.supervillainy.game.ai.minions.abilities.Ability;

public abstract class Minion implements AI, Comparable<Minion> {
	
	protected int rank;
	protected int experience;
	protected int experienceCap;
	protected ArrayList<Ability> abilities;
	
	public Minion(){
		this(0, new ArrayList<Ability>());
	}
	
	public Minion(int xp){
		this(xp, new ArrayList<Ability>());
	}
	
	public Minion(int xp, ArrayList<Ability> abilities){
		experience = xp;
		this.abilities = abilities;
	}
	
	public void update() {
		for (Ability a : abilities){
			a.update();
		}
	}
	
	@Override
	public int compareTo(Minion m) {
		return Double.compare(rank, m.rank);
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	public void addXp(int xp){
		experience += xp;
	}
	
	public int getXpCap(){
		return experienceCap;
	}
	
	public abstract Minion nextRank();

}
