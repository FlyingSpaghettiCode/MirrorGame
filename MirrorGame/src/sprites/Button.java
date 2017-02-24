package sprites;

import java.lang.reflect.Field;

import images.ImageLoader;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import math.Grid;

public class Button extends Sprite implements Collidable {
	
	boolean on = false;
	boolean oldOn = false;
	Sprite target;
	Field field;
	Object onVal;
	Object offVal;
	
	public Button(double xPosition, double yPosition, double width, double height, boolean red, boolean green, boolean blue, Sprite target, String clazz, String field, Object onVal, Object offVal) {
		super(xPosition, yPosition, width, height);
		
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.onVal = onVal;
		this.offVal = offVal;
		
		this.target = target;
		
		try {
			this.field = Class.forName(clazz).getDeclaredField(field);
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
		this.setImage(ImageLoader.getImage("goal"));
	}

	@Override
	public void update() {
	}
	
	@Override
	public void handle(){
		oldOn = on;
		on = false;
	}
	
	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		on = true;
	}
	
	@Override
	public void draw(WritableImage buffer) {
		
		if(on != oldOn){
			try {
				field.set(target, on ? onVal : offVal);
				target.update();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		int diff = on ? 0x77 : 0;
		
		int color = 0xFF000000 + (red ? 0x00FF0000 - (diff << 16) : 0) + 
				(green ? 0x0000FF00 - (diff << 8) : 0) + 
				(blue ? 0x000000FF - diff : 0);
		
		this.draw(color, buffer);
	}
	
}
