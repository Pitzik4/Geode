package misson20000.api.vorxel.renders;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import org.lwjgl.opengl.GL11;

import misson20000.api.vorxel.Vorxel;
import misson20000.api.vorxel.cubes.Cube;
import static org.lwjgl.opengl.GL11.*;

public class RenderWater extends RenderStandardCube {

	private static final int texSize = 256;
	private int reflection = Cube.dirt.texture.getTextureID();
	private boolean rendering;

	@Override
	public void render(Cube c) {
		if(!rendering) {
			rendering = true;
			GL11.glPushMatrix();
		    //glViewport(0,0, texSize, texSize);
		    glLoadIdentity();
		    GL11.glTranslatef(0-c.x, 0-c.y, 0-c.z);

		    glPushMatrix();
		    glTranslatef(0.0f, 0.0f, 0.0f);
		    glScalef(1.0f, -1.0f, 1.0f);
		    double[] p = {0.0, 1.0, 0.0, 0.0};
		    
		    DoubleBuffer plane = ByteBuffer.allocateDirect(32).asDoubleBuffer();
		    plane.put(p);
		    plane.position(0);
		    
		    glEnable(GL_CLIP_PLANE0);
		    glClipPlane(GL_CLIP_PLANE0, plane);
		    Vorxel.renderScene();
		    glDisable(GL_CLIP_PLANE0);
		    glPopMatrix();

		    //render reflection to texture
		    glBindTexture(GL_TEXTURE_2D, reflection);
		    //glCopyTexSubImage2D copies the frame buffer
		    //to the bound texture
		    glCopyTexSubImage2D(GL_TEXTURE_2D,0,0,0,0,0,texSize, texSize);
		    
			GL11.glBegin(GL11.GL_QUADS);
			
				renderTopFace();
				renderBottomFace();
				
				renderFrontFace();
				renderBackFace();
				
				renderLeftFace();
				renderRightFace();
			
			GL11.glEnd();
		    GL11.glViewport(0, 0, 640, 480);
		    GL11.glPopMatrix();
		    rendering = false;
		}
	}

}
