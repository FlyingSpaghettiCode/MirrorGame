package sprites;

import levels.Level;

public class MoveableSprite extends Sprite {

	//constructors with full functionality
	public MoveableSprite(Level level) {
		super(level);
	}
	public MoveableSprite(Level level, double x, double y) {
		super(level, x, y);
	}
	public MoveableSprite(Level level, double xPosition, double yPosition, String PATH) {
		super(level, xPosition, yPosition, PATH);
	}
	public MoveableSprite(Level level, String PATH) {
		super(level, PATH);
	}
	//constructors without full functionality
	public MoveableSprite() {
		super();
	}
	//movement and stuff
	public void translateX(double x){
		this.setxPosition(this.getxPosition()+x);
	}
	public void translateY(double y){
		this.setyPosition(this.getyPosition()+y);
	}
}
