/**
 * 
 */
package sprites;

import input.KeyboardInputHandler;
import input.MouseInputHandler;
import javafx.scene.Scene;

/**
 * This is a sprite that can be controlled by the user.
 * @author Adriano
 * @version 0.1
 * @date 24 July 2016
 */
public class ControllableSprite extends MoveableSprite{

	private KeyboardInputHandler keyIn;
	private MouseInputHandler mouseIn;
	
	//constructors with full functionality
	private void init(){
		keyIn = new KeyboardInputHandler(this.getScene());
		setMouseIn(new MouseInputHandler(this.getScene()));
	}
	public ControllableSprite(Scene scene) {
		super(scene);
		init();
	}
	public ControllableSprite(Scene scene, double x, double y) {
		super(scene, x, y);
		init();
	}
	public ControllableSprite(Scene scene, double xPosition, double yPosition, String PATH) {
		super(scene, xPosition, yPosition, PATH);
		init();
	}
	public ControllableSprite(Scene scene, String PATH) {
		super(scene, PATH);
		init();
	}
	//by default will move according to the arrow keys and ignore mouse controls
	//this is to be overriden in children that act differently
	public void input(){
		if(keyIn.isKeyPressed("UP")) translateY(-10);
		if(keyIn.isKeyPressed("DOWN")) translateY(10);
		if(keyIn.isKeyPressed("LEFT")) translateX(-10);
		if(keyIn.isKeyPressed("RIGHT")) this.translateX(10);
	}
	
	//for children
	protected MouseInputHandler getMouseIn() {return mouseIn;}
	protected void setMouseIn(MouseInputHandler mouseIn) {this.mouseIn = mouseIn;}
	protected KeyboardInputHandler getKeyIn() {return keyIn;}
	protected void setKeyIn(KeyboardInputHandler keyIn) {this.keyIn = keyIn;}
	
}
