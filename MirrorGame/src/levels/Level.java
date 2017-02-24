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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
	private double SV = 0.25;
	private boolean reached = false;
	private List<SoundPlayer> sounds;
	private int frame;
	
	private long time = 0;
		
	public Level(Scene scene, Game main) {
		this.main = main;
		this.scene = scene;
		keyIn = main.getKeyIn();
		mouseIn = new MouseInputHandler(scene);
		sprites = new ArrayList<Sprite>();
		tree = new PlayerTree(this);
		background = Color.BLACK;
		
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
	
	public void addSprite(Sprite sprite){
		if(!sprites.contains(sprite)) sprites.add(sprite);
                sprite.setLevel(this);
	}
	public void removeSprite(Sprite sprite){
		sprites.remove(sprite);
                sprite.setLevel(null);
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
			
			if(!(sprite instanceof Collidable && sprite instanceof MoveableSprite))
				continue;
			
			for(int j = 0; j < sprites.size(); j++){
			
				Sprite otherSprite = sprites.get(j);
				
				if(otherSprite instanceof MoveableSprite)
					continue;
				
				if(!(otherSprite instanceof Collidable) || !sameColor(sprite, otherSprite))
					continue;
				
				double[] mtv = ((Collidable) sprite).getHitbox().getMTV(((Collidable) otherSprite).getHitbox());
				
				if(mtv[0] == 0 && mtv[1] == 0)
					continue;
				
				((Collidable) otherSprite).handle((Collidable) sprite, mtv);
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
                WritableImage buffer = new WritableImage((int)gc.getCanvas().getWidth(),(int)gc.getCanvas().getHeight());
		for(Sprite sprite: sprites) sprite.draw(buffer);
                gc.drawImage(buffer, 0, 0);
	}
	
	public static double[] getFontMetrics(Font font, String text){
		
		double[] metrics = new double[2];
		GraphicsContext gc = new Canvas(1000, 1000).getGraphicsContext2D();
		
		Group root = new Group();
		new Scene(root);
		root.getChildren().add(gc.getCanvas());
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 1000, 1000);
		gc.setFill(Color.WHITE);
		gc.setFont(font);
		gc.fillText(text, 0, 50);
		
		WritableImage image = new WritableImage(1000, 1000);
		gc.getCanvas().snapshot(null, image);
		
		PixelReader reader = image.getPixelReader();
		List<Integer> exists = new ArrayList<Integer>();
		int sum = 0;
		int height = 0;
		
		for(int i = 0; i < 1000; i++){
			boolean here = false;
			
			for(int j = 50; j >= 0; j--){
				if((reader.getArgb(i, j) & 0xFF) > 0){
					here = true;
					if(50 - j > height)
						height = 50 - j;
				}
			}
			
			exists.add(here ? 1 : 0);
			sum += here ? 1 : 0;
			
			if(exists.size() > 50){
				sum -= exists.get(i - 50);
				if(sum == 0){
					metrics[0] = i - 49;
					metrics[1] = height;
					return metrics;
				}
			}
			
		}
		
		metrics[0] = 950;
		metrics[1] = height;
		return metrics;
	}


	public List<SoundPlayer> getSounds() {
		return sounds;
	}


	public void setSounds(List<SoundPlayer> sounds) {
		this.sounds = sounds;
	}
}
