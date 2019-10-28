package game;

import game.gfx.Camera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.levels.Level;

public class Handler {
	static Game game = Launcher.getGame();
	
	
	public static Camera getCamera() {
		return 	game.getCamera();
	}
	public static KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	public static int getScreenWidth() {
		return game.getWidth();
	}
	public static int getScreenHeight() {
		return game.getHeight();
	}
	public static Game getGame() {
		return game;
	}
	public static void setGame(Game gamein) {
		game = gamein;
	}

	public static MouseManager getMouseManager() {
		return game.getMouseManager();
	}
}
