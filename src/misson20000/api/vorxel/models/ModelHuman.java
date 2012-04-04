package misson20000.api.vorxel.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.TextureLoader;

import pitzik4.geode.Geode;

import misson20000.api.vorxel.cubes.Cube;

public class ModelHuman extends Model {
	private final Box RARM = new Box();
	private final Box HEAD = new Box();
	private final Box BODY = new Box();
	private final Box LARM = new Box();
	private final Box RLEG = new Box();
	private final Box LLEG = new Box();
	private Face[] faces;
	private Box[] boxes;
	
	public ModelHuman() {
		loadTextures();
		boxes = new Box[4];
		HEAD.pitch = 45;
		RARM.pitch = 45;
		LARM.pitch = 360-45;
		boxes[0] = HEAD;
		boxes[1] = BODY;
		boxes[2] = LARM;
		boxes[3] = RARM;
		createHead();
		createBody();
		createLeftArm();
		createRightArm();
		//createLeftLeggi();
		//createRiteLeggi();
	}
	private void createRiteLeggi() {
		faces[30] = new Face(EnumFaceSide.TOP, RLEG.top, 0.5f, 1f, 0.5f, 0.5f, -2f, 0.25f);
		faces[31] = new Face(EnumFaceSide.BOTTOM, RLEG.bottom, 0.5f, 1f, 0.5f, 0.5f, -2f, 0.25f);
		faces[32] = new Face(EnumFaceSide.FRONT, RLEG.front, 0.5f, 1f, 0.5f, 0.5f, -2f, 0.25f);
		faces[33] = new Face(EnumFaceSide.BACK, RLEG.back, 0.5f, 1f, 0.5f, 0.5f, -2f, 0.25f);
		faces[34] = new Face(EnumFaceSide.RIGHT, RLEG.right, 0.5f, 1f, 0.5f, 0.5f, -2f, 0.25f);
		faces[35] = new Face(EnumFaceSide.LEFT, RLEG.left, 0.5f, 1f, 0.5f, 0.5f, -2f, 0.25f);
	}
	private void createLeftLeggi() {
		faces[24] = new Face(EnumFaceSide.TOP, LLEG.top, 0.5f, 1f, 0.5f, 0f, -2f, 0.25f);
		faces[25] = new Face(EnumFaceSide.BOTTOM, LLEG.bottom, 0.5f, 1f, 0.5f, -0f, -2f, 0.25f);
		faces[26] = new Face(EnumFaceSide.FRONT, LLEG.front, 0.5f, 1f, 0.5f, 0f, -2f, 0.25f);
		faces[27] = new Face(EnumFaceSide.BACK, LLEG.back, 0.5f, 1f, 0.5f, 0f, -2f, 0.25f);
		faces[28] = new Face(EnumFaceSide.RIGHT, LLEG.right, 0.5f, 1f, 0.5f, 0f, -2f, 0.25f);
		faces[29] = new Face(EnumFaceSide.LEFT, LLEG.left, 0.5f, 1f, 0.5f, 0f, -2f, 0.25f);
	}
	private void createRightArm() {
		RARM.faces[0] = new Face(EnumFaceSide.TOP, RARM.top, 0.5f, 1f, 0.5f, 1f, -1f, 0.25f);
		RARM.faces[1] = new Face(EnumFaceSide.BOTTOM, RARM.bottom, 0.5f, 1f, 0.5f, 1f, -1f, 0.25f);
		RARM.faces[2] = new Face(EnumFaceSide.FRONT, RARM.front, 0.5f, 1f, 0.5f, 1f, -1f, 0.25f);
		RARM.faces[3] = new Face(EnumFaceSide.BACK, RARM.back, 0.5f, 1f, 0.5f, 1f, -1f, 0.25f);
		RARM.faces[4] = new Face(EnumFaceSide.RIGHT, RARM.right, 0.5f, 1f, 0.5f, 1f, -1f, 0.25f);
		RARM.faces[5] = new Face(EnumFaceSide.LEFT, RARM.left, 0.5f, 1f, 0.5f, 1f, -1f, 0.25f);
	}
	private void createLeftArm() {
		LARM.faces[0] = new Face(EnumFaceSide.TOP, LARM.top, 0.5f, 1f, 0.5f, -0.5f, -1f, 0.25f);
		LARM.faces[1] = new Face(EnumFaceSide.BOTTOM, LARM.bottom, 0.5f, 1f, 0.5f, -0.5f, -1f, 0.25f);
		LARM.faces[2] = new Face(EnumFaceSide.FRONT, LARM.front, 0.5f, 1f, 0.5f, -0.5f, -1f, 0.25f);
		LARM.faces[3] = new Face(EnumFaceSide.BACK, LARM.back, 0.5f, 1f, 0.5f, -0.5f, -1f, 0.25f);
		LARM.faces[4] = new Face(EnumFaceSide.RIGHT, LARM.right, 0.5f, 1f, 0.5f, -0.5f, -1f, 0.25f);
		LARM.faces[5] = new Face(EnumFaceSide.LEFT, LARM.left, 0.5f, 1f, 0.5f, -0.5f, -1f, 0.25f);
	}
	private void createBody() {
		BODY.faces[0] = new Face(EnumFaceSide.TOP, BODY.top, 1f, 1f, 0.5f, 0f, -1, 0.25f);
		BODY.faces[1] = new Face(EnumFaceSide.BOTTOM, BODY.bottom, 1f, 1f, 0.5f, 0f, -1, 0.25f);
		BODY.faces[2] = new Face(EnumFaceSide.FRONT, BODY.front, 1f, 1f, 0.5f, 0f, -1f, 0.25f);
		BODY.faces[3] = new Face(EnumFaceSide.BACK, BODY.back, 1f, 1f, 0.5f, 0f, -1f, 0.25f);
		BODY.faces[4] = new Face(EnumFaceSide.RIGHT, BODY.right, 1f, 1, 0.5f, 0f, -1, 0.25f);
		BODY.faces[5] = new Face(EnumFaceSide.LEFT, BODY.left, 1f, 1f, 0.5f, 0f, -1f, 0.25f);
	}
	private void createHead() {
		HEAD.init(0.175, 0.5, 0, 0.75, 0.75, 0.75);
	}
	@Override
	public Face[] getFaces() {
		return faces;
	}
	
