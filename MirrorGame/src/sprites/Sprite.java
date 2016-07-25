package sprites;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A bear bones sprite class; it can display at given coordinates and display an image.
 * @author Adriano Hernandez
 * @version 1.0
 * @date 24 July 2016
 */
public class Sprite {
	//instance variables
	private Image image;
	private double xPosition;
	private double yPosition;
	private Scene scene;
	
	//constructors with full functionality
	public Sprite(Scene scene) {
		this(scene,0,0);
	}
	public Sprite(Scene scene, double x, double y){
		this(scene, x, y, "/images/testImage.png");
	}
	public Sprite(Scene scene, double xPosition, double yPosition, String PATH){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		image = new Image(PATH);
		this.scene = scene;
	}
	public Sprite(Scene scene, String PATH){
		this(scene,0,0,PATH);
	}
	//constructors with limited functionality: NOT RECOMMENDED
	public Sprite(){
		this(null); 
		//if you do not wanna take input or interact with the scene this works
		//however, if you do wanna have a working sprite give the scene
		//the scene is needed for input to work and other such events
	}
	
	//all getters and setters for instance variables
	public Image getImage() {return image;}
	public void setImage(Image image) {this.image = image;}
	public double getxPosition() {return xPosition;}
	public void setxPosition(double xPosition) {this.xPosition = xPosition;}
	public double getyPosition() {return yPosition;}
	public void setyPosition(double yPosition) {this.yPosition = yPosition;}
	public Scene getScene() {return scene;}
	public void setScene(Scene scene) {this.scene = scene;}
	
	//draw at it's coordinates
	public void draw(GraphicsContext gc){
		gc.drawImage(image,xPosition,yPosition);
	}
}
