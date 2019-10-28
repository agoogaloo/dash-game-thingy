package game.state;

import java.awt.Graphics;

import game.Handler;
import game.entities.EntityManager;
import game.entities.creature.Player;
import game.entities.statics.Heart;
import game.levels.Level;
import game.tiles.Tile;

public class GameState  extends States{
	
	public static Level level;
	public static boolean showHitBox=false;
	public GameState(){
		level = new Level(1);
		//player = new Player(Handler.getLevel().getSpawnX()*Tile.WIDTH,Handler.getLevel().getSpawnY()*Tile.HEIGHT);
	}
	@Override
	public void update() {
		level.update();
		Handler.getCamera().centerOnEntity(EntityManager.player);
	}

	@Override
	public void render(Graphics g) {
		level.render(g);
	}
	public static void setLevel(Level levelin) {
		level = levelin;
	}
	public static Level getLevel() {
		return level;
	}
}
