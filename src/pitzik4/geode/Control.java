package pitzik4.geode;

import org.lwjgl.input.Keyboard;

public enum Control {
	MOVE_LEFT (Keyboard.KEY_A),
	MOVE_RIGHT (Keyboard.KEY_D),
	MOVE_FORWARD (Keyboard.KEY_W),
	MOVE_BACK (Keyboard.KEY_S),
	JUMP (Keyboard.KEY_SPACE),
	MOVE_DOWN (Keyboard.KEY_E),
	MOVE_UP (Keyboard.KEY_Q);
	
	private int keyValue;
	
	private Control(int keyValue) {
		this.keyValue = keyValue;
	}
	
	public boolean isDown() {
		return Keyboard.isKeyDown(keyValue);
	}
	public int getKeyValue() {
		return keyValue;
	}
}
