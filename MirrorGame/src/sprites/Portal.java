package sprites;

import images.ResizableImage;

public class Portal extends Sprite implements Collidable {

	boolean sender;
	Portal other;
	
	public Portal(double x, double y, boolean red, boolean green, boolean blue, Portal other, boolean sender){
		this.other = other;
		this.sender = sender;
		this.setxPosition(x);
		this.setyPosition(y);
		this.red = red;
		this.green = green;
		this.blue = blue;
		
		this.setImage(new ResizableImage("/images/portal.png"));
	}
	
	@Override
	public void handle(Collidable otherSprite, double[] mtv) {
		
		if(sender){
			((Sprite)otherSprite).setxPosition(other.getxPosition());
			((Sprite)otherSprite).setyPosition(other.getyPosition());
		}
		
	}

}
