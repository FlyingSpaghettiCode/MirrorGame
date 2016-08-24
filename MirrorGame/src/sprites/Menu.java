package sprites;

import game.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Menu {
	
	Main main;
	
	public Menu(Main main){
		this.main = main;
	}
	
	public void draw(GraphicsContext gc){
		
		gc.setFill(Color.GAINSBORO);
		gc.fillRect(main.WIDTH/4, main.HEIGHT/4, main.WIDTH/2, main.HEIGHT/2);
		
	}

}
