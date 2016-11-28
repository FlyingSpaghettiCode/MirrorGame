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
 * A bare bones sprite class; it can display at given coordinates and display an
 * image.
 * 
 * @author Adriano Hernandez
 * @version 1.0
 * @date 24 July 2016
 */
public abstract class Sprite extends Object {
	// instance variables
	private Image image;
	private double xPosition;
	private double yPosition;
	protected Level level;
	private String name;
	private double width;
	private double height;
	public boolean red = false;
	public boolean green = false;
	public boolean blue = false;

	public Sprite(double xPosition, double yPosition, double width, double height) {
		// some basic init
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;

		this.level = Level.getInstance();
		setName();
	}

	public Sprite(double xPosition, double yPosition, double width, double height, String PATH) {
		this(xPosition, yPosition, width, height);

		image = new Image(PATH);
	}

	// all getters and setters for instance variables
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Getters and setters for height and width
	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	// set a pretty random name of length 10
	public void setName() {
		String charList = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890~!@#$%^&*()-=_+[]{}/|;':,.<>?\\";
		String n = "";
		for (int i = 0; i < 10; i++) {
			int m = (int) (Math.random() * charList.length());
			n += charList.substring(m, m + 1);
		}
		setName(n);
	}

	// handle mechanisms that must be dealt with by the sprite
	public void handle() {
	}

	// Draws at its coordinates. Can handle overlapping.
	public void draw(WritableImage buffer) {
		PixelReader mask = image.getPixelReader();
		PixelWriter canvas = buffer.getPixelWriter();
		PixelReader source = buffer.getPixelReader();
		int color = 0xFF000000 + (red ? 0x00FF0000 : 0) + (green ? 0x0000FF00 : 0) + (blue ? 0x000000FF : 0);
		double zoomX = width / image.getWidth(); // Increment in x position
		double zoomY = height / image.getHeight(); // Increment in y position

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				canvas.setArgb((int) xPosition + i, (int) yPosition + j,
						(mask.getArgb((int) (i / zoomX), (int) (j / zoomY)) & color) | 0xFF000000
								| source.getArgb((int) (i + xPosition), (int) (j + yPosition)));

	}

	public abstract void update();

	public boolean isRed() {
		return red;
	}

	public boolean isGreen() {
		return green;
	}

	public boolean isBlue() {
		return blue;
	}
}
