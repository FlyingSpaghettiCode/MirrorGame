package sprites;

import java.util.Vector;

import levels.Level;

public class MoveableSprite extends Sprite {
	
	double velX;
	double velY;

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
