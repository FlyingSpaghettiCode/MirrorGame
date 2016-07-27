package players;

import levels.Level;
import sprites.Collidable;
import sprites.MoveableSprite;

/**
 * A player that is part of a tree that moves the same in the x and y
 * @author Adriano
 *
 */
public class Player extends MoveableSprite implements Collidable{

	private Player parent;
	private Player[] children;
	
	
	
	public Player(Level level) {
		super(level);
		// TODO Auto-generated constructor stub
	}

	public Player(Level level, double x, double y) {
		super(level, x, y);
		// TODO Auto-generated constructor stub
	}

	public Player(Level level, double xPosition, double yPosition, String PATH) {
		super(level, xPosition, yPosition, PATH);
		// TODO Auto-generated constructor stub
	}

	public Player(Level level, String PATH) {
		super(level, PATH);
		// TODO Auto-generated constructor stub
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void isTouching(Collidable otherSprite) {
		// TODO Auto-generated method stub
		
	}
	
	//we override the movement methods because they depend on the relationships of the player
	@Override
	public void translateX(double x){
		if(isRoot()) super.translateX(x);
	}
	@Override
	public void translateY(double y){
		if(isRoot()) super.translateY(y);
	}
	
	//a player function that doubles
	@SuppressWarnings("unused")
	private double playerFunction(double z){
		return 2*z;
	}

	public boolean isRoot() {return parent == null;}
	
	//getters and setters
	public Player getParent() {return parent;}
	public void setParent(Player parent) {this.parent = parent;}
	public Player[] getChildren() {	return children;}
	public void setChildren(Player[] children) {this.children = children;}
}
