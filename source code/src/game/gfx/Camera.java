package game.gfx;

import game.Handler;
import game.entities.Entity;
import game.state.GameState;
import game.tiles.Tile;

public class Camera {
	
	private float xOffset, yOffset;
	
	public Camera(float xOffset, float yOffset) {
		this.xOffset=xOffset;
		this.yOffset=yOffset;
	}
	
	public void move(float xMove, float yMove) {
		xOffset+=xMove;
		yOffset+=yMove;
	}
	public void limit() {
		if (xOffset<0) {
			xOffset=0;
		}else if(xOffset>GameState.level.getWidth()*Tile.WIDTH-Handler.getScreenWidth()) {
			xOffset=(GameState.level.getWidth()*Tile.WIDTH)-Handler.getScreenWidth();
		}
		if (yOffset<0) {
			yOffset=0;
		}else if(yOffset>GameState.level.getHeight()*Tile.HEIGHT-Handler.getScreenHeight()) {
			yOffset=(GameState.level.getHeight()*Tile.HEIGHT)-Handler.getScreenHeight();
		}
	}
	public void centerOnEntity(Entity e) {
		xOffset = e.getX() - Handler.getScreenWidth()/2 + e.getWidth()/2;
		yOffset= e.getY()-Handler.getScreenHeight()/2 +e.getHeight()/2;		
		limit();
						
	}
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

}
