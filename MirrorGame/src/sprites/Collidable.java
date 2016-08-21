package sprites;

import java.awt.Rectangle;

import math.Hitbox;

import com.sun.prism.paint.Color;

/**
 * A sprite that can collide is part of this group.
 * @author Patrick
 * @date 31 July 2016
 * @version 1
 */
public interface Collidable{
	Hitbox getHitbox();
	Hitbox getHitbox(double x, double y);
	void handle(Collidable otherSprite);
}
