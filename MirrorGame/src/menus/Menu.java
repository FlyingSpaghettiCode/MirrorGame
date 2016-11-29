package menus;

import java.util.HashSet;

import game.Game;
import input.MouseEventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Menu implements MouseEventHandler {

	Game main;
	
	double x;
	double y;
	double width;
	double height;
	
	double oldMx = 0;
	double oldMy = 0;
	
	HashSet<MenuElement> elements = new HashSet<MenuElement>();

	public Menu(Game main, double x, double y, double width, double height) {
		this.main = main;
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		main.getMouseIn().add(this);
	}

	public void draw(GraphicsContext gc) {
		gc.setFill(Color.GAINSBORO);
		gc.fillRect(x, y, width, height);
		
		for(MenuElement element : elements)
			element.draw(gc);
	}

	@Override
	public void handle(MouseEvent e) {
		
		if(this.between(x, e.getScreenX(), x+width) && this.between(y, e.getScreenY(), y+height)){
			boolean move = e.getEventType().getName().equals("MOUSE_MOVED");
			
			for(MenuElement el : elements){
				boolean now = el.inside(e.getScreenX(), e.getScreenY());
				
				if(move){
					boolean old = el.inside(oldMx, oldMy);
					if(!old && now)
						el.mouseEntered();
					else if(old && !now)
						el.mouseExited();
				}
				else if(now)
					el.mouseClicked();
			}
		}
		
		oldMx = e.getScreenX();
		oldMy = e.getScreenY();
	}
	
	private boolean between(double a, double o, double b){
		return a <= o && o <= b;
	}

}