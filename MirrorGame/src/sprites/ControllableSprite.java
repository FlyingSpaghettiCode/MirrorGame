/**
 * 
 */
package sprites;

import input.KeyboardInputHandler;
import levels.Level;

/**
 * This is a sprite that can be controlled by the user.
 * @author Adriano
 * @version 0.1
 * @date 24 July 2016
 */
public class ControllableSprite extends MoveableSprite{

	//constructors with full functionality
	private void init(){
		//this is already set
		//Level level = this.getLevel();
		//level.setKeyIn(new KeyboardInputHandler(this.getLevel().getScene()));
		//level.setMouseIn(new MouseInputHandler(this.getLevel().getScene()));
	}
	public ControllableSprite(Level level) {
		super(level);
		init();
	}
	public ControllableSprite(Level level, double x, double y) {
		super(level, x, y);
		init();
	}
	public ControllableSprite(Level level, double xPosition, double yPosition, String PATH) {
		super(level, xPosition, yPosition, PATH);
		init();
	}
	public ControllableSprite(Level level, String PATH) {
		super(level, PATH);
		init();
	}
	//by default will move according to the arrow keys and ignore mouse controls
	//this is to be overriden in children that act differently
	public void input(){
		KeyboardInputHandler keyIn = getLevel().getKeyIn();
		if(keyIn.isKeyPressed("UP")) translateY(-10);
		if(keyIn.isKeyPressed("DOWN")) translateY(10);
		if(keyIn.isKeyPressed("LEFT")) translateX(-10);
		if(keyIn.isKeyPressed("RIGHT")) this.translateX(10);
	}
	@Override
	public void handle(){
		input();
	}
}
