package com.supervillainy.game.ai.minions;

import java.util.ArrayList;

import com.supervillainy.game.ai.minions.abilities.Ability;
import com.supervillainy.game.ai.minions.abilities.CaptainAbility;
import com.supervillainy.game.ai.minions.abilities.ThugAbility;

public class Captain extends Minion {
	
	public Captain(int xp, ArrayList<Ability> abilities){
		super(xp, abilities);
		rank = 3;
		experienceCap = 500;
		abilities.add(new CaptainAbility());
	}

	@Override
	public Minion nextRank() {
		return new Captain(experience - experienceCap, abilities);
	}

}
