package sprites;

public abstract class MoveableSprite extends Sprite {

	public MoveableSprite(double xPosition, double yPosition, double width, double height, String PATH) {
		super(xPosition, yPosition, width, height, PATH);
	}

	public MoveableSprite(double xPosition, double yPosition, double width, double height) {
		super(xPosition, yPosition, width, height);
	}

	double velX;
	double velY;

	// movement and stuff
	public void translateX(double x) {
		this.setxPosition(Math.max(Math.min(this.getxPosition() + x, level.main.WIDTH - this.getWidth()), 0));
	}

	public void translateY(double y) {
		this.setyPosition(Math.max(Math.min(this.getyPosition() + y, level.main.HEIGHT - this.getHeight()), 0));
	}

	// velocity
	public void setVelocityX(double x) {
		velX = x;
	}

	public void setVelocityY(double y) {
		velY = y;
	}

	public double getVelocityX() {
		return velX;
	}

	public double getVelocityY() {
		return velY;
	}

	/*
	 * @Override public void handle(){ this.translateX(this.getVelocityX());
	 * this.translateY(this.getVelocityY()); }
	 */
}
