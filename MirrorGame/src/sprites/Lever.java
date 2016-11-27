package sprites;

import java.lang.reflect.Field;

import javafx.scene.image.Image;
import levels.Level;

public class Lever extends Sprite implements Collidable {

	boolean on = false;
	Sprite target;
	Field field;
	double TOGGLE_INTERVAL = 20;
	double lastToggle = -10000;
	Object onVal;
	Object offVal;
	
	Image other;
	
	public Lever(double x, double y, boolean red, boolean green, boolean blue, Level level, int target, String clazz, String field, Object onVal, Object offVal){
		super(level);
		
		this.setxPosition(x);
		this.setyPosition(y);
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.setImage(this.getColoredImage(new Image("/images/leftLever.png")));
		other = this.getColoredImage(new Image("/images/rightLever.png"));
		
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
		
		MoveableSprite s = (MoveableSprite) otherSprite;
		
		s.setxPosition(s.getxPosition() + mtv[0]);
		s.setyPosition(s.getyPosition() + mtv[1]);
		
		if(this.getLevel().getFrame() - lastToggle >= TOGGLE_INTERVAL){
			lastToggle = this.getLevel().getFrame();
			on = !on;
			double h = this.getHeight();
			double w = this.getWidth();
			Image temp = this.getImage();
			this.setImage(other);
			other = temp;
			
			try {
				field.set(target, on ? onVal : offVal);
				target.update();
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
