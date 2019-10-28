package game.UI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {
	private static ArrayList<UIObject> objects = new ArrayList<UIObject>();
	
	public static void update() {
		for(UIObject o:objects) {
			o.update();
		
		}
		
	}
	public static void render(Graphics g) {
		for(UIObject o:objects) {
			o.render(g);
	
		}
		
	}
	public static void onMouseMove(MouseEvent e) {
		for(UIObject o:objects) {
			o.onMouseMove(e);
	
		}
	}
	public static void onMouseRelease(MouseEvent e) {
		for(UIObject o:objects) {
			o.onMouseRelease(e);
		}
	}
	public static void addObject(UIObject o) {
		objects.add(o);
	}
	public static void clear() {
		objects.clear();
	}

}
