package misson20000.api.vorxel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import pitzik4.geode.Geode;

public class Vorxel {
	private static Geode game;

	public static void init(VorxelSettings set, Geode gamep) {
		game = gamep;
		try {
			Display.setDisplayMode(new DisplayMode(set.width,set.height));
			Display.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		init(new VorxelSettings(), null);
	}

	public static void tick() {
		if(Display.isCreated()) {
			Display.update();
			if(Display.isCloseRequested()) {
				game.close();
				Display.destroy();
			}
		}
	}
}
