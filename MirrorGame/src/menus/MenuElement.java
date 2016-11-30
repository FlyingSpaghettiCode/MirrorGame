package menus;

import javafx.scene.canvas.GraphicsContext;

public class MenuElement {
	
	double x;
	double y;
	double width;
	double height;
	
	public MenuElement(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void mouseEntered(){
		
	}
	
	public void mouseExited(){
		
	}
	
	public void mouseClicked(){
		
	}
	
	public void mouseHovering(){
		
	}
	
	public void draw(GraphicsContext gc){
		
	}
	
	public boolean inside(double xPos, double yPos){
		return this.between(x, xPos, x + width) && this.between(y, yPos, y + height);
	}
	
	private boolean between(double a, double o, double b){
		return a <= o && o <= b;
	}

}
