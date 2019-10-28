package game.entities.statics;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;

public class Heart extends StaticEntity{
	public Heart(float x, float y) {
		super(x,y,33,27);
		CLASS = "heart";
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.health,(int)(x-Handler.getCamera().getxOffset()),
				(int)(y-Handler.getCamera().getyOffset()),width,height, null);
		
	}	
}


