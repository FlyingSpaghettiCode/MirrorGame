package sprites;

import javafx.scene.Scene;

public class MoveableSprite extends Sprite {

	//constructors with full functionality
	public MoveableSprite(Scene scene) {
		super(scene);
	}
	public MoveableSprite(Scene scene, double x, double y) {
		super(scene, x, y);
	}
	public MoveableSprite(Scene scene, double xPosition, double yPosition, String PATH) {
		super(scene, xPosition, yPosition, PATH);
	}
	public MoveableSprite(Scene scene, String PATH) {
		super(scene, PATH);
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
