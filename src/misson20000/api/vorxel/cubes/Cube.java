package misson20000.api.vorxel.cubes;

import misson20000.api.vorxel.renders.Render;

import org.newdawn.slick.opengl.Texture;

public abstract class Cube {
	public static final Cube air = new CubeAir();
	public static final Cube grass = new CubeGrass();
	public static final Cube dirt = new CubeDirt();
	public static final Cube water = new CubeWater();

	public Texture texture;
	public Texture toptexture;
	public int x;
	public int y;
	public int z;

	public Render getRender() {
		// TODO Auto-generated method stub
		return Render.renderStandardCube;
	}
	public abstract boolean isSolid();
}
