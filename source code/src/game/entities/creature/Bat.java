package game.entities.creature;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import game.Handler;
import game.entities.EntityManager;
import game.gfx.Animation;
import game.gfx.Assets;
import game.state.GameState;

public class Bat extends Creature{
	Animation flyAn;
	private char direction;
	private int dirChangeCounter=0;
	public Bat(float x, float y) {
		super(x,y,48,33);
		CLASS = "bat";
		health=3;
		flyAn=new Animation(6,Assets.bat,"");
		setRandomDirection();
	}
	
	public void update() {
		flyAn.update();
		if (dirChangeCounter==30){
			setRandomDirection();
			dirChangeCounter=0;
		}
		yMove=0;
		xMove=0;
		if(stun<=0) {
			switch(direction) {
			case'u':
				yMove=speed;
				break;
			case'd':
				yMove=(0-speed);
				break;
			case'r':
				xMove=speed;
				break;
			case'l':
				xMove=0-speed;
				break;
			}
		}
		
		this.move();
		if(stun>0) {
			stun--;
		}
		dirChangeCounter++;
	}
	@Override
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
		ArrayList<String> entities =entityCollide(0,0);
		if (entities.contains("player")) {
			//EntityManager.killEntity(this);
		}
		moveX();
		moveY();
	}
	public void render(Graphics g){
		g.drawImage(flyAn.getCurrentFrame(), (int)(x-Handler.getCamera().getxOffset()),
				(int)(y-Handler.getCamera().getyOffset()), flyAn.getCurrentFrame().getWidth()*3, 
				flyAn.getCurrentFrame().getHeight()*3, null);
		if(GameState.showHitBox) {
			g.drawRect((int)(x+bounds.x-Handler.getCamera().getxOffset()), 
					(int)(y+bounds.y-Handler.getCamera().getyOffset()), bounds.width, bounds.height);
		}
			
	}
	private void setRandomDirection() {
		int num = ThreadLocalRandom.current().nextInt(1, 5);
		switch(num) {
		case 1:
			direction='u';
			return;
		case 2:
			direction ='r';
			return;
		case 3:
			direction='d';
			return;
		case 4:
			direction='l';
			return;
		}
		
	}

}
