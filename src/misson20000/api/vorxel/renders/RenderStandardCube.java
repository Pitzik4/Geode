package misson20000.api.vorxel.renders;

import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import misson20000.api.vorxel.cubes.Cube;

import org.lwjgl.opengl.GL11;
public class RenderStandardCube extends Render {
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

	protected void renderRightFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 0);
		glVertex3f(1.0f, 1.0f, 1.0f);
		glTexCoord2f(0, 0);
		glVertex3f(1.0f, 1.0f,0f);
		glTexCoord2f(0, 1);
		glVertex3f(1.0f,0f,0f);
		glTexCoord2f(1, 1);
		glVertex3f(1.0f,0f, 1.0f);
	}

	protected void renderLeftFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 1);
		glVertex3f(0f, 1.0f, 1.0f);
		glTexCoord2f(1, 0);
		glVertex3f(0f, 1.0f,0f);
		glTexCoord2f(0, 0);
		glVertex3f(0f,0f,0f);
		glTexCoord2f(0, 1);
		glVertex3f(0f,0f, 1.0f);
	}

	protected void renderBackFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 1);
		glVertex3f( 1.0f, 1.0f, 0f);
		glTexCoord2f(0, 1);
		glVertex3f(0f, 1.0f, 0f);
		glTexCoord2f(0, 0);
		glVertex3f(0f,0f, 0f);
		glTexCoord2f(1, 0);
		glVertex3f( 1.0f,0f, 0f);	
	}

	protected void renderFrontFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 1);
		glVertex3f( 1.0f, 1.0f, 1.0f);
		glTexCoord2f(0, 1);
		glVertex3f(0f, 1.0f, 1.0f);
		glTexCoord2f(0, 0);
		glVertex3f(0f,0f, 1.0f);
		glTexCoord2f(1, 0);
		glVertex3f( 1.0f,0f, 1.0f);		
	}

	protected void renderBottomFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 1);
		glVertex3f( 1.0f,0f, 1.0f);
		glTexCoord2f(0, 1);
		glVertex3f(0f,0f, 1.0f);
		glTexCoord2f(0, 0);
		glVertex3f(0f,0f,0f);
		glTexCoord2f(1, 0);
		glVertex3f( 1.0f,0f,0f);
	}

	protected void renderTopFace() {
		GL11.glColor3f(1, 1, 1);
		glTexCoord2f(1, 0);
		glVertex3f( 1.0f, 1.0f,0f);
		glTexCoord2f(0, 0);
		glVertex3f(0f, 1.0f,0f);
		glTexCoord2f(0, 1);
		glVertex3f(0f, 1.0f, 1.0f);
		glTexCoord2f(1, 1);
		glVertex3f( 1.0f, 1.0f, 1.0f);
	}

}
