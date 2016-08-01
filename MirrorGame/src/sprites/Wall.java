package sprites;

import math.Hitbox;

public class Wall extends Sprite implements Collidable{

	public Wall() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Hitbox getHitbox() {
		return new Hitbox(this.getxPosition(), this.getyPosition(), this.getImage().getWidth(), this.getImage().getHeight());
	}
	
	@Override
	public Hitbox getHitbox(double x, double y) {
		return new Hitbox(x, y, this.getImage().getWidth(), this.getImage().getHeight());
	}

	@Override
	public void handle(Collidable otherSprite) {
	}

}
