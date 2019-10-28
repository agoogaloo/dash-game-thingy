package game.state;

import java.awt.Graphics;

import game.Game;
import game.Launcher;

public abstract class States {
	
	private static States currentState = null;
	
	public static void setState(States state) {
		currentState = state;
	}
	
	public static States getState() {
		return currentState;
	}
	
	
	
	public States() {
		
	}
	public abstract void update();
	
	public abstract void render(Graphics g);

}
