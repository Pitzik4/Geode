package misson20000.api.vorxel;

import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
public class RenderStandardCube extends Render {

	private Texture tex;

	@Override
	public void render(Cube c) {
		GL11.glColor3f(1, 0, 0);
		
		c.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
		
			renderTopFace();
			renderBottomFace();
			
			renderFrontFace();
			renderBackFace();
			
			renderLeftFace();
			renderRightFace();
		
		GL11.glEnd();
	}

	private void renderRightFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 0);
		glVertex3f(1.0f, 1.0f, 1.0f);
		glTexCoord2f(0, 0);
		glVertex3f(1.0f, 1.0f,-1.0f);
		glTexCoord2f(0, 1);
		glVertex3f(1.0f,-1.0f,-1.0f);
		glTexCoord2f(1, 1);
		glVertex3f(1.0f,-1.0f, 1.0f);
	}

	private void renderLeftFace() {
		glVertex3f(-1.0f, 1.0f, 1.0f);
		glVertex3f(-1.0f, 1.0f,-1.0f);
		glVertex3f(-1.0f,-1.0f,-1.0f);
		glVertex3f(-1.0f,-1.0f, 1.0f);
	}

	private void renderBackFace() {
		glVertex3f( 1.0f, 1.0f, -1.0f);
		glVertex3f(-1.0f, 1.0f, -1.0f);
		glVertex3f(-1.0f,-1.0f, -1.0f);
		glVertex3f( 1.0f,-1.0f, -1.0f);	
	}

	private void renderFrontFace() {
		glVertex3f( 1.0f, 1.0f, 1.0f);
		glVertex3f(-1.0f, 1.0f, 1.0f);
		glVertex3f(-1.0f,-1.0f, 1.0f);
		glVertex3f( 1.0f,-1.0f, 1.0f);		
	}

	private void renderBottomFace() {
		glVertex3f( 1.0f,-1.0f, 1.0f);
		glVertex3f(-1.0f,-1.0f, 1.0f);
		glVertex3f(-1.0f,-1.0f,-1.0f);
		glVertex3f( 1.0f,-1.0f,-1.0f);
	}

	private void renderTopFace() {
		glVertex3f( 1.0f, 1.0f,-1.0f);
		glVertex3f(-1.0f, 1.0f,-1.0f);
		glVertex3f(-1.0f, 1.0f, 1.0f);
		glVertex3f( 1.0f, 1.0f, 1.0f);
	}

}
