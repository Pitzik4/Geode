package misson20000.api.vorxel;

import misson20000.api.vorxel.cubes.Cube;

import org.lwjgl.util.vector.Vector3f;

public class World {
	public float movespeed = 0.5f;
	private Cube[][][] spawn;

	public Vector3f getStartingPosition() {
		return new Vector3f(-8, -15, -8); //These must be negative to work right
	}
	
	public Cube[][][] getSpawn() {
		return spawn;
	}
	
	public void createSpawn() {
		spawn = new Cube[16][16][16];
		for(int x = 0; x < 16; x++) {
			for(int y = 0; y<16; y++) {
				for(int z = 0; z<16; z++) {
					if(y == 0) {
						spawn[x][y][z] = Cube.grass;
					} else {
						spawn[x][y][z] = Cube.air;
					}
				}
			}
		}
	}
	
	public Cube getCubeAt(int x, int y, int z) {
		return spawn[x][y][z];
	}

	public Cube getCubeAt(double x, double y, double z) {
		return spawn[(int) x][(int) y][(int) z];
	}
}
