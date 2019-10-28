package game.entities.creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.Handler;
import game.entities.Entity;
import game.entities.EntityManager;
import game.gfx.Animation;
import game.gfx.Assets;
import game.state.GameState;
import game.tiles.Tile;

public class Player extends Creature {

	Animation rightIdleAn, leftIdleAn, leftAn, rightAn,attackLAn,attackRAn,
	dashAttackLAn,dashAttackRAn,dashAttackULAn,dashAttackURAn;
	private final ArrayList<Animation> ANIMATIONS;
	private final int DASHLENGTH = (int) (Tile.WIDTH * 3.5);
	private final double GRAVITYACCELERATION = 1.2;
	private ArrayList<Character> inputs;
	private char direction='r', dashDirection=' ';
	private boolean moving = false,dashAttack=false,attacking=false;
	private float dashLocX, dashLocY, dashTime = 6f, checkX, checkY, gravitySpeed = 0;
	private Animation currentAnimation;
	private int invincible=0,attackTime=0,picXOffset,picYOffset;
	//private Rectangle hitbox2 = new Rectangle((int)(x-20),(int)(y-13),42,44);
	private int sendMessage = 0;
	private int maxHeath=3;
	public Player(float x, float y) {
		super(x, y, 45, 63);
		CLASS = "player";
		leftIdleAn = new Animation(6, Assets.guyIdleL,"leftIdleAn");
		rightIdleAn = new Animation(6, Assets.guyIdleR,"rightIdleAn");
		leftAn = new Animation(5, Assets.guyLeft,"leftAn");
		rightAn = new Animation(5, Assets.guyRight,"rightAn");
		attackLAn= new Animation(6,Assets.guyAttackL,"attackLAn");
		attackRAn=new Animation(6,Assets.guyAttackR,"attackRAn");
		dashAttackLAn= new Animation(6,Assets.guyDashAttackL,"dashAttackLAn");
		dashAttackRAn=new Animation(6,Assets.guyDashAttackR,"dashAttackRAn");
		dashAttackULAn=new Animation(6,Assets.guyDashAttackUL,"dashAttackULAn");
		dashAttackURAn=new Animation(6,Assets.guyDashAttackUR,"dashAttackURAn");
		inputs = new ArrayList<Character>();
		currentAnimation=rightIdleAn;
		
		ANIMATIONS=new ArrayList<Animation>();
		ANIMATIONS.add(rightIdleAn);
		ANIMATIONS.add(leftIdleAn);
		ANIMATIONS.add(leftAn);
		ANIMATIONS.add(rightAn);
		ANIMATIONS.add(attackLAn);
		ANIMATIONS.add(attackRAn);
		ANIMATIONS.add(dashAttackLAn);
		ANIMATIONS.add(dashAttackRAn);
		ANIMATIONS.add(dashAttackULAn);
		ANIMATIONS.add(dashAttackURAn);
		health=3;
		dashLocX = x;
		dashLocY = y;
	}

