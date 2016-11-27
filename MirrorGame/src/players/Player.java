package players;

import java.awt.Rectangle;

import images.ColorUtil;
import images.ImageLoader;
import levels.Level;
import math.Hitbox;
import sprites.Collidable;
import sprites.MoveableSprite;

//for now just a moveable sprite that knows it's a collidable
public class Player extends MoveableSprite implements Collidable {

	
	public Player(double xPosition, double yPosition, double width, double height, String PATH) {
		super(xPosition, yPosition, width, height, PATH);
	}
	
	public Player(double xPosition, double yPosition, double width, double height) {
		super(xPosition, yPosition, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		
	}

	@Override
	public void update(){
	}
}
