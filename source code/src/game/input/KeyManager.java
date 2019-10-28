package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	private boolean[] keys;
	public boolean up, down, left, right,space,esc;
	
	public KeyManager() {
		keys = new boolean[256];
		
	}
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		space=keys[KeyEvent.VK_SPACE];
		esc =keys[KeyEvent.VK_ESCAPE];
		
		
	}
	@Override
	public void keyPressed(KeyEvent key) {
		keys[key.getKeyCode()]= true;	
	}

	@Override
	public void keyReleased(KeyEvent key) {
		keys[key.getKeyCode()]= false;
	}

	@Override
	public void keyTyped(KeyEvent key) {
		
	}

}
