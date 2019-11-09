package game.levels;

import java.awt.Graphics;

import game.Handler;
import game.entities.EntityManager;
import game.entities.creature.Bat;
import game.entities.creature.Player;
import game.entities.statics.Exit;
import game.gfx.Assets;
import game.state.PauseState;
import game.state.States;
import game.tiles.Tile;
import game.tiles.TileManager;
import game.utils.Utils;

public class Level {
	private int width, height, id;
	
	//private String mapPath,entPath;
	private static int pauseTimer=10;
	private int[][] map;
	private Tile t;
	private Exit exit;

	
	public Level(int level) {
		EntityManager.init();
		load(level);
	}
	
	public void update() {
		EntityManager.update();
		if (exit!=null){
			exit.update();
		}
		if(Handler.getKeyManager().esc&&pauseTimer<=0){
			States.setState(new PauseState());
			
		}
		if(pauseTimer>0) {
			pauseTimer--;
		}
	}
	
	public void render(Graphics g) {
		
		//only rendering what is actually on screen
		g.drawImage(Assets.backGround,0,0, null);
		
		int xStart=(int) Math.max(0, Handler.getCamera().getxOffset()/Tile.WIDTH);
		int xEnd=(int) Math.min(width, ((Handler.getCamera().getxOffset()+
				Handler.getScreenWidth())/Tile.WIDTH)+1 );
		int yStart=(int) Math.max(0, Handler.getCamera().getyOffset()/Tile.HEIGHT);
		int yEnd=(int) Math.min(height, ((Handler.getCamera().getyOffset()+
				Handler.getScreenHeight())/Tile.HEIGHT)+1 );
		for (int y=yStart;y<yEnd;y++) {
			for (int x=xStart;x<xEnd;x++) {
				getTile(x,y).render(g, (int) (x*Tile.WIDTH-Handler.getCamera().getxOffset()), 
						(int) (y*Tile.HEIGHT-Handler.getCamera().getyOffset()));
			}
		}
		//g.drawImage(Assets.filter,0,0,Handler.getScreenWidth(), 
				//Handler.getScreenHeight(), null);
		if(exit!=null) {
			exit.render(g);
		}
		EntityManager.render(g);
		
	}
	public void load(int level){
		id=level;
		int spawnX, spawnY;
		String mapPath="/levels/level"+level+"map.txt";
		String entPath="/levels/level"+level+"ent.txt";
		String file = Utils.fileToString(mapPath);
		
		String[] tokens = file.split("\\s+");
		width = Integer.parseInt(tokens[0]);
		height= Integer.parseInt(tokens[1]);
		spawnX = Integer.parseInt(tokens[2]);
		spawnY = Integer.parseInt(tokens[3]);
		
		map = new int[width][height];
		for(int y=0; y<height;y++) {
			for(int x=0;x<width;x++) {
				
				map[x][y]=TileManager.getTileArrayId(tokens[(x+y*width)+4]);	
			}	
		}
		
		EntityManager.clearEntities();
		file = Utils.fileToString(entPath);
		tokens = file.split("\\s+");
		for(int y=0; y<height;y++) {
			for(int x=0;x<width;x++) {
				String token =tokens[x+y*width];
				if(!(token.equals("b")||token.equals("-")||token.equals("e"))) {
					System.out.println("entity ID '"+token+"' not recognized, no entity added");
				}
				if(token.equals("b")) {
					EntityManager.addEntity(new Bat(x*Tile.WIDTH,y*Tile.HEIGHT));
				}else if(token.equals("e")) {
					exit=new Exit(x*Tile.WIDTH,y*Tile.HEIGHT);
				}
			}
		}
		EntityManager.setPlayer(new Player(spawnX*Tile.WIDTH-Tile.WIDTH,spawnY*Tile.HEIGHT-EntityManager.getPlayer().getHeight()));
	
	}	
	public void reset() {
		load(id);
		
	}
	public void nextLevel() {
		id++;
		if (id>2) {
			id=1;
		}
		load(id);
	}
	public void setLevel(int level) {
		load(level);
	}
	public Tile getTile(int x, int y) {
		if (x<0||y<0||x>width-1||y>height-1) {
			return TileManager.empty;
		}
		 t = TileManager.tiles[map[x][y]];
		if (t==null) {
			return TileManager.empty;
		}
		else return t;
	}
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public static void setPauseTimer(int time) {
		pauseTimer=time;
	}

}
