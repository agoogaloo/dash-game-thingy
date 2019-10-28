package game.state;

import java.awt.Graphics;

import game.Handler;
import game.UI.ClickListener;
import game.UI.UIImageButton;
import game.UI.UIManager;
import game.gfx.Assets;
import game.input.MouseManager;

public class MenuState extends States{

	public MenuState() {
		MouseManager.setUseUI(true);
		UIManager.clear();
		
		UIManager.addObject(new UIImageButton(200,200,100,50,Assets.test,new ClickListener() {
			@Override
			public void onClick() {
				States.setState(Handler.getGame().gameState);
				System.out.println("ok");
				
			}	
		},false));
		
	
	}

	@Override
	public void update() {
		System.out.println(Handler.getMouseManager().getMouseX()+","+ Handler.getMouseManager().getMouseY());
		UIManager.update();
	}

	@Override
	public void render(Graphics g) {
		UIManager.render(g);
	}

}
