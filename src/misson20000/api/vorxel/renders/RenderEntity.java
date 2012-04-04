package misson20000.api.vorxel.renders;

import org.lwjgl.opengl.GL11;

import misson20000.api.vorxel.Vorxel;
import misson20000.api.vorxel.entities.Entity;
import misson20000.api.vorxel.models.Box;
import misson20000.api.vorxel.models.Face;

public class RenderEntity {
	public static void renderEntity(Entity ent) {
		GL11.glLoadIdentity();
		Vorxel.lookThrough();
		GL11.glTranslatef(0-ent.x, 0-ent.y, 0-ent.z);
		Box[] boxes = ent.getModel().getBoxes();
		for(Box box : boxes) {
			box.render();
		}
	}
}
