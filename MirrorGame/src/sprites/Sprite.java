package sprites;

import images.ResizableImage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import levels.Level;

/**
 * A bear bones sprite class; it can display at given coordinates and display an image.
 * @author Adriano Hernandez
 * @version 1.0
 * @date 24 July 2016
 */
public class Sprite extends Object{
	//instance variables
	private ResizableImage image;
	private double xPosition;
	private double yPosition;
	private String name;
	public boolean red = false;
	public boolean green = false;
	public boolean blue = false;
	
	private double width;
	private double height;
	
	//constructors with full functionality
	public Sprite() {
		this(0,0);
	}
	public Sprite(double x, double y){
		this(x, y, "/images/testImage.png");
	}
	public Sprite(double xPosition, double yPosition, String PATH){
		//some basic init
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		image = new ResizableImage(PATH);
		this.level = level;	
		setName();
	}
	public Sprite(String PATH){
		this(0,0,PATH);
	}

	public double getxPosition() {return xPosition;}
	public void setxPosition(double xPosition) {this.xPosition = xPosition;}
	public double getyPosition() {return yPosition;}
	public void setyPosition(double yPosition) {this.yPosition = yPosition;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	// Getters and setters for height and width
	public double getHeight(){return this.getImage().getActualHeight();}
	public double getWidth(){return this.getImage().getActualWidth();}
	public void setHeight(double h){this.getImage().setHeight(h);}
	public void setWidth(double w){this.getImage().setWidth(w);}
	
	//set a pretty random name of length 10
	public void setName(){
		String charList = 
				"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~!@#$%^&*()-=_+[]{}/|;':,.<>?\\";
		String n = "";
		for(int i = 0; i < 10; i++){
			int m = (int)(Math.random()*charList.length());
			n+=charList.substring(m,m+1);
		}
		setName(n);
	}
	
	//handle mechanisms that must be dealt with by the sprite
	public void handle(){}
	//draw at it's coordinates
	public void draw(GraphicsContext gc){
		gc.drawImage(image, xPosition, yPosition, image.getActualWidth(), image.getActualHeight());
	}
	
	public boolean isRed(){
		return red;
	}
	
	public boolean isGreen(){
		return green;
	}
	
	public boolean isBlue(){
		return blue;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
}
