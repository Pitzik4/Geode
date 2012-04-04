package misson20000.api.vorxel.cubes;

import misson20000.api.vorxel.renders.Render;

public class CubeWater extends Cube {
	@Override
	public Render getRender() {
		return Render.renderWater;
	}
	@Override
	public boolean isSolid() {
		// TODO Auto-generated method stub
		return false;
	}

}
