package sprites;

import math.Hitbox;

/**
 * A sprite that can collide is part of this group.
 * @author Patrick
 * @date 31 July 2016
 * @version 1
 */
public interface Collidable{
	default Hitbox getHitbox(){
		return new Hitbox(((Sprite)this).getxPosition(), ((Sprite)this).getyPosition(), ((Sprite)this).getImage().getActualWidth(), ((Sprite)this).getImage().getActualHeight());
	}
	
	void handle(Collidable otherSprite, double mtv[]);
}
