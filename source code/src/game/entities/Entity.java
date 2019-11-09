package game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Handler;

public abstract class Entity {
	public final int DEFAULT_HP = 10;
	protected String CLASS;
	protected boolean killed=false,destructible = true;
	protected float x, y;
	protected int width, height,health,xKnockback=0,yKnockback=0,stun,iFrames=0;
	protected Rectangle bounds;
	
	public Entity(float x, float y, int width, int height) {
		this.health=DEFAULT_HP;
		this.x=x;
		this.y=y;	
		this.width = width;
		this.height=height;
		this.bounds = new Rectangle(0,0,width,height);
		
	}
	public void getHit(int damage, int xKnockback, int yKnockback,int stun) {
		health-=damage;
		this.xKnockback=xKnockback;
		this.yKnockback=yKnockback;
		this.stun=stun;
		iFrames=5;
		if(destructible&&health<=0) {
			EntityManager.killEntity(this);
		}
	}
	public void getHit(int damage, int xKnockback, int yKnockback,int stun,int iFrames) {
		health-=damage;
		this.xKnockback=xKnockback;
		this.yKnockback=yKnockback;
		this.stun=stun;
		this.iFrames=iFrames;
		if(destructible&&health<=0) {
			EntityManager.killEntity(this);
		}
	}
	public ArrayList<String> entityCollide(float xOffset, float yOffset) {
		ArrayList<String> entities = new ArrayList<String>();
		for (Entity e:EntityManager.getEntities()) {
			if (e.equals(this)) {
				continue;	
			}
			if (e.getMovingBounds(0,0).intersects(this.getMovingBounds(xOffset, yOffset))){
				entities.add(e.getCLASS());
			}
		}
		return entities;
	}
	public String getCLASS() {
		return CLASS;
	}
	public Rectangle getMovingBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x+bounds.x+xOffset),(int)(y+bounds.y+yOffset), bounds.width, bounds.height);
		
	}
	public Rectangle getBounds() {
		return this.bounds;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public abstract void update();
	
	public abstract void render(Graphics g);

}
