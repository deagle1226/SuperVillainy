package com.supervillainy.game.ai.minions;

import java.util.ArrayList;

import com.supervillainy.game.ai.Decision;
import com.supervillainy.game.ai.minions.abilities.Ability;
import com.supervillainy.game.ai.minions.abilities.ThugAbility;

public class Thug extends Minion {
	
	public Thug(int xp, ArrayList<Ability> abilities){
		super(xp, abilities);
		rank = 2;
		experienceCap = 300;
		abilities.add(new ThugAbility());
	}

	@Override
	public Minion nextRank() {
		return new Captain(experience - experienceCap, abilities);
	}
	

}
