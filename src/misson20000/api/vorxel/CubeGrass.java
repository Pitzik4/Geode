package misson20000.api.vorxel;

import misson20000.api.vorxel.renders.Render;

public class CubeGrass extends Cube {
	@Override
	public Render getRender() {
		return Vorxel.renderGrass;
	}
}
