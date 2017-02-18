package sprites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import levels.Level;


/**
 * A bare bones sprite class; it can display at given coordinates and display an image.
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
        //deprecated
	public void draw(GraphicsContext gc){
                PixelReader mask = image.getPixelReader();
                PixelWriter canvas = gc.getPixelWriter();
                int color = 0xFF000000 
                        + (red ? 0x00FF0000 : 0) 
                        + (green ? 0x0000FF00 : 0)
                        + (blue ? 0x000000FF : 0);
                double zoom = level.main.tileW/image.getWidth();
                for(int j = 0; j < image.getHeight(); j++)
                    for(int i = 0; i < image.getWidth(); i++)
                        canvas.setArgb((int)(i * zoom + xPosition), (int)(j * zoom + yPosition), 
                            (mask.getArgb(i,j) & color)|0xFF000000);
                
		//gc.drawImage(image, xPosition, yPosition, level.main.tileW, level.main.tileW);
	}
        //Draws at its coordinates. Can handle overlapping. 
        public void draw(WritableImage buffer)
        {
            int color = 0xFF000000 
                + (red ? 0x00FF0000 : 0) 
                + (green ? 0x0000FF00 : 0)
                + (blue ? 0x000000FF : 0);
            draw((int) xPosition, (int) yPosition, level.main.tileW, buffer, image, color);
            //draw((int) (xPosition + level.main.tileW/4), (int) (yPosition + level.main.tileW/4), level.main.tileW, buffer, image, color & 0xFF8F8F8F);
        }
        
        protected void draw(int x, int y, double scale, WritableImage buffer, Image maskImage, int color)
        {
            PixelReader mask = maskImage.getPixelReader();
            PixelWriter canvas = buffer.getPixelWriter();
            PixelReader source = buffer.getPixelReader();
            double zoom = scale/image.getWidth();
            for(int j = 0; j < image.getHeight(); j++)
                for(int i = 0; i < image.getWidth(); i++)
                {
                    try
                    {
                        canvas.setArgb((int)(i * zoom + x), (int)(j * zoom + y), 
                            (mask.getArgb(i,j) & color)|0xFF000000
                                    | source.getArgb((int)(i * zoom + x), (int)(j * zoom + y)));
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                    }
                }
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
