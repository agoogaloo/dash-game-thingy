package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.UI.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{
	private boolean leftClick, rightClick;
	private int mouseX, mouseY;
	private static boolean useUI=true;
	
	public MouseManager() {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		mouseY=e.getY();
		
		UIManager.onMouseMove(e);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1) {
			leftClick=true;
		}else if(e.getButton()==MouseEvent.BUTTON3) {
			rightClick=true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton()==MouseEvent.BUTTON1) {
			leftClick=false;
		}else if(e.getButton()==MouseEvent.BUTTON3) {
			rightClick=false;
		}
		UIManager.onMouseRelease(e);
	}
	public static void setUseUI(boolean use) {
		useUI=use;
	}
	public boolean getLeftClick() {
		return leftClick;
	}

	public boolean getRightClick() {
		return rightClick;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}


}
