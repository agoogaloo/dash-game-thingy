package game;

public class Launcher {
	public static Game game;
	public static void main (String[] args) {
		game = new Game("waaa", 800,600);
		game.start();
		
	}
	public static Game getGame() {
		return game;
	}

}
