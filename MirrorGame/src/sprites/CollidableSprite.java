package sprites;

/**
 * A sprite that can collide is part of this group.
 * @author Adriano
 * @date 24 July 2016
 * @version 0.9
 */
public interface CollidableSprite{
	void isTouching(CollidableSprite otherSprite);
}