	@Override
	public void move() {
		checkX = x;
		checkY = y;
		if (xKnockback>1||xKnockback<-1) {
			moving=false;
			xKnockback*=0.95;
		}else {
			xKnockback=0;
		}
		if (yKnockback>1||yKnockback<-1) {
			moving=false;
			yKnockback*=0.95;
			
		}else {
			yKnockback=0;
		}
		checkY+=yKnockback;
		checkX+=xKnockback;
		if (!moving) {
			if (inputs.contains('r') && stun <= 0) {
				dashLocX = x + DASHLENGTH;
				moving = true;
				dashDirection = 'r';
				direction = 'r';
			} else if (inputs.contains('l') && stun <= 0) {
				dashLocX = x - DASHLENGTH;
				moving = true;
				dashDirection = 'l';
				direction = 'l';
			} else if (inputs.contains('u') && stun <= 0) {
				dashLocY = y - DASHLENGTH;
				moving = true;
				dashDirection = 'u';
			} else if (inputs.contains('d') && stun <= 0) {
				dashLocY = y + DASHLENGTH;
				moving = true;
				dashDirection = 'd';
			}
			checkY += gravitySpeed;
			if(gravitySpeed<=30) {
				gravitySpeed += GRAVITYACCELERATION;
			}
		} else {
			gravitySpeed = 0;
			switch (dashDirection) {
			case 'r':
				if (x <= dashLocX) {
					checkX = x + ((dashLocX - x) / dashTime) + 3.5f;
				} else {
					checkX = dashLocX;
					moving = false;
					//dashDirection = ' ';
					stun = 7;

				}
				break;
			case 'l':
				if (x >= dashLocX) {
					checkX = x - (((x - dashLocX) / dashTime) + 3.5f);
				} else {
					checkX = dashLocX;
					moving = false;
					//dashDirection = ' ';
					stun = 7;
				}
				break;
			case 'u':
				if (y >= dashLocY) {
					checkY = y - (((y - dashLocY) / dashTime) + 2.5f);
				} else {
					checkY = dashLocY;
					moving = false;
					//dashDirection = ' ';
					stun = 3;
				}
				break;
			case 'd':
				if (y <= dashLocY) {
					checkY = y + ((dashLocY - y) / dashTime) + 2.5f;
				} else {
					checkY = dashLocY;
					moving = false;
					//dashDirection = ' ';
					stun = 3;
				}
				break;
			}
		}
		entityResponse();
		wallCollide();
	}

	private void entityResponse() {
		ArrayList<String> entities = entityCollide(0, 0);
		
		if (entities.contains("bat")&&invincible<=0) {
			health--;
			invincible=40;
			moving=false;
			if(direction=='r') {
				getHit(0,-17,-15,30);
			}else {
				getHit(0,17,-15,30);
			}
			
		}
	}

	private void wallCollide() {
		// checking collisions at checkY and y
		int checkXL = (int) ((checkX + bounds.x) / Tile.WIDTH);
		int checkXR = (int) ((checkX + bounds.x + bounds.width) / Tile.WIDTH);
		int checkYU = (int) ((checkY + bounds.y) / Tile.WIDTH);
		int checkYD = (int) ((checkY + bounds.y + bounds.height) / Tile.WIDTH);
		// wall collision right
		if (checkX > x) {
			if (tileCollide(checkXR, checkYU, checkXR, checkYD)) {
				x = (checkXR * Tile.WIDTH) - (bounds.width + bounds.x);
				moving = false;
				gravitySpeed = 0;
				stun = 7;
					xKnockback=0;
			} else {
				x = checkX;
			}
			// wall collision left
		} else if (checkX < x) {
			if (tileCollide(checkXL, checkYU, checkXL, checkYD)) {
				x = (checkXL * Tile.WIDTH) + Tile.WIDTH + 1;
				moving = false;
				gravitySpeed = 0;
				stun = 7;
				xKnockback=0;
			} else {
				x = checkX;
			}
		}
		// wall collisions downward
		if (checkY > y) {
			if (tileCollide(checkXR, checkYD) || tileCollide(checkXL, checkYD)) {
				y = (checkYD * Tile.WIDTH) - bounds.height - bounds.y - 1;
				moving = false;
				gravitySpeed = 0;
				yKnockback=0;
			} else {
				y = checkY;
			}
			// wall collision up
		} else if (checkY < y) {
			if (tileCollide(checkXR, checkYU) || tileCollide(checkXL, checkYU)) {
				y = (checkYU * Tile.WIDTH) + Tile.WIDTH;
				moving = false;
				gravitySpeed = 0;
				stun = 3;
				if (yKnockback>0) {
					yKnockback=0;
				}
			} else {
				y = checkY;
			}
		}

	}

