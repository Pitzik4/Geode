package misson20000.api.vorxel;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class VTex implements Texture {

	private int id;
	private int height;
	private int width;

	public VTex(int id) {
		this.id = id;
	}
	
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
	}

	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getImageHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getImageWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public byte[] getTextureData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTextureHeight() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getTextureID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getTextureRef() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTextureWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public boolean hasAlpha() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void release() {
		// TODO Auto-generated method stub

	}

}
