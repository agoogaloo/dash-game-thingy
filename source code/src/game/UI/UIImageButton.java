package game.UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject{
	private BufferedImage[] images;
	private ClickListener clicker;
	private boolean hoverable;
	
	public UIImageButton(int x, int y, int width, int height, 
			BufferedImage[] images, ClickListener clicker, boolean hoverable) {
		super(x, y, width, height);
		this.images=images;
		this.clicker=clicker;
		this.hoverable=hoverable;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if (hoverable&&hover) {
			g.drawImage(images[1],x,y,width,height,null);
		}else {
			g.drawImage(images[0], x, y, width, height,null);
		}
		
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
