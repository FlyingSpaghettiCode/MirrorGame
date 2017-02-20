package menus;

import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import levels.Level;

public class PauseMenu extends Menu {
	
	public PauseMenu(Game main, double x, double y, double width, double height){
		super(main, x, y, width, height);
		
		elements.add(new MenuElement(x+width/4, y+height/4, width/2, height/2){
			boolean border = false;
			Color color = Color.BLACK;
			double strokeWidth = 10;
			double t = 0;
			
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
			
			@Override
			public void mouseHovering(){
				strokeWidth = Math.abs(Math.sin(t)) * 6 + 4;
				t += Math.PI/45;
			}
		});
		
		elements.add(new MenuElement(x+width * 3.0/8, y+height * 5.0/6, width/4, height/12){
			boolean border = false;
			Color color = Color.BLACK;
			double strokeWidth = 5;
			double t = 0;
			
			@Override
			public void draw(GraphicsContext gc){
				if(border){
					gc.setFill(Color.YELLOW);
					gc.fillRect(x-strokeWidth, y-strokeWidth, width+2*strokeWidth, height+2*strokeWidth);
				}
				
				gc.setFill(color);
				gc.fillRect(x, y, width, height);
				
				gc.setFill(Color.WHITESMOKE);
				gc.setFont(Font.font("Verdant", FontWeight.BOLD, 18));
				double[] metrics = Level.getFontMetrics(Font.font("Verdant", FontWeight.BOLD, 18), "Resume");
				gc.fillText("Resume", x + width/2 - metrics[0]/2, y + height/2 + metrics[1]/2);
			}
			
			@Override
			public void mouseClicked(){
				main.setPaused(!main.isPaused());
			}
			
			@Override
			public void mouseEntered(){
				border = true;
				color = Color.rgb(50, 50, 50);
			}
			
			@Override
			public void mouseExited(){
				border = false;
				color = Color.BLACK;
			}
			
			@Override
			public void mouseHovering(){
				strokeWidth = Math.abs(Math.sin(t)) * 3 + 2;
				t += Math.PI/45;
			}
		});
	}
}
