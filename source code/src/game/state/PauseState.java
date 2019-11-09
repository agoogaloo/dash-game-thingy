package game.state;

import java.awt.Graphics;

import game.Handler;
import game.gfx.Assets;
import game.levels.Level;

public class PauseState extends States {
	private int selection = 1;
	private int timer=20;

	@Override
	public void update() {
		if(Handler.getKeyManager().up&&timer<=0) {
			selection-=1;
			
			timer=10;
		}
		if(Handler.getKeyManager().down&&timer<=0) {
			selection+=1;
			
			timer=10;
		}
		if(selection>5) {
			selection=1;
		}else if(selection<1) {
			selection=5;	
		}
		if(Handler.getKeyManager().esc&&timer<=0) {
			States.setState(Handler.getGame().gameState);
			Level.setPauseTimer(20);
		}
		if(Handler.getKeyManager().space) {
			switch(selection) {
			case 1: 
				Level.setPauseTimer(20);
				break;
			case 2:
				GameState.showHitBox=!GameState.showHitBox;
				break;
			case 3:
				GameState.level.load(1);
				break;
			case 4:
				
				GameState.level.load(2);
				break;
			case 5:
				System.exit(1);
				break;
			}
			States.setState(Handler.getGame().gameState);
		}
		if(timer>0) {
			timer--;
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pauseScreen,0,0, null);
		g.drawImage(Assets.select,300,246+(selection*54), null);
	}

}
