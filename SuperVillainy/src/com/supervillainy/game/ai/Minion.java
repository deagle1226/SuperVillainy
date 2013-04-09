package com.supervillainy.game.ai;

public abstract class Minion implements AI, Comparable<Minion> {
	
	protected Decision decision;
	protected int rank;
	
	public void update() {
		decision.update();
	}
	
	@Override
	public int compareTo(Minion m) {
		return Double.compare(rank, m.rank);
	}

}
