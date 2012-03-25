package misson20000.api.vorxel;

import misson20000.api.vorxel.renders.Render;

import org.newdawn.slick.opengl.Texture;

public class Cube {

	public Texture texture;
	public Texture toptexture;

	public Render getRender() {
		// TODO Auto-generated method stub
		return Vorxel.renderStandardCube;
	}

}
