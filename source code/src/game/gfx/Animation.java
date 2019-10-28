package game.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index, partFrames;
	private BufferedImage[]frames;
	private long timer;
	private String id;
	public Animation(int speed,BufferedImage[] frames,String id) {
		this.frames=frames;
		this.speed=speed;
		this.id=id;
		index=0;
		partFrames=0;
	}
	public void update() {
		partFrames++;
		if (partFrames>=speed) {
			index++;
			partFrames=0;
			if (index>=frames.length) {
				index=0;	
			}
		}
	}
		
		
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	public int getIndex() {
		return index;
	}
	public String getId() {
		return id;
	}
	public void setFrame(int frame) {
		index=frame;
		partFrames=0;
	}
	public void printTimer() {
		System.out.println((timer)+", "+(index));
	}
}
