package misson20000.api.vorxel;

import java.io.IOException;
import java.util.Random;

import misson20000.api.vorxel.renders.Render;
import misson20000.api.vorxel.renders.RenderGrass;
import misson20000.api.vorxel.renders.RenderStandardCube;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
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
	private static float x;
	private static float z;
	private static int rot;
	private static boolean exit;
	private static int xrot;
	private static int yrot;
	public static Render renderGrass = new RenderGrass();
	private static Cube grass = new CubeGrass();
	public static float windz = 0.1f;
	public static float windx = 0.1f;
	private static float tarwinz = 0.4f;;
	private static float tarwinx = 0.4f;


	public static void init(VorxelSettings set, Geode gamep) {
		game = gamep;
		try {
	        DisplayMode d[] = Display.getAvailableDisplayModes();
	        DisplayMode displayMode = d[0];
			//Display.setDisplayMode(new DisplayMode(set.width,set.height));
	        Display.setDisplayMode(displayMode);
			Display.create();
			
			Mouse.create();
			Keyboard.create();
			
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
	        
	        Vorxel.testCube.texture = TextureLoader.getTexture("PNG", Geode.class.getResourceAsStream("/dirt.png"));
	        Vorxel.grass.texture = Vorxel.testCube.texture;
	        Vorxel.grass.toptexture = TextureLoader.getTexture("PNG", Geode.class.getResourceAsStream("/grass.png"));

		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Random random = new Random();

	public static void tick() {
		if(Display.isCreated()) {
			double xrotrads = Math.toRadians(xrot);
			if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
				x += 1;
				z += 1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
				z -= 1;
				x -= 1;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
				x++;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
				x--;
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				exit = true;
			}
			
			windx = Mouse.getX()/ 100;
			windz = Mouse.getY() / 100;
			Mouse.setClipMouseCoordinatesToWindow(true);
			render();
			Display.update();
			if(Display.isCloseRequested() || exit) {
				game.close();
				Display.destroy();
			}
		}
	}

	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		
		//tesetRender();
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glLoadIdentity();
		GLU.gluLookAt(x, 0 , z, 0, 0, 0, 0, 1, 0);
		//Move to camera		//GL11.glTranslatef(x, 0, z);
		//Rotate camera
		//GL11.glRotated(0 + xrot, 0, 1, 0);
		//Move to 
		//GL11.glTranslatef(x + x, 0, z + z);
		//GL11.glRotated(0 + yrot, 1, 0, 0);
		//GLU.gluLookAt(x, 0, z, 0, 0, 0, 0, 1, 0);
		GL11.glRotatef(45, 0, 1, 1);
		GL11.glRotatef(-45, 1, 0, 0);

		GL11.glTranslatef(0, 0, -6);
		Render.renderCube(Vorxel.testCube);
		GL11.glTranslatef(0, 1, 0);
		Render.renderCube(Vorxel.grass);
		GL11.glTranslatef(1, 0, 0);
		Render.renderCube(Vorxel.grass);
		GL11.glTranslatef(1, 0, 0);
		Render.renderCube(Vorxel.grass);
		GL11.glTranslatef(1, 0, 0);
		Render.renderCube(Vorxel.grass);
		
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
