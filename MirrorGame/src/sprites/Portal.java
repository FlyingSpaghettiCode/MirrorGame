package sprites;

import javafx.scene.image.Image;
import levels.Level;


public class Portal extends Sprite implements Collidable {

	Portal other;
	
	public Portal(Level level, double x, double y, boolean red, boolean green, boolean blue){
		super(level, x, y);
		
		this.other = other;
		this.setxPosition(x);
		this.setyPosition(y);
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.setImage(this.getColoredImage(new Image("/images/portal.png")));
	}
	
	public void setExit(Portal exit){
		other = exit;
		exit.other = this;
	}
	
	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		Sprite s = (Sprite) otherSprite;
		
		s.setxPosition(s.getxPosition() + mtv[0]);
		s.setyPosition(s.getyPosition() + mtv[1]);
		
		double dx = s.getxPosition() + s.getWidth()/2.0 - this.getxPosition() - this.getWidth()/2.0;
		double dy = s.getyPosition() + s.getHeight()/2.0 - this.getyPosition() - this.getHeight()/2.0;
		
		if(mtv[0] != 0){
			//s.setxPosition(other.getxPosition() + other.getWidth()/2.0 - dx);
			s.setxPosition(other.getxPosition() + Math.signum(dx) * -1 * s.getWidth());
			s.setyPosition(other.getyPosition() + other.getHeight()/2.0 + dy - s.getHeight()/2.0);
		}
		else if(mtv[1] != 0){
			s.setxPosition(other.getxPosition() + other.getWidth()/2.0 + dx - s.getWidth()/2.0);
			s.setyPosition(other.getyPosition() + Math.signum(dy) * -1 * s.getHeight());
		}
	}

}
