package game.entities.creature;
import game.Handler;
import game.entities.Entity;
import game.state.GameState;
import game.tiles.Tile;

public abstract class Creature extends Entity{
	
	
	public final float DEFAULT_SPEED = 5f;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		
		speed = DEFAULT_SPEED;
	}
	public void move() {
		if (xKnockback!=0) {
			if(xKnockback>0) {
				xKnockback-=2;
			}else {
				xKnockback+=2;
			}
		}
		if (yKnockback!=0) {
			if(yKnockback>0) {
				yKnockback-=2;
			}else {
				yKnockback+=2;
			}
		}
		yMove+=yKnockback;
		xMove+=xKnockback;
		if ((entityCollide(xMove, 0).size()==0)) {
			moveX();
		}
		if(entityCollide(0,yMove).size()==0) {
			moveY();
		}
	}
	
	public void moveX() {
		//checking collisions with wall on the right
		if (xMove>0) {
			int newX=(int)((x+xMove+bounds.x+bounds.width)/Tile.WIDTH);
			if (!tileCollide(newX,(int)((y+bounds.y)/Tile.HEIGHT))&&
					!tileCollide(newX,(int) ((y+bounds.y+bounds.height)/Tile.HEIGHT))){
				x+=xMove;
			}else {
				x=newX*Tile.WIDTH-bounds.x-bounds.width-1;
			}
			
		//checking for collisions with walls on the left
		}else if (xMove<0) {
			int newX=(int)((x+xMove+bounds.x)/Tile.WIDTH);
			
			if (!tileCollide(newX,(int)((y+bounds.y)/Tile.HEIGHT))&&
					!tileCollide(newX,(int) ((y+bounds.y+bounds.height)/Tile.HEIGHT))){
				x+=xMove;
			}else{
				x=newX*Tile.WIDTH+Tile.WIDTH-bounds.x;
			}
		}
		
		
	}
	public void moveY() {
		//collisions for going down
		if (yMove>0) {
			int newY=(int)(y+yMove+bounds.y+bounds.height)/Tile.HEIGHT;
			
			if (!tileCollide((int) ((x+bounds.x)/Tile.WIDTH),newY)&&
			!tileCollide((int) ((x+bounds.x+bounds.width)/Tile.WIDTH),newY)){
				y+=yMove;
			}else {
				y=newY*Tile.HEIGHT-bounds.y-bounds.height-1;
			}
		//collisions for going up	
		}else if(yMove<0) {	
			int newY=(int)(y+yMove+bounds.y)/Tile.HEIGHT;
				
			if (!tileCollide((int) ((x+bounds.x)/Tile.WIDTH),newY)&&
			!tileCollide((int) ((x+bounds.x+bounds.width)/Tile.WIDTH),newY)){
				y+=yMove;
			}else {
				y=newY*Tile.HEIGHT+Tile.HEIGHT-bounds.y;
			}
		}
	}
	protected boolean tileCollide(int x, int y) {
		return GameState.getLevel().getTile(x,y).getSolid();
		
	}
	protected boolean tileCollide(int x1, int y1, int x2, int y2) {
		if (x1==x2){
			if (y1>y2) {
				for(int y=y2; y<=y1; y++) {
					if ( GameState.getLevel().getTile(x1,y).getSolid()==true) {
						return true;
					}
				}return false;
			}else {
				for(int y=y1; y<=y2; y++) {
					if ( GameState.level.getTile(x1,y).getSolid()==true) {
						return true;
					}
				}return false;
			}
		}else {
			if (x1>x2) {
				for(int x=x2; x<=x1; x++) {
					if ( GameState.level.getTile(x,y1).getSolid()==true) {
						return true;
					}
				}return false;
			}else {
				for(int x=x1; x<=x2; x++) {
					if ( GameState.level.getTile(x,y1).getSolid()==true) {
						return true;
					}
				}return false;
			}
		}
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed){
		this.speed=speed;
	}
}
