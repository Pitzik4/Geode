package misson20000.api.vorxel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Random;

import misson20000.api.vorxel.cubes.Cube;
import misson20000.api.vorxel.models.ModelHuman;
import misson20000.api.vorxel.renders.Render;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.TextureLoader;

import pitzik4.geode.Geode;
import pitzik4.geode.World;

public class Vorxel {
	private static Geode game;
	private static boolean exit;
	public static float windz = 0.1f;
	public static float windx = 0.1f;
	@SuppressWarnings("unused")
	private static float tarwinz = 0.4f;;
	@SuppressWarnings("unused")
	private static float tarwinx = 0.4f;
	@SuppressWarnings("unused")
	private static Random random = new Random();
	private static World world;
	public static HashMap<Integer, Location> namemap;
	public static Integer nextName = 0;

	public static void init(VorxelSettings set, Geode gamep) {
		world = new World();
		game = gamep;
		try {
	        DisplayMode d[] = Display.getAvailableDisplayModes();
	        DisplayMode displayMode = d[0];
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
	        
	        Cube.dirt.texture = TextureLoader.getTexture("PNG", Geode.class.getResourceAsStream("/dirt.png"));
	        Cube.grass.texture = Cube.dirt.texture;
	        Cube.grass.toptexture = TextureLoader.getTexture("PNG", Geode.class.getResourceAsStream("/grass.png"));
			Mouse.setGrabbed(true);
			world.createSpawn();
		} catch (LWJGLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void lookThrough()
    {
        GL11.glRotatef(game.getPlayerController().getPitch(), 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(game.getPlayerController().getYaw(), 0.0f, 1.0f, 0.0f);
        GL11.glTranslatef(game.getPlayerController().getX(), game.getPlayerController().getY(), game.getPlayerController().getZ());
    }
    
	public static void tick() {
		//Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight()/2);
		if(Display.isCreated()) {
	        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
	        	exit = true;
	        }
	        if(Keyboard.isKeyDown(Keyboard.KEY_R)) {
	        	Mouse.setGrabbed(false);
	        }
	        if(Mouse.isButtonDown(0)) {
	        	Mouse.setGrabbed(true);
	        }
	        
			Display.update();
			if(Display.isCloseRequested() || exit) {
				game.close();
				Display.destroy();
			}
		}
		for(int i = 0; i < world.entities.size(); i++) {
			world.entities.get(i).onTick();
		}
	}

	public static void render() {
		GL11.glClearColor(0f, 255f/191f, 1f, 0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glLoadIdentity();
		lookThrough();
		/*doPicking();
		namemap = new HashMap<Integer, Location>();
		Render.renderArray(world.getSpawn());
		GL11.glLoadIdentity();
		game.render();
		stopPicking();*/ //Picking junk
		/*GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glLoadIdentity();
		lookThrough();*/
		Render.renderArray(world.getSpawn());
		Render.renderEntities(world.entities);
		/*GL11.glLoadIdentity();
		game.render();*/
		Display.update();
	}

	private static void stopPicking() {
		/*int hits;
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPopMatrix();
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glFlush();
		
		// returning to normal rendering mode
		hits = GL11.glRenderMode(GL11.GL_RENDER);
		
		// if there are hits process them
		if (hits != 0)
			processHits(hits,selectBuf);*/
	}

	private static void doPicking() {
		IntBuffer buffer = ByteBuffer.allocateDirect(nextName).asIntBuffer();
		GL11.glSelectBuffer(buffer);
		GL11.glRenderMode(GL11.GL_SELECT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glPushMatrix();
		GL11.glLoadIdentity();
		GLU.gluPickMatrix(Mouse.getX(), Mouse.getY(), 4, 4, buffer);
		GL11.glRenderMode(GL11.GL_RENDER);
	}

	public static void renderScene() {
		game.render();
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		Render.renderArray(world.getSpawn());
		GL11.glLoadIdentity();
		Display.update();
	}
}

