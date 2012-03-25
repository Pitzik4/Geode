package misson20000.api.vorxel.renders;

import org.lwjgl.opengl.GL11;

import misson20000.api.vorxel.cubes.Cube;

public abstract class Render {
	public static final Render renderStandardCube = new RenderStandardCube();
	public static final Render renderNothing = new RenderNothing();
	public static final Render renderGrass = new RenderGrass();
	
	public abstract void render(Cube c);
	public static void renderCube(Cube c) {
		c.getRender().render(c);
	}
	public static void renderArray(Cube[][][] cubes) {
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y<16; y++) {
				for(int z = 0; z<16; z++) {
					renderCube(cubes[x][y][z]);
					GL11.glTranslatef(0, 0, 1);
				}
				GL11.glTranslatef(0, 1, -16);
			}
			GL11.glTranslatef(1, -16, 0);
		}
	}
}
