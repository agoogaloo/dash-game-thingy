package game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import game.Handler;
import game.entities.creature.Bat;
import game.entities.creature.Player;
import game.entities.statics.Heart;
import game.state.GameState;

public class EntityManager {
	public static Player player;
	private static ArrayList<Entity> entities;
	
	public static void init() {
		player = new Player(400,400);
		entities =new ArrayList <Entity>();
		entities.add(player);
	}
	
	public static void update() {
		for(Entity i:entities) {
			if (i.killed==true) {
				if(i.equals(player)) {
					player.killed=false;
					GameState.getLevel().reset();
					return;
				}
				entities.remove(i);
				return;
			}
			if(i.getX()<=Handler.getScreenWidth()+Handler.getCamera().getxOffset()+240&&
					i.getX()+i.getWidth()>=Handler.getCamera().getxOffset()-240&&
					i.getY()<=Handler.getScreenHeight()+Handler.getCamera().getyOffset()+240&&
					i.getY()+i.getHeight()>=Handler.getCamera().getyOffset()-240) {
				i.update();
				
			}
		}
		
	}
	public static void render(Graphics g) {
		for(Entity i:entities) {
			if(i.getX()<=Handler.getScreenWidth()+Handler.getCamera().getxOffset()&&
					i.getX()+i.getWidth()+15>=Handler.getCamera().getxOffset()&&
					i.getY()<=Handler.getScreenHeight()+Handler.getCamera().getyOffset()&&
					i.getY()+i.getHeight()+15>=Handler.getCamera().getyOffset()) {
				i.render(g);
			}
		}
	}
	
	public static void addEntity(Entity e) {
		entities.add(e);
	}
	public static void killEntity(Entity e) {
		e.killed=true;
	}
	public static Player getPlayer() {
		return player;
	}
	public static void setPlayer(Player playerin) {
		entities.remove(player);
		player = playerin;
		entities.add(player);
		
	}
	public static ArrayList<Entity> getEntities() {
		return entities;
	}
	public static void setEntities(ArrayList<Entity> entitiesin) {
		entities = entitiesin;
	}
	public static void clearEntities() {
		entities.clear();
		entities.add(player);
		
	}
}
