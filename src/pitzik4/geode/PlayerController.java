package pitzik4.geode;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class PlayerController implements Tickable {
	private Geode game;
	private Vector3f pos;
	private float pitch;
	private float yaw;
	public static float WALK_SPEED = 0.25f;

	public PlayerController(Geode geode) {
		game = geode;
	}

	@Override
	public void tick() {
		yaw(Mouse.getDX());
		pitch(-Mouse.getDY());
		if(Control.MOVE_FORWARD.isDown()) {
			walkForward(WALK_SPEED);
		}
		if(Control.MOVE_BACK.isDown()) {
			walkBackwards(WALK_SPEED);
		}
		if(Control.MOVE_LEFT.isDown()) {
			strafeLeft(WALK_SPEED);
		}
		if(Control.MOVE_RIGHT.isDown()) {
			strafeRight(WALK_SPEED);
		}
	}
	
	public void yaw(float amount)
	{
	    yaw += amount;
	}
	public void pitch(float amount)
	{
	    pitch += amount;
	    if(pitch < -90 || pitch > 90) {
	    	pitch -= amount;
	    }
	}
	public void walkForward(float distance)
	{
	    pos.x -= distance * (float)Math.sin(Math.toRadians(yaw));
	    pos.z += distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void walkBackwards(float distance)
	{
	    pos.x += distance * (float)Math.sin(Math.toRadians(yaw));
	    pos.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	public void strafeLeft(float distance)
	{
	    pos.x -= distance * (float)Math.sin(Math.toRadians(yaw-90));
	    pos.z += distance * (float)Math.cos(Math.toRadians(yaw-90));
	}
	public void strafeRight(float distance)
	{
	    pos.x -= distance * (float)Math.sin(Math.toRadians(yaw+90));
	    pos.z += distance * (float)Math.cos(Math.toRadians(yaw+90));
	}
	
	public void goTo(Vector3f pos, float pitch, float yaw) {
		this.pos = pos;
		this.pitch = pitch;
		this.yaw = yaw;
	}
	public float getPitch() {
		return pitch;
	}
	public float getYaw() {
		return yaw;
	}
	public float getX() {
		return pos.x;
	}
	public float getY() {
		return pos.y;
	}
	public float getZ() {
		return pos.z;
	}

}