	@Override
	public void update() {
		
		getInput();
		//hitbox2 = new Rectangle((int)(x+3),(int)(y-13),0,0);
		attack();
		move();
		//checking/doing stuff if you're dead
		if(health<=0) {
			killed=true;
			System.out.println("you have die");
		}
		setAnimation();
		
		
		// setting h-box based on animation frame
		setAnimationBounds();
		updateAnimation();
		// updating hitstun/lag counter
		if (stun > 0) {
			stun --;
		}
		if (invincible > 0) {
			invincible--;
		}
		
		// sending things to console
		if (sendMessage > 60) {
			System.out.println("cordanites:" + x + "," + y+" knockback:"+xKnockback+","+yKnockback + " stun:" + stun);
			sendMessage = 0;
		}
		sendMessage ++;
		
	}
	private void attack(){
		Rectangle hitbox=new Rectangle(0,0,0,0); 
		if(inputs.contains('a')&&moving){
			dashAttack=true;
			attacking=true;
			attackTime=16;
		}else if(inputs.contains('a')&&stun==0) {
			attacking=true;
			attackTime=16;
		}
		
		if(!attacking) {
			return;
		}
		if (attackTime<=0) {
			attacking=false;
			dashAttack=false;
			
		}
		

		if(dashAttack&&!moving) {
			attackTime--;
			stun=30;
			System.out.println("ataaaacking");
			switch(dashDirection) {
			
			case'r':
				hitbox= new Rectangle((int)(x+20),(int)(y+13),61,46);
				break;
			case'l':
				hitbox= new Rectangle((int)(x-41),(int)(y+13),61,46);
				
				break;
			case'u':
				hitbox= new Rectangle((int)(x-6),(int)(y-13),50,30);
				break;
			default:
				hitbox= new Rectangle((int)(x+3),(int)(y-13),1,0);
			}  
			for(Entity i:EntityManager.getEntities()) {
				if (i.equals(this)){
					continue;
				}
				if(hitbox.intersects(i.getMovingBounds(0, 0))) {
					if(direction=='r') {
						i.getHit(1,30,-10,20);
					}else {
						i.getHit(1,-30,-10,20);
					}
				}
			}
			
		}else if(attacking&&!dashAttack) {
			attackTime--;
			stun=15;
			if(direction=='r') {
				hitbox= new Rectangle((int)(x+20),(int)(y+13),42,44);
			}else {
	
				hitbox= new Rectangle((int)(x-20),(int)(y+13),42,44);
			}  
			for(Entity i:EntityManager.getEntities()) {
				if (i.equals(this)){
					continue;
				}
				if(hitbox.intersects(i.getMovingBounds(0, 0))) {
					System.out.println("ataaaack!");
					if(direction=='r') {
						i.getHit(1,27,-10,35);
					}else {
						i.getHit(1,-27,-10,35);
					}	
				}
			}
		}		
	}
	private void updateAnimation(){
		switch(currentAnimation.getId()) {
		case "leftIdleAn":
			leftIdleAn.update();
			break;
		case "rightIdleAn":
			rightIdleAn.update();
			
			break;
		case "rightAn":
			rightAn.update();
			break;
		case"leftAn":
			leftAn.update();
			break;
		case "attackLAn":
			attackLAn.update();
			break;
		case "attackRAn":
			attackRAn.update();
			break;
		case "dashAttackLAn":
			dashAttackLAn.update();
			break;
		case "dashAttackRAn":
			dashAttackRAn.update();
			break;
		case "dashAttackULAn":
			dashAttackULAn.update();
			break;
		case "dashAttackURAn":
			dashAttackURAn.update();
			break;
		}
		for (Animation i:ANIMATIONS){
			if(i!=currentAnimation) {
				i.setFrame(0);
			}
		}
	}
	private void setAnimation() {
		
		if (moving) {
			
			switch (dashDirection) {
			case 'l':
				currentAnimation = leftAn;
				break;
			case 'r':
				currentAnimation = rightAn;
				break;
			default:
				if (direction=='r'){
					currentAnimation=rightIdleAn;
				}else {
					currentAnimation=leftIdleAn;
				}

			}
		}else if (attacking) {
			if (dashAttack) {
				switch(dashDirection) {
				case 'l':
					currentAnimation=dashAttackLAn;
					break;
				case'r':
					currentAnimation=dashAttackRAn;
					break;
				case'u':
					currentAnimation=(direction=='l') ? dashAttackULAn:dashAttackURAn;
					
				}
				return;
			}
			switch(direction) {
			case 'l':
				currentAnimation=attackLAn;
				break;
			case'r':
				currentAnimation=attackRAn;
				break;
			
			}
		}else {
			switch (direction) {
			case 'l':
				currentAnimation = leftIdleAn;
				break;
			case 'r':
				currentAnimation =rightIdleAn;
				break;
			}
		}
	}
	@Override
	public void render(Graphics g) {
		
		g.drawImage(currentAnimation.getCurrentFrame(), (int) (x - Handler.getCamera().getxOffset())+picXOffset,
				(int) (y - Handler.getCamera().getyOffset())+picYOffset,currentAnimation.getCurrentFrame().getWidth() * 3,
				currentAnimation.getCurrentFrame().getHeight() * 3, null);
		if(GameState.showHitBox) {
			g.drawRect((int) (x + bounds.x - Handler.getCamera().getxOffset()),
					(int) (y + bounds.y - Handler.getCamera().getyOffset()), bounds.width, bounds.height);
		}
		/*g.drawRect((int)(hitbox2.getX()- Handler.getCamera().getxOffset()),
				(int)(hitbox2.getY()- Handler.getCamera().getyOffset()),
				(int)(hitbox2.getWidth()),(int)(hitbox2.getHeight()));*/
		
	}
	private void setAnimationBounds() {
		picXOffset=0;
		picYOffset=0;
		
		switch(currentAnimation.getId()) {
		case"rightIdleAn":
			bounds.setBounds(0,2, 39, 60);
			picXOffset=-6;
			break;
		case"leftIdleAn":
			bounds.setBounds(0, 2, 39, 60);
			break;
		case"rightAn":
			bounds.setBounds(10, 9, 37, 53);
			break;
		case"leftAn":
			bounds.setBounds(10, 9, 37, 53);
			break;
		case"attackLAn":
			picXOffset=-21;
			bounds.setBounds(0, 2,39,60);
			break;
		case"attackRAn":
			picXOffset=-6;
			break;
		case"dashAttackLAn":
			picXOffset=-41;
			bounds.setBounds(0, 2,39,60);
			break;
		case"dashAttackRAn":
			picXOffset=-3;
			break;
		case"dashAttackULAn":
			picYOffset=-13;
			picXOffset=-6;
			break;
		case"dashAttackURAn":
			picYOffset=-13;
			picXOffset=-6;
			break;
			
		}
	}
	@Override
	public void getHit(int damage, int xKnockback, int yKnockback,float stun) {
		health-=damage;
		this.xKnockback=xKnockback;
		this.yKnockback=yKnockback;
		this.stun=stun;
	}
	private void getInput() {
		inputs.clear();
		
			if (Handler.getKeyManager().up) {
				inputs.add('u');
			}
			if (Handler.getKeyManager().down) {
				inputs.add('d');
			}
			if (Handler.getKeyManager().left) {
				inputs.add('l');
			}
			if (Handler.getKeyManager().right) {
				inputs.add('r');
			}
			if (Handler.getKeyManager().space) {
				inputs.add('a');
			}
		
	}
	private int getCurrentAnimationIndex() {
		switch (currentAnimation.getId()) {
		case "leftAn":
			return leftAn.getIndex();
		case "rightAn":
			return rightAn.getIndex();
		case "leftIdleAn":
			return leftIdleAn.getIndex();
		case "rightIdleAn":
			return rightIdleAn.getIndex();
		case "attackLAn":
			return attackLAn.getIndex();
		case "attackRAn":
			return attackRAn.getIndex();
		default:
			return rightAn.getIndex();
		}
	}
	public void heal() {
		health=maxHeath;
	}
}
