package levels;

import java.util.ArrayList;

import input.KeyboardInputHandler;
import input.MouseInputHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import sprites.Sprite;

/**
 * This is a level class
 * @author Adriano
 * @version 0.2
 * @date 25 July 2016
 */
public class Level {

	private ArrayList<Sprite> sprites;
	private KeyboardInputHandler keyIn;
	private MouseInputHandler mouseIn;
	private Scene scene;
	
	public Level(Scene scene) {
		this.scene = scene;
		keyIn = new KeyboardInputHandler(scene);
		mouseIn = new MouseInputHandler(scene);
		sprites = new ArrayList<Sprite>();
	}


	//getters and setters
	public ArrayList<Sprite> getSprites() {return sprites;}
	public void setSprites(ArrayList<Sprite> sprites) {this.sprites = sprites;}
	public Scene getScene() {return scene;}
	public void setScene(Scene scene) {this.scene = scene;}
	public MouseInputHandler getMouseIn() {return mouseIn;}
	public void setMouseIn(MouseInputHandler mouseIn) {this.mouseIn = mouseIn;}
	public KeyboardInputHandler getKeyIn() {return keyIn;}
	public void setKeyIn(KeyboardInputHandler keyIn) {this.keyIn = keyIn;}
	
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
