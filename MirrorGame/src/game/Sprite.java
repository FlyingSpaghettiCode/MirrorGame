package game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

//a sprite class without animations
public class Sprite {

	private Image image;
	private String name;
	private double xPosition;
	private double yPosition;
	
	public Sprite() {
		
	}
	
	//all getters and setters
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	//draw
	public void draw(GraphicsContext gc){
		gc.drawImage(image,xPosition,yPosition);
	}
}
