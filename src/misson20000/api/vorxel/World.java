package misson20000.api.vorxel;

import org.lwjgl.util.vector.Vector3f;

public class World {
	public float movespeed = 0.5f;

	public Vector3f getStartingPosition() {
		return new Vector3f(0, 0, 0);
	}
}
