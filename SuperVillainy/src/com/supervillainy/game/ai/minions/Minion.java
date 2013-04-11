package com.supervillainy.game.ai.minions;

import com.supervillainy.game.ai.AI;
import com.supervillainy.game.ai.Decision;

public abstract class Minion implements AI, Comparable<Minion> {
	
	private Decision decision;
	protected int rank;
	
	public void update(Decision d) {
		decision = d;
		decision.update();
	}
	
	@Override
	public int compareTo(Minion m) {
		return Double.compare(rank, m.rank);
	}
	
	public String toString() {
		return decision.task.getName();
	}

}
