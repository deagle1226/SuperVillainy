package com.supervillainy.game.ai.minions;

import com.supervillainy.game.ai.Decision;

public class Goon extends Minion {
	
	public Goon(){
		super();
		rank = 1;
		experienceCap = 200;
	}

	@Override
	public Minion nextRank() {
		return new Thug(experience - experienceCap, abilities);
	}
	
	


}
