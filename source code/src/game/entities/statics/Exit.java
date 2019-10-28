package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.levels.Level;
import game.state.GameState;

public class Exit extends StaticEntity{
	public Exit(float x, float y) {
		super(x,y,48,96);
		CLASS = "exit";
		destructible = false;
	}

	@Override
	public void update() {
		if (entityCollide(0,0).contains("player")){
			GameState.level.nextLevel();
		}	
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.exit,(int)(x-Handler.getCamera().getxOffset()),
				(int)(y-Handler.getCamera().getyOffset()),width,height, null);
	}
}
