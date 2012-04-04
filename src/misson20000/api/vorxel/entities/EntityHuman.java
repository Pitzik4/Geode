package misson20000.api.vorxel.entities;

import java.util.Random;

import misson20000.api.vorxel.models.Model;
import misson20000.api.vorxel.models.ModelHuman;

public class EntityHuman extends Entity {
	private	Model model = new ModelHuman();
	private Random ai = new Random();
	
	@Override
	public Model getModel() {
		return model;
	}
	
	@Override
	public void onTick() {
		if((ai.nextInt(8))==0) { //1/8 chance
			x += 0.2;
		}
		if((ai.nextInt(8))==0) { //1/8 chance
			z += 0.2;
		}
	}
}
