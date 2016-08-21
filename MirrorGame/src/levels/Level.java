package levels;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import sprites.Sprite;

/**
 * This is a level class
 * @author Adriano
 * @version 0.2
 * @date 25 July 2016
 */
public abstract class Level {

	protected List<Sprite> sprites;


	//getters and setters
//	public List<Sprite> getSprites() {return sprites;} //bad encapsulation; probably unnecessary
	
	public void addSprite(Sprite sprite){
		if(!sprites.contains(sprite)) sprites.add(sprite);
	}
	public void removeSprite(Sprite sprite){
		sprites.remove(sprite);
	}
	public void handle(){
		for(Sprite sprite: sprites) sprite.handle();
	}
	public void draw(GraphicsContext gc){
		for(Sprite sprite: sprites) sprite.draw(gc);
	}
}
