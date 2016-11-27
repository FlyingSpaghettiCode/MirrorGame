package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import levels.Level;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * A bear bones sprite class; it can display at given coordinates and display an image.
 * @author Adriano Hernandez
 * @version 1.0
 * @date 24 July 2016
 */
public abstract class Sprite extends Object{
	//instance variables
	private Image image;
	private double xPosition;
	private double yPosition;
	protected Level level;
	private String name;
	public boolean red = false;
	public boolean green = false;
	public boolean blue = false;
	
	public Sprite(double xPosition, double yPosition, double width, double height){
		//some basic init
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		this.level = Level.getInstance();	
		setName();
	}

	public Sprite(double xPosition, double yPosition, double width, double height, String PATH){
		this(xPosition, yPosition, width, height);
		
		image = new Image(PATH);
	}
	
	//all getters and setters for instance variables
	public Image getImage() {return image;}
	public void setImage(Image image) {this.image = image;}
	public double getxPosition() {return xPosition;}
	public void setxPosition(double xPosition) {this.xPosition = xPosition;}
	public double getyPosition() {return yPosition;}
	public void setyPosition(double yPosition) {this.yPosition = yPosition;}
	public Level getLevel() {return level;}
	public void setLevel(Level level) {this.level = level;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	// Getters and setters for height and width
	public double getHeight(){return level.main.tileW;}
	public double getWidth(){return level.main.tileW;}
	
	public Image getColoredImage(Image im){
		PixelReader bi = im.getPixelReader();
		
		WritableImage image = new WritableImage((int)im.getWidth(), (int)im.getHeight());
		PixelWriter pw = image.getPixelWriter();
		
		Color c = new Color(red ? 1 : 0, green ? 1 : 0, blue ? 1 : 0, 1);
		
		for(int a = 0; a < (int)im.getWidth(); a++){
			for(int b = 0; b < (int)im.getHeight(); b++){
				Color color = bi.getColor(a, b);
				pw.setColor(a, b, color.getRed() > 0 ? c : color);
			}
		}
		
		return image;
	}
	
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
		gc.drawImage(image, xPosition, yPosition, level.main.tileW, level.main.tileW);
	}
	
	public abstract void update();
	
	public boolean isRed(){
		return red;
	}
	
	public boolean isGreen(){
		return green;
	}
	
	public boolean isBlue(){
		return blue;
	}
}
