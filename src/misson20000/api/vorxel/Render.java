package misson20000.api.vorxel;

public abstract class Render {
	public abstract void render(Cube c);
	public static void renderCube(Cube c) {
		c.getRender().render(c);
	}
}
