package math;

import levels.Level;
import game.Main;
import sprites.Sprite;

public class Camera {
	
	private double scale = 1.0;
	private double scaleTime = 2; // Time to scale, in frames
	private double xBuffer = 100; // Horizontal screen buffer, in pixels
	private double yBuffer = 100; // Vertical screen buffer, in pixels
	
	private double camX;
	private double camY;
	private double cameraTime = 2;
	
	private Level level;
	private Main main;
	
	
	public Camera(Level level, Main main){
		camX = main.WIDTH / 2.0;
		camY = main.HEIGHT / 2.0;
		this.level = level;
		this.main = main;
	}
	
	public void update(){
		this.tryRequiredScaleFactor();
		//this.tryRequiredCameraPosition();
	}

	
	private void tryRequiredScaleFactor(){
	
		double l = Double.MAX_VALUE;
		double r = Double.MIN_VALUE;
		double t = Double.MAX_VALUE;
		double b = Double.MIN_VALUE;
		
		l = 0; // Used only when camera is stationary
		t = 0;
		
		if(level.getSprites().size() <= 0){
			if(scale != 1)
				scale(1);
			return;
		}
		
		for(Sprite sprite : level.getSprites()){
			l = Math.min(sprite.getxPosition() * scale, l);
			r = Math.max(sprite.getxPosition() * scale + sprite.getWidth() * scale, r);
			t = Math.min(sprite.getyPosition() * scale, t);
			b = Math.max(sprite.getyPosition() * scale + sprite.getHeight() * scale, b);
		}
		
		double reqWidth = r - l + xBuffer * scale;
		double reqHeight = b - t + yBuffer * scale;
		double reqWScale = reqWidth / main.WIDTH;
		double reqHScale = reqHeight / main.HEIGHT;
		
		double reqScale =  Math.max(reqWScale, reqHScale);
		
		if(reqScale == scale)
			return;

		if(reqScale < 1){
			if(scale > 1)
				scale(1);
		}
		else
			scale(scale + (reqScale - scale) / scaleTime);
	}
	
	private void tryRequiredCameraPosition(){
		double l = Double.MAX_VALUE;
		double r = Double.MIN_VALUE;
		double t = Double.MAX_VALUE;
		double b = Double.MIN_VALUE;
		
		if(level.getSprites().size() <= 0){
			if(camX != main.WIDTH / 2.0 || camY != main.HEIGHT / 2.0)
				this.setCamera(main.WIDTH / 2.0, main.HEIGHT / 2.0);
			return;
		}
		
		for(Sprite sprite : level.getSprites()){
			l = Math.min(sprite.getxPosition() * scale, l);
			r = Math.max(sprite.getxPosition() * scale + sprite.getWidth() * scale, r);
			t = Math.min(sprite.getyPosition() * scale, t);
			b = Math.max(sprite.getyPosition() * scale + sprite.getHeight() * scale, b);
		}
		
		double reqX = (l + r) / 2.0;
		double reqY = (t + b) / 2.0;
		
		if(camX != reqX || camY != reqY)
			setCamera(camX + (reqX - camX) / cameraTime, camY + (reqY - camY) / cameraTime);
	}
	
	public void scale(double factor){
		for(Sprite sprite : level.getSprites()){
			sprite.setxPosition(sprite.getxPosition() * scale / factor);
			sprite.setyPosition(sprite.getyPosition() * scale / factor);
			sprite.setWidth(sprite.getWidth() * scale / factor);
			sprite.setHeight(sprite.getHeight() * scale / factor);
		}
		
		level.setSV(level.getSV() * scale / factor);
		
		this.scale = factor;
	}
	
	public void setCamera(double x, double y){
		double dx = x - camX;
		double dy = y - camY;
		
		for(Sprite sprite : level.getSprites()){
			sprite.setxPosition(sprite.getxPosition() - dx);
			sprite.setyPosition(sprite.getyPosition() - dy);
		}
		
		camX = x;
		camY = y;
	}
	
}
