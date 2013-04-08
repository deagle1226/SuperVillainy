package com.supervillainy.game.powers;

import java.io.IOException;

import com.supervillainy.game.entity.EntityManager;
import com.supervillainy.game.texture.TextureLoader;

public interface Power {
	
	public String getName();
	
	public void init(TextureLoader loader) throws IOException;
	
	public void update(EntityManager manager, int delta);
	
	public void render();
	
	public void effect(EntityManager manager, int delta);

}
