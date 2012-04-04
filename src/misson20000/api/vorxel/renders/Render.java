package misson20000.api.vorxel.renders;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import misson20000.api.vorxel.Location;
import misson20000.api.vorxel.Vorxel;
import misson20000.api.vorxel.cubes.Cube;
import misson20000.api.vorxel.cubes.CubeWater;
import misson20000.api.vorxel.entities.Entity;

public abstract class Render {
	public static final Render renderStandardCube = new RenderStandardCube();
	public static final Render renderNothing = new RenderNothing();
	public static final Render renderGrass = new RenderGrass();
	public static final Render renderWater = new RenderWater();
	
	public abstract void render(Cube c);
	public static void renderCube(Cube c) {
		c.getRender().render(c);
	}
	public static void renderArray(Cube[][][] cubes) {
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y<16; y++) {
				for(int z = 0; z<16; z++) {
					//Vorxel.namemap.put(Vorxel.nextName, new Location(x, y, z));
					//GL11.glPushName(Vorxel.nextName);
					renderCube(cubes[x][y][z]);
					//GL11.glPopName();
					GL11.glTranslatef(0, 0, 1);
					//Vorxel.nextName++;
				}
				GL11.glTranslatef(0, 1, -16);
			}
			GL11.glTranslatef(1, -16, 0);
		}
	}
	
	public static void renderEntities(ArrayList<Entity> list) {
		for(int i = 0; i < list.size(); i++) {
			RenderEntity.renderEntity(list.get(i));
		}
	}
}
