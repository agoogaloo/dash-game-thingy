package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import game.display.Display;
import game.gfx.Assets;
import game.gfx.Camera;
import game.input.KeyManager;
import game.input.MouseManager;
import game.state.GameState;
import game.state.MenuState;
import game.state.PauseState;
import game.state.States;
import game.tiles.Tile;

public class Game implements Runnable{
	private final int FPS = 60;
	private final int DELAY = 1000000000/FPS;
	
	private Display display;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;

	private boolean run = false;
	private int width, height;
	private String title;
	
	public States gameState;

	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private Camera camera;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		mouseManager= new MouseManager();
		keyManager = new KeyManager();
	}
	
	private void init() {
		display= new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		camera= new Camera(0,0);
		gameState= new GameState();
		gameState.setState(gameState);
	}
	
	private void update() {
		keyManager.update();
		if(States.getState()!=null) {
			States.getState().update();
		}
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		
		g.clearRect(0,0,width+Tile.WIDTH,height+Tile.HEIGHT);
		if (States.getState()!=null) {
			States.getState().render(g);
		}
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		long delta = 0;
		long before = System.nanoTime();
		long now;
		
		long timerStart=System.nanoTime();
		long timer=0;
		int ticks = 0;
		while (run) {
			now = System.nanoTime();
			before=now;
			update();
			render();
			delta=now-before;
			ticks++;
			timer = now-timerStart;
			while(delta<DELAY) {
				now = System.nanoTime();
				delta=now-before;
			}
			if (timer>=1000000000) {
				System.out.println(ticks+"fps");
				timerStart=System.nanoTime();
				ticks=0;
				
			}
			
		}
		stop();
	}
	
	public synchronized void start() {
		if (run) {
			return;
		}
		run = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if (!run) {
			return;
		}
		run=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	public Camera getCamera() {
		return camera;
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
