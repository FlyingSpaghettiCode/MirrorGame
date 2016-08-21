package players;

import input.KeyboardInputHandler;

//a player tree
/**
 * This is player tree class that we find in standard levels
 * @author Adriano
 *
 */
public class KeyboardPlayerTree extends PlayerTreeNode {

	private KeyboardInputHandler keyIn;
	public final double STEP = 1;
	
	public KeyboardPlayerTree(KeyboardInputHandler keyIn) {
		this.keyIn = keyIn;
	}


	//some basic functionality
	public void input(){
		if(keyIn.isKeyPressed("UP")) this.translateY(-STEP);
		if(keyIn.isKeyPressed("DOWN")) this.translateY(STEP);
		if(keyIn.isKeyPressed("LEFT")) this.translateX(-STEP);
		if(keyIn.isKeyPressed("RIGHT")) this.translateX(STEP);
	}
}
