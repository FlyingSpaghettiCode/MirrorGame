package menus;

import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PauseMenu extends Menu {
	
	public PauseMenu(Game main, double x, double y, double width, double height){
		super(main, x, y, width, height);
		
		elements.add(new MenuElement(x+width/4, y+height/4, width/2, height/2){
			boolean border = false;
			Color color = Color.BLACK;
			double strokeWidth = 5;
			
			@Override
			public void draw(GraphicsContext gc){
				if(border){
					gc.setFill(Color.YELLOW);
					gc.fillRect(x-strokeWidth, y-strokeWidth, width+2*strokeWidth, height+2*strokeWidth);
				}
				
				gc.setFill(color);
				gc.fillRect(x, y, width, height);
			}
			
			@Override
			public void mouseClicked(){
				color = new Color(Math.random(), Math.random(), Math.random(), 1);
			}
			
			@Override
			public void mouseEntered(){
				border = true;
			}
			
			@Override
			public void mouseExited(){
				border = false;
			}
		});
	}
}
