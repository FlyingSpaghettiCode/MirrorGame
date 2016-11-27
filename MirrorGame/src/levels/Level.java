package levels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import math.Function;
import players.Player;
import players.PlayerTree;
import players.PlayerTreeNode;
import sounds.SoundPlayer;
import game.Game;
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
 * @authors Adriano, Patrick
 * @version 0.2
 * @date 25 July 2016
 */
public class Level {

	public Game main;
	private ArrayList<Sprite> sprites;
	private KeyboardInputHandler keyIn;
	private MouseInputHandler mouseIn;
	private Scene scene;
	private PlayerTree tree;
	private Color background;
	private double SV = 10;
	private boolean reached = false;
	private List<SoundPlayer> sounds;
	private int frame;
	
	private static Level instance;
	
	public Level(Scene scene, Game main) {
		this.main = main;
		this.scene = scene;
		keyIn = main.getKeyIn();
		mouseIn = new MouseInputHandler(scene);
		sprites = new ArrayList<Sprite>();
		tree = new PlayerTree(this);
		background = Color.BLACK;
		
		Level.instance = this;
	}


	//getters and setters
	public int getFrame(){return frame;}
	public ArrayList<Sprite> getSprites() {return sprites;}
	public void setSprites(ArrayList<Sprite> sprites) {this.sprites = sprites;}
	public Scene getScene() {return scene;}
	public void setScene(Scene scene) {this.scene = scene;}
	public MouseInputHandler getMouseIn() {return mouseIn;}
	public void setMouseIn(MouseInputHandler mouseIn) {this.mouseIn = mouseIn;}
	public KeyboardInputHandler getKeyIn() {return keyIn;}
	public void setKeyIn(KeyboardInputHandler keyIn) {this.keyIn = keyIn;}
	public PlayerTree getTree(){return tree;}
	public double getSV(){return SV;}
	public void setSV(double SV){this.SV = SV;}
	public static Level getInstance(){return Level.instance;}
	
	public void addSprite(Sprite sprite){
		if(!sprites.contains(sprite)) sprites.add(sprite);
	}
	public void removeSprite(Sprite sprite){
		sprites.remove(sprite);
	}
	
	public void addPlayer(Player player){
		PlayerTreeNode node = new PlayerTreeNode(player, new Function());
		node.setPlayer(player);
		tree.setRoot(node);
		
		this.addSprite(player);
	}
	
	public void addPlayer(Player player, Player model, Function function){
		PlayerTreeNode modelNode = tree.getNode(model);
		PlayerTreeNode node = new PlayerTreeNode(player, function);
		node.setPlayer(player);
		node.setParent(modelNode);
		modelNode.addChild(node);
		
		this.addSprite(player);
	}
	
	public void handle(int frame){
		this.frame = frame;
		
		PlayerTreeNode root = this.getTree().getRoot();
		if(root != null){
 			Player player = root.getPlayer();
 			double velX = 0;
 			double velY = 0;
 			
 			if(keyIn.isKeyPressed("UP")) velY = -1 * SV;
 			if(keyIn.isKeyPressed("DOWN")) velY = SV;
 			if(keyIn.isKeyPressed("LEFT")) velX = -1 * SV;
 			if(keyIn.isKeyPressed("RIGHT")) velX = SV;
 			
 			if(velX != 0 && velY != 0){
 				double mag = Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
 				velX *= SV / mag;
  				velY *= SV / mag;
  			}
 			
 			root.calcVelocity(velX, velY);
		}
		
		for(Sprite sprite: sprites) sprite.handle();
		if(root != null && !reached){
			root.translateX();
			root.translateY();
		}
		
		this.handleCollisions();
		
		
	}
	
	private void handleCollisions(){
		
		for(int i = 0; i < sprites.size(); i++){
			
			Sprite sprite = sprites.get(i);
			
			if(!(sprite instanceof Collidable))
				continue;
		
			for(int j = i+1; j < sprites.size(); j++){
				
				Sprite otherSprite = sprites.get(j);
				
				if(!(otherSprite instanceof Collidable) || !sameColor(sprite, otherSprite))
					continue;
				
				double[] mtv;
				if(sprite instanceof MoveableSprite)
					mtv = ((Collidable) sprite).getHitbox().getMTV(((Collidable) otherSprite).getHitbox());
				else if(otherSprite instanceof MoveableSprite)
					mtv = ((Collidable) otherSprite).getHitbox().getMTV(((Collidable) sprite).getHitbox());
				else
					continue;
				
				//System.out.println(mtv[0] + " " + mtv[1]);
				
				if(mtv[0] == 0 && mtv[1] == 0)
					continue;
				
				
				// Collision
				/*boolean sM = sprite instanceof MoveableSprite;
				boolean oM = otherSprite instanceof MoveableSprite;
				
				if(!sM && !oM)
					continue;
				
				Sprite rightBottom;
				if(mtv[0] > 0)
					rightBottom = sprite.getxPosition() < otherSprite.getxPosition() ? otherSprite : sprite;
				else
					rightBottom = sprite.getyPosition() < otherSprite.getyPosition() ? otherSprite : sprite;
				
				Sprite target = sM ? sprite : otherSprite;
				double mod = rightBottom.equals(target) ? 1 : -1;
				// Handle collision
				target.setxPosition(target.getxPosition() + mod * mtv[0]);
				target.setyPosition(target.getyPosition() + mod * mtv[1]);*/
				
				((Collidable) sprite).handle((Collidable) otherSprite, mtv);
				((Collidable) otherSprite).handle((Collidable) otherSprite, mtv);
			}
		}
	}
	
	public Sprite getSprite(int id){ // TODO implement
		return sprites.get(id);
	}
	
	private boolean sameColor(Sprite one, Sprite two){
		return (one.isRed() && two.isRed()) || (one.isGreen() && two.isGreen()) || (one.isBlue() && two.isBlue());
	}
	
	public void draw(GraphicsContext gc){
		gc.setFill(background);
		gc.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		for(Sprite sprite: sprites) sprite.draw(gc);
	}


	public List<SoundPlayer> getSounds() {
		return sounds;
	}


	public void setSounds(List<SoundPlayer> sounds) {
		this.sounds = sounds;
	}
}
