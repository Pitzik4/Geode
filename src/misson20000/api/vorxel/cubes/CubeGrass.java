package misson20000.api.vorxel.cubes;

import misson20000.api.vorxel.renders.Render;

public class CubeGrass extends Cube {
	@Override
	public Render getRender() {
		return Render.renderGrass;
	}

	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return true;
	}
}
