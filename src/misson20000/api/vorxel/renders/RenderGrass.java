package misson20000.api.vorxel.renders;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.lwjgl.opengl.GL11;

import misson20000.api.vorxel.Cube;
import misson20000.api.vorxel.Vorxel;

public class RenderGrass extends RenderStandardCube {

	@Override
	public void render(Cube c) {
		// TODO Auto-generated method stub
		GL11.glColor3f(1, 1, 1);
		
		c.toptexture.bind();
		GL11.glBegin(GL11.GL_QUADS);
			renderTopFace();
			renderGrassBlade(0.25f, 0.25f);
			renderGrassBlade(0.5f, 0.25f);
			renderGrassBlade(0.5f, 0.5f);
			renderGrassBlade(1f, 0.25f);
			renderGrassBlade(0.25f, 1f);
			renderGrassBlade(0.5f, 0.5f);
			renderGrassBlade(0.f, 0.25f);
			renderGrassBlade(0f, 0.5f);
			renderGrassBlade(0f, 0f);
			renderGrassBlade(1f, 1f);
		GL11.glEnd();
		c.texture.bind();
		GL11.glBegin(GL11.GL_QUADS);
			renderBottomFace();
			
			renderFrontFace();
			renderBackFace();
			
			renderLeftFace();
			renderRightFace();
			
		GL11.glEnd();
	}

	private void renderGrassBlade(float x, float z) {
		// TODO Auto-generated method stub
		glTexCoord2f(1, 0);
		glVertex3f(x + Vorxel.windx, 1.1f, z + Vorxel.windz);
		glTexCoord2f(0, 0);
		glVertex3f(x + Vorxel.windx, 1.1f,z+Vorxel.windz-0.1f);
		glTexCoord2f(0, 1);
		glVertex3f(x,1f,z-0.1f);
		glTexCoord2f(1, 1);
		glVertex3f(x,1f, z);
	}

}
