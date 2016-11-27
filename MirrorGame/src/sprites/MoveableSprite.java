package sprites;

import java.util.Vector;

import levels.Level;

public class MoveableSprite extends Sprite {
	
	double velX;
	double velY;

	//constructors with full functionality
	public MoveableSprite(Level level) {
		super(level);
	}
	public MoveableSprite(Level level, double x, double y) {
		super(level, x, y);
	}
	public MoveableSprite(Level level, double xPosition, double yPosition, double width, double height, String PATH) {
		super(level, xPosition, yPosition, width, height, PATH);
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
	
	//velocity
	public void setVelocityX(double x){
		velX = x;
	}
	
	public void setVelocityY(double y){
		velY = y;
	}
	
	public double getVelocityX(){
		return velX;
	}
	
	public double getVelocityY(){
		return velY;
	}
	
	/*@Override
	public void handle(){
		this.translateX(this.getVelocityX());
		this.translateY(this.getVelocityY());
	}*/
}
