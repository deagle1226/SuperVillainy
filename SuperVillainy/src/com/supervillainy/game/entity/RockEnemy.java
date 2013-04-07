package com.supervillainy.game.entity;

import com.supervillainy.game.model.ObjModel;
import com.supervillainy.game.texture.Texture;

public class RockEnemy extends Enemy {
	
	private float spin;

	public RockEnemy(Texture texture, ObjModel model, float x, float y) {
		super(texture, model, x, y);
		health = 5;
		scale = 2f;
		rotation = 0f;
		spin = 0.2f;
	}
	
	@Override
	public void update(EntityManager manager, int delta){
		super.update(manager, delta);
		rotation += spin;
	}

}
