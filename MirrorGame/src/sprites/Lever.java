package sprites;

import java.lang.reflect.Field;

import images.ResizableImage;
import levels.Level;

public class Lever extends Sprite implements Collidable {

	boolean on = false;
	Sprite target;
	Field field;
	double TOGGLE_INTERVAL = 60;
	double lastToggle = -10000;
	Object onVal;
	Object offVal;
	
	public Lever(double x, double y, boolean red, boolean green, boolean blue, Level level, int target, String clazz, String field, Object onVal, Object offVal){
		super(level);
		this.setImage(new ResizableImage("/images/leftLever.png"));
		
		this.setxPosition(x);
		this.setyPosition(y);
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.onVal = onVal;
		this.offVal = offVal;
		
		this.target = level.getSprite(target);
		
		try {
			this.field = Class.forName(clazz).getDeclaredField(field);
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		
		if(this.getLevel().getFrame() - lastToggle >= TOGGLE_INTERVAL){
			lastToggle = this.getLevel().getFrame();
			on = !on;
			double h = this.getHeight();
			double w = this.getWidth();
			this.setImage(new ResizableImage("/images/" + (on ? "right" : "left") + "Lever.png"));
			this.setHeight(h);
			this.setWidth(w);
			
			try {
				field.set(target, on ? onVal : offVal);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
