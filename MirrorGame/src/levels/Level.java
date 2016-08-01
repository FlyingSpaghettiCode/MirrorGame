package levels;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import math.Function;
import math.Hitbox;
import players.Player;
import players.PlayerTree;
import players.PlayerTreeNode;
import game.Main;
import input.KeyboardInputHandler;
import input.MouseInputHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sprites.Collidable;
import sprites.MoveableSprite;
import sprites.Sprite;

/**
 * This is a level class
 * @authorsssssssssss Adriano, Patrick
 * @version 0.2
 * @date 25 July 2016
 */
public class Level {

	private Main main;
	private ArrayList<Sprite> sprites;
	private KeyboardInputHandler keyIn;
	private MouseInputHandler mouseIn;
	private Scene scene;
	private PlayerTree tree;
	private Color background;
	
	public Level(Scene scene, Main main) {
		this.main = main;
		this.scene = scene;
		keyIn = new KeyboardInputHandler(scene);
		mouseIn = new MouseInputHandler(scene);
		sprites = new ArrayList<Sprite>();
		tree = new PlayerTree(this);
		background = Color.BLACK;
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
	public PlayerTree getTree(){return tree;}
	
	public void addSprite(Sprite sprite){
		if(!sprites.contains(sprite)) sprites.add(sprite);
	}
	public void removeSprite(Sprite sprite){
		sprites.remove(sprite);
	}
	
	public void addPlayer(Player player){
		PlayerTreeNode node = new PlayerTreeNode(player, Arrays.asList(), Arrays.asList());
		node.setPlayer(player);
		tree.setRoot(node);
		
		this.addSprite(player);
	}
	
	public void addPlayer(Player player, Player model, List<Function> functionsX, List<Function> functionsY){
		PlayerTreeNode modelNode = tree.getNode(model);
		PlayerTreeNode node = new PlayerTreeNode(player, functionsX, functionsY);
		node.setPlayer(player);
		node.setParent(modelNode);
		modelNode.addChild(node);
		
		this.addSprite(player);
	}
	
	public void handle(){
		PlayerTreeNode root = this.getTree().getRoot();
		if(root != null){
			Player player = root.getPlayer();
			KeyboardInputHandler keyIn = this.getKeyIn();
			double velX = 0;
			double velY = 0;
			
			if(keyIn.isKeyPressed("UP")) velY = -10;
			if(keyIn.isKeyPressed("DOWN")) velY = 10;
			if(keyIn.isKeyPressed("LEFT")) velX = -10;
			if(keyIn.isKeyPressed("RIGHT")) velX = 10;
			if(keyIn.isKeyPressed("ESCAPE")){
				System.err.println("Game terminated.");
				System.exit(0);
			}
			
			root.calcVelocityX(velX);
			root.calcVelocityY(velY);
		}
		
		for(int i = 0; i < sprites.size(); i++){
			Sprite sprite = sprites.get(i);
			if(sprite instanceof Collidable){
				for(int j = i+1; j < sprites.size(); j++){
					Sprite otherSprite = sprites.get(j);
					if(otherSprite instanceof Collidable && sameColor(sprite, otherSprite)){
						Hitbox sH = ((Collidable) sprite).getHitbox();
						boolean sM = sprite instanceof MoveableSprite;
						if(sM)
							sH = ((Collidable) sprite).getHitbox(sprite.getxPosition()+((MoveableSprite) sprite).getVelocityX(), sprite.getyPosition()+((MoveableSprite) sprite).getVelocityY());
						Hitbox oH = ((Collidable) otherSprite).getHitbox();
						boolean oM = otherSprite instanceof MoveableSprite;
						if(oM)
							oH = ((Collidable) otherSprite).getHitbox(otherSprite.getxPosition()+((MoveableSprite) otherSprite).getVelocityX(), otherSprite.getyPosition()+((MoveableSprite) otherSprite).getVelocityY());
						if(sH.isColliding(oH)){
							if(sM){
								((MoveableSprite) sprite).setVelocityX(0);
								((MoveableSprite) sprite).setVelocityY(0);
							}
							if(oM){
								((MoveableSprite) otherSprite).setVelocityX(0);
								((MoveableSprite) otherSprite).setVelocityY(0);
							}
						}
					}
				}
			}
		}
		
		for(Sprite sprite: sprites) sprite.handle();
		if(root != null){
			root.translateX();
			root.translateY();
		}
		
	}
	
	private boolean sameColor(Sprite one, Sprite two){
		return (one.isRed() && two.isRed()) || (one.isGreen() && two.isGreen()) || (one.isBlue() && two.isBlue());
	}
	
	public void draw(GraphicsContext gc){
		gc.setFill(background);
		gc.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		for(Sprite sprite: sprites) sprite.draw(gc);
	}
}
