package players;

import sprites.Collidable;
import sprites.MoveableSprite;

//for now just a controllable sprite that knows it's a collidable
public class Player extends MoveableSprite implements Collidable{

	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Player(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Player(double xPosition, double yPosition, String PATH) {
		super(xPosition, yPosition, PATH);
		// TODO Auto-generated constructor stub
	}

	public Player(String PATH) {
		super(PATH);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void isTouching(Collidable otherSprite) {
		// TODO Auto-generated method stub
		
	}

}
