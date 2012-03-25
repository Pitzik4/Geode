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
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import pitzik4.geode.Geode;

public class Vorxel {
	public static final Render renderStandardCube = new RenderStandardCube();
	private static final Cube testCube = new Cube();
	private static Geode game;
	private static Texture tex;
	private static Vector3f pos;
	private static boolean exit;
	private static float pitch;
	private static float yaw;
	public static Render renderGrass = new RenderGrass();
	private static Cube grass = new CubeGrass();
	public static float windz = 0.1f;
	public static float windx = 0.1f;
	private static float tarwinz = 0.4f;;
	private static float tarwinx = 0.4f;
	private static Random random = new Random();
	private static World world;

	public static void init(VorxelSettings set, Geode gamep) {
		world = new World();
		pos = world.getStartingPosition();
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

	public static void yaw(float amount)
	{
	    yaw += amount;
	}
	 
	public static void pitch(float amount)
	{
	    pitch += amount;
	}
	
	public static void walkForward(float distance)
	{
	    pos.x -= distance * (float)Math.sin(Math.toRadians(yaw));
	    pos.z += distance * (float)Math.cos(Math.toRadians(yaw));
	}
	 
	public static void walkBackwards(float distance)
	{
	    pos.x += distance * (float)Math.sin(Math.toRadians(yaw));
	    pos.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	 
	public static void strafeLeft(float distance)
	{
	    pos.x -= distance * (float)Math.sin(Math.toRadians(yaw-90));
	    pos.z += distance * (float)Math.cos(Math.toRadians(yaw-90));
	}
	 
	public static void strafeRight(float distance)
	{
	    pos.x -= distance * (float)Math.sin(Math.toRadians(yaw+90));
	    pos.z += distance * (float)Math.cos(Math.toRadians(yaw+90));
	}
	
    public static void lookThrough()
    {
        GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(pos.x, pos.y, pos.z);
    }
    
	public static void tick() {
		yaw(Mouse.getDX());
		pitch(0-Mouse.getDY());
		//Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
		Mouse.setGrabbed(true);

		if(Display.isCreated()) {
			render();
			Display.update();
			if(Display.isCloseRequested() || exit) {
				game.close();
				Display.destroy();
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            walkForward(world.movespeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            walkBackwards(world.movespeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            strafeLeft(world.movespeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            strafeRight(world.movespeed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
        	exit = true;
        }
	}

	private static void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glLoadIdentity();

		lookThrough();

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
}

