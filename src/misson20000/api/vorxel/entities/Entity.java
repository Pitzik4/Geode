package misson20000.api.vorxel.entities;

import misson20000.api.vorxel.models.Model;

public abstract class Entity {
	public int x;
	public int y;
	public int z;
	public abstract Model getModel();
	public void onTick() {
		// TODO Auto-generated method stub
		
	}
}
