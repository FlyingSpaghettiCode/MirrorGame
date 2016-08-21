package sprites;

import images.ResizableImage;
import levels.Level;

public class Lever extends Sprite implements Collidable {

	boolean on = false;
	double TOGGLE_INTERVAL = 60;
	double lastToggle = -10000;
	
	public Lever(double x, double y, boolean red, boolean green, boolean blue, Level level){
		super(level);
		this.setImage(new ResizableImage("/images/leftLever.png"));
		
		this.setxPosition(x);
		this.setyPosition(y);
		this.red = red;
		this.green = green;
		this.blue = blue;
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
		}
		
	}
	

}
