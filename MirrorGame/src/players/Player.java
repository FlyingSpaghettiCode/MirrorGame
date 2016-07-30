package players;

import levels.Level;
import sprites.Collidable;
import sprites.ControllableSprite;

//for now just a controllable sprite that knows it's a collidable
public class Player extends ControllableSprite implements Collidable{

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
	public void isTouching(Collidable otherSprite) {
		// TODO Auto-generated method stub
		
	}

}
