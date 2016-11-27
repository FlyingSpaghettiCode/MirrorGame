package sprites;

public class Wall extends Sprite implements Collidable {

	public Wall(double x, double y, double width, double height, boolean red, boolean green, boolean blue, String color) {
		super(x, y, width, height, "images/" + color + ".png");
		
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	@Override
	public void handle(Collidable otherSprite, double[] mtv) {

		if (!(otherSprite instanceof MoveableSprite))
			return;

		MoveableSprite target = (MoveableSprite) otherSprite;

		target.setxPosition(target.getxPosition() + mtv[0]);
		target.setyPosition(target.getyPosition() + mtv[1]);

	}
	
	@Override
	public void update(){
	}

}
