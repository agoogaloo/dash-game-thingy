package game.tiles;

import game.gfx.Assets;

public class TileManager {
	
	public static Tile[]tiles = new Tile [64];
	public static String[]tileIds= new String[64];
	public static Tile empty =new Tile(Assets.empty, "0",0, false);
	public static Tile grassT1 = new Tile(Assets.grassU1, "u1",1, true);
	public static Tile grassT2 = new Tile(Assets.grassU2, "u2",2, true);
	public static Tile grassR=new Tile(Assets.grassR, "r",3, true);
	public static Tile grassL =new Tile(Assets.grassL, "l",4,true);
	public static Tile grassD1 =new Tile(Assets.grassD1, "d1",5, true);
	public static Tile grassD2 =new Tile(Assets.grassD2, "d2",6, true);
	public static Tile grassC =new Tile(Assets.grassC, "c",7, true);
	public static Tile grassCornerOutUR =new Tile(Assets.grassCornerOutUR, "ur",8, true);
	public static Tile grassCornerOutUL =new Tile(Assets.grassCornerOutUL, "ul",9, true);
	public static Tile grassCornerOutDR =new Tile(Assets.grassCornerOutDR, "dr",10, true);
	public static Tile grassCornerOutDL =new Tile(Assets.grassCornerOutDL, "dl",11, true);
	public static Tile grassCornerInUR =new Tile(Assets.grassCornerInUR, "UR",12, true);
	public static Tile grassCornerInUL =new Tile(Assets.grassCornerInUL, "UL",13, true);
	public static Tile grassCornerInDR =new Tile(Assets.grassCornerInDR, "DR",14, true);
	public static Tile grassCornerInDL =new Tile(Assets.grassCornerInDL, "DL",15, true);
	
	public static int getTileArrayId(String tileId) {
		int id=java.util.Arrays.asList(tileIds).indexOf(tileId);
		if (id==-1) {
			id=7;
			System.out.println(" tile id '"+tileId+"' not recognized.");
		}
		
		return id;
	}
}
