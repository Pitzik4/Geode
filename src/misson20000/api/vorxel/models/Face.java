package misson20000.api.vorxel.models;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class Face {
	public static final int TOP = 0;
	public static final int BOTTOM = 1;
	public static final int FRONT = 2;
	public static final int BACK = 3;
	public static final int LEFT = 4;
	public static final int RIGHT = 5;
	public Texture texture;
	public Point[] points = new Point[4];
	private EnumFaceSide which;
	private float x;
	private float y;
	private float z;
	
	public Face(EnumFaceSide which, Texture tex) {
		texture = tex;
		this.which = which;
		if(which.equals(EnumFaceSide.TOP)) initTop();
		if(which.equals(EnumFaceSide.BOTTOM)) initBottom();
		if(which.equals(EnumFaceSide.LEFT)) initLeft();
		if(which.equals(EnumFaceSide.RIGHT)) initRight();
		if(which.equals(EnumFaceSide.FRONT)) initFront();
		if(which.equals(EnumFaceSide.BACK)) initBack();
	}

	public Face(EnumFaceSide which, Texture tex, float width, float height, float depth, float x, float y, float z) {
		texture = tex;
		this.which = which;
		this.x = x;
		this.y = y;
		this.z = z;
		if(which.equals(EnumFaceSide.TOP)) initTop(width, height, depth);
		if(which.equals(EnumFaceSide.BOTTOM)) initBottom(width, height, depth);
		if(which.equals(EnumFaceSide.LEFT)) initLeft(width, height, depth);
		if(which.equals(EnumFaceSide.RIGHT)) initRight(width, height, depth);
		if(which.equals(EnumFaceSide.FRONT)) initFront(width, height, depth);
		if(which.equals(EnumFaceSide.BACK)) initBack(width, height, depth);
	}

	private void initBack(float width, float height, float depth) {
		points[0] = new Point(x + width, y + height, z);
		points[1] = new Point(x, y + height, z);
		points[2] = new Point(x, y, z);
		points[3] = new Point(x + width, y, z);	
	}

	private void initFront(float width, float height, float depth) {
		points[0] = new Point(x + width, y + height, z + depth);
		points[1] = new Point(x, y + height, z + depth);
		points[2] = new Point(x, y, z + depth);
		points[3] = new Point(x + width, y, z + depth);	
	}

	private void initRight(float width, float height, float depth) {
		points[0] = new Point(width + x, y + height, z + depth);
		points[1] = new Point(width + x, y + height, z);
		points[2] = new Point(width + x, y, z);
		points[3] = new Point(width + x, y, z + depth);	
	}

	private void initLeft(float width, float height, float depth) {
		points[0] = new Point(x, y + height, z + depth);
		points[1] = new Point(x, y + height, z);
		points[2] = new Point(x, y, z);
		points[3] = new Point(x, y, z + depth);
	}

	private void initBottom(float width, float height, float depth) {
		points[0] = new Point(x + width, y, z + depth);
		points[1] = new Point(x, y, z + depth);
		points[2] = new Point(x, y, z);
		points[3] = new Point(x + width, y, z);
	}

	private void initTop(float width, float height, float depth) {
		points[0] = new Point(width + x, y, depth + z);
		points[1] = new Point(x, y, depth + z);
		points[2] = new Point(x, y, z);
		points[3] = new Point(width + x, y, z);		
	}

	private void initRight() {
		points[0] = new Point(1, 1, 1);
		points[1] = new Point(1, 1, 0);
		points[2] = new Point(1, 0, 0);
		points[3] = new Point(1, 0, 1);
	}

	private void initLeft() {
		points[0] = new Point(0, 1, 1);
		points[1] = new Point(0, 1, 0);
		points[2] = new Point(0, 0, 0);
		points[3] = new Point(0, 0, 1);
	}

	private void initBack() {
		points[0] = new Point(1, 1, 0);
		points[1] = new Point(0, 1, 0);
		points[2] = new Point(0, 0, 0);
		points[3] = new Point(1, 0, 0);
	}

	private void initFront() {
		points[0] = new Point(1, 1, 1);
		points[1] = new Point(0, 1, 1);
		points[2] = new Point(0, 0, 1);
		points[3] = new Point(1, 0, 1);
	}

	private void initBottom() {
		points[0] = new Point(1, 0, 1);
		points[1] = new Point(0, 0, 1);
		points[2] = new Point(0, 0, 0);
		points[3] = new Point(1, 0, 0);
	}

	private void initTop() {
		points[0] = new Point(0.75f, 0.5f, 0.75f);
		points[1] = new Point(0.25f, 0.5f, 0.75f);
		points[2] = new Point(0.25f, 0.5f, 0.25f);
		points[3] = new Point(0.75f, 0.5f, 0.25f);
	}

	public void render() {
		GL11.glColor3f(1, 1, 1); //Set color to white to get rid of tint
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		texture.bind();
		GL11.glBegin(GL11.GL_QUADS); 	//Start rendering in QUAD mode
			GL11.glTexCoord2f(1, 1); //Texture mapping
			GL11.glVertex3f(points[0].x, points[0].y, points[0].z); //Point 1
			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(points[1].x, points[1].y, points[1].z); //Point 2
			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(points[2].x, points[2].y, points[2].z); //Point 3
			GL11.glTexCoord2f(1, 0);
			GL11.glVertex3f(points[3].x, points[3].y, points[3].z); //Point 4
		GL11.glEnd(); //Stop rendering this face
	}
}
