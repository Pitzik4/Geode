package misson20000.api.vorxel.models;

import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

public class Box {
	public Texture top;
	public Texture bottom;
	public Texture front;
	public Texture back;
	public Texture left;
	public Texture right;
	public Texture texture;
	public Face[] faces = new Face[6];
	public float pitch = 0;
	public float yaw = 0;
	public float twist = 0;
	
	public void render() {
		glRotatef(pitch, 1, 0, 0);
		glRotatef(yaw, 0, 1, 0);
		glRotatef(twist, 0, 0, 1);
		faces[0].render();
		faces[1].render();
		faces[2].render();
		faces[3].render();
		faces[4].render();
		faces[5].render();
		glRotatef(0-pitch, 1, 0, 0);
		glRotatef(0-yaw, 0, 1, 0);
		glRotatef(0-twist, 0, 0, 1);
	}
	public void init(float x, float y, float z, float w, float h, float d) {
		faces[0] = new Face(EnumFaceSide.TOP, top, w, h, d, x, y, z);
		faces[1] = new Face(EnumFaceSide.BOTTOM, bottom, w, h, d, x, y, z);
		faces[2] = new Face(EnumFaceSide.FRONT, front, w, h, d, x, y, z);
		faces[3] = new Face(EnumFaceSide.BACK, back, w, h, d, x, y, z);
		faces[4] = new Face(EnumFaceSide.LEFT, left, w, h, d, x, y, z);
		faces[5] = new Face(EnumFaceSide.RIGHT, right, w, h, d, x, y, z);
	}
	public void init(double x, double y, double z, double d, double e, double f) {
		init((float)x, (float)y, (float)z, (float)d, (float)e, (float)f);
	}
}