	public void loadTextures() {
		try {
			HEAD.top = TextureLoader.getTexture("PNG", new FileInputStream(new File("human/headtop.png")), true);
			HEAD.bottom = TextureLoader.getTexture("PNG", new FileInputStream(new File("human/headbottom.png")), true);
			HEAD.front = TextureLoader.getTexture("PNG", new FileInputStream(new File("human/headfront.png")), true);
			HEAD.back = TextureLoader.getTexture("PNG", new FileInputStream(new File("human/headback.png")), true);
			HEAD.left = TextureLoader.getTexture("PNG", new FileInputStream(new File("human/headleft.png")), true);
			HEAD.right = TextureLoader.getTexture("PNG", new FileInputStream(new File("human/headright.png")), true);
			LARM.top = HEAD.bottom;
			LARM.bottom = HEAD.bottom;
			LARM.front = HEAD.bottom;
			LARM.back = HEAD.bottom;
			LARM.left = HEAD.bottom;
			LARM.right = HEAD.bottom;
			RARM.top = HEAD.bottom;
			RARM.bottom = HEAD.bottom;
			RARM.front = HEAD.bottom;
			RARM.back = HEAD.bottom;
			RARM.left = HEAD.bottom;
			RARM.right = HEAD.bottom;
			BODY.top = HEAD.bottom;
			BODY.bottom = HEAD.bottom;
			BODY.front = HEAD.bottom;
			BODY.back = HEAD.bottom;
			BODY.left = HEAD.bottom;
			BODY.right = HEAD.bottom;
			LLEG.top = HEAD.bottom;
			LLEG.bottom = HEAD.bottom;
			LLEG.front = HEAD.bottom;
			LLEG.back = HEAD.bottom;
			LLEG.left = HEAD.bottom;
			LLEG.right = HEAD.bottom;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Box[] getBoxes() {
		// TODO Auto-generated method stub
		return boxes;
	}

}
