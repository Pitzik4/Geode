package misson20000.api.vorxel;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import pitzik4.geode.Geode;

public class Vorxel {
	public static final Render renderStandardCube = new RenderStandardCube();
	private static final Cube testCube = new Cube();
	private static Geode game;
	private static Texture tex;

	public static void init(VorxelSettings set, Geode gamep) {
		game = gamep;
		try {
	        DisplayMode d[] = Display.getAvailableDisplayModes();
	        DisplayMode displayMode = d[0];
			//Display.setDisplayMode(new DisplayMode(set.width,set.height));
	        Display.setDisplayMode(displayMode);
			Display.create();
			
	        GL11.glEnable(GL11.GL_TEXTURE_2D); // Enable Texture Mapping
	        GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
	        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
	        GL11.glClearDepth(1.0); // Depth Buffer Setup
	        GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
	        GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do

	        GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
	        GL11.glLoadIdentity(); // Reset The Projection Matrix
			// Calculate The Aspect Ratio Of The Window
	        GLU.gluPerspective(
	          45.0f,
	          (float) displayMode.getWidth() / (float) displayMode.getHeight(),
	          0.1f,
	          100.0f);
	        GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix

	        // Really Nice Perspective Calculations
	        GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
	        Vorxel.testCube.texture = TextureLoader.getTexture("PNG", Geode.class.getResourceAsStream("/texture.png"));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void tick() {
		if(Display.isCreated()) {
			render();
			Display.update();
			if(Display.isCloseRequested()) {
				game.close();
				Display.destroy();
			}
		}
	}

	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		
		GLU.gluLookAt(0, 0, 0, 0, 0, 0, 0, 1, 0);
		//tesetRender();
	
		GL11.glLoadIdentity();
		GL11.glTranslatef(0, 0, -6);
		GL11.glRotated(-45, 1, 1, 1);
		Render.renderCube(Vorxel.testCube);
		
		GL11.glLoadIdentity();
		GL11.glTranslatef(-1, 1, -6);
		GL11.glRotated(-45, 1, 1, 1);
		Render.renderCube(Vorxel.testCube);
		
		GL11.glLoadIdentity();

	}

	private static void tesetRender() {
		System.out.println("Render");
		GL11.glLoadIdentity();                          // Reset The Current Modelview Matrix
        GL11.glTranslatef(1.5f,0.0f,-7.0f);             // Move Right 1.5 Units And Into The Screen 6.0
        //GL11.glRotatef(rquad,1.0f,1.0f,1.0f);               // Rotate The Quad On The X axis ( NEW )
        GL11.glColor3f(0.5f,0.5f,1.0f);                 // Set The Color To Blue One Time Only
        GL11.glBegin(GL11.GL_QUADS);                        // Draw A Quad
            GL11.glColor3f(0.0f,1.0f,0.0f);             // Set The Color To Green
            GL11.glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f);         // Bottom Left Of The Quad (Top)
            GL11.glVertex3f( 1.0f, 1.0f, 1.0f);         // Bottom Right Of The Quad (Top)
            GL11.glColor3f(1.0f,0.5f,0.0f);             // Set The Color To Orange
            GL11.glVertex3f( 1.0f,-1.0f, 1.0f);         // Top Right Of The Quad (Bottom)
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);         // Top Left Of The Quad (Bottom)
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Bottom)
            GL11.glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Bottom)
            GL11.glColor3f(1.0f,0.0f,0.0f);             // Set The Color To Red
            GL11.glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Front)
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Front)
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Front)
            GL11.glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Front)
            GL11.glColor3f(1.0f,1.0f,0.0f);             // Set The Color To Yellow
            GL11.glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Back)
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Back)
            GL11.glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Back)
            GL11.glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Back)
            GL11.glColor3f(0.0f,0.0f,1.0f);             // Set The Color To Blue
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f);         // Top Right Of The Quad (Left)
            GL11.glVertex3f(-1.0f, 1.0f,-1.0f);         // Top Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);         // Bottom Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);         // Bottom Right Of The Quad (Left)
            GL11.glColor3f(1.0f,0.0f,1.0f);             // Set The Color To Violet
            GL11.glVertex3f( 1.0f, 1.0f,-1.0f);         // Top Right Of The Quad (Right)
            GL11.glVertex3f( 1.0f, 1.0f, 1.0f);         // Top Left Of The Quad (Right)
            GL11.glVertex3f( 1.0f,-1.0f, 1.0f);         // Bottom Left Of The Quad (Right)
            GL11.glVertex3f( 1.0f,-1.0f,-1.0f);         // Bottom Right Of The Quad (Right)
        GL11.glEnd();                                       // Done Drawing The Quad
	}
}
