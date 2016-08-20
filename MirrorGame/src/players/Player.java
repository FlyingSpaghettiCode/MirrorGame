package players;

import java.awt.Rectangle;

import levels.Level;
import math.Hitbox;
import sprites.Collidable;
import sprites.MoveableSprite;

//for now just a moveable sprite that knows it's a collidable
public class Player extends MoveableSprite implements Collidable {

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

	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		
	}

}
