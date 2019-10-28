package game.UI;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	int x,y,width,height;
	Rectangle bounds;
	boolean hover = false;
	
	public UIObject(int x, int y, int width, int height) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.bounds = new Rectangle(x,y,width,height);
	}
	
	public void onMouseMove(MouseEvent e) {
		if (bounds.contains(e.getX(), e.getY())){
			hover = true;
		}else {
			hover=false;
		}
		
	}
	public void onMouseRelease(MouseEvent e){
		if (hover) {
			onClick();
		}
		
	}
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
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
	
	
	
	
}
