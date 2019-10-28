package game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.gfx.Assets;

import java.awt.Rectangle;

public class Tile {
	
	protected boolean solid;
	protected BufferedImage texture;
	protected final String TILEID;
	protected final int ARRAYID;
	protected Rectangle bounds;
	
	public static final int WIDTH=48, HEIGHT=48;
	
	public Tile(BufferedImage texture,String TILEID,int ARRAYID, boolean solid) {
		this.texture=texture;
		this.TILEID=TILEID;
		this.ARRAYID=ARRAYID;
		this.solid=solid;
		TileManager.tiles[ARRAYID]=this;
		TileManager.tileIds[ARRAYID]=TILEID;
	}
	public void update() {
		
	}
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
	}
	public boolean getSolid() {
		return solid;
	}
	public String getTileId() {
		return TILEID;
	}

}
