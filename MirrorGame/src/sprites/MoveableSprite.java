package sprites;

public class MoveableSprite extends Sprite {

	//constructors with full functionality
	public MoveableSprite() {
		super();
	}
	public MoveableSprite(double x, double y) {
		super( x, y);
	}
	public MoveableSprite(double xPosition, double yPosition, String PATH) {
		super( xPosition, yPosition, PATH);
	}
	public MoveableSprite(String PATH) {
		super( PATH);
	}
	//movement and stuff
	public void translateX(double x){
		this.setxPosition(this.getxPosition()+x);
	}
	public void translateY(double y){
		this.setyPosition(this.getyPosition()+y);
	}
}
