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
import images.ResizableImage;
import input.KeyboardInputHandler;
import input.MouseInputHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import sounds.SoundPlayer;
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
	private double SVX = 10;
	private double SVY = 10;
	private double scale = 1.0;
	private double scaleTime = 2; // Time to scale, in frames
	private boolean reached = false;
	private double xBuffer = 100; // Horizontal screen buffer, in pixels
	private double yBuffer = 100; // Vertical screen buffer, in pixels
	private double camX;
	private double camY;
	private double cameraTime = 2;
	
	public Level(Scene scene, Main main) {
		this.main = main;
		this.scene = scene;
		keyIn = new KeyboardInputHandler(scene);
		mouseIn = new MouseInputHandler(scene);
		sprites = new ArrayList<Sprite>();
		tree = new PlayerTree(this);
		background = Color.BLACK;
		camX = main.WIDTH / 2.0;
		camY = main.HEIGHT / 2.0;
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
	
	public void handle(int frame){
		PlayerTreeNode root = this.getTree().getRoot();
		if(root != null){
			Player player = root.getPlayer();
			KeyboardInputHandler keyIn = this.getKeyIn();
			double velX = 0;
			double velY = 0;
			
			if(keyIn.isKeyPressed("UP")) velY = -1 * SVY;
			if(keyIn.isKeyPressed("DOWN")) velY = SVY;
			if(keyIn.isKeyPressed("LEFT")) velX = -1 * SVX;
			if(keyIn.isKeyPressed("RIGHT")) velX = SVX;
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
		if(root != null && !reached){
			root.translateX();
			root.translateY();
		}
		
		double l = Double.MAX_VALUE;
		double r = Double.MIN_VALUE;
		double t = Double.MAX_VALUE;
		double b = Double.MIN_VALUE;
		
		l = 0; // Used only when camera is stationary
		t = 0;
		
		if(sprites.size() <= 0){
			if(scale != 1)
				scale(1);
			//if(camX != main.WIDTH / 2.0 || camY != main.HEIGHT / 2.0)
			//	setCamera(main.WIDTH / 2.0, main.HEIGHT / 2.0);
		}
		else {
			for(Sprite sprite : sprites){
				l = Math.min(sprite.getxPosition() * scale, l);
				r = Math.max(sprite.getxPosition() * scale + sprite.getWidth() * scale, r);
				t = Math.min(sprite.getyPosition() * scale, t);
				b = Math.max(sprite.getyPosition() * scale + sprite.getHeight() * scale, b);
			}
			
			this.tryRequiredScaleFactor(l, r, t, b);
			
			//this.tryRequiredCameraPosition(l, r, t, b); Doesn't work very well.
		}
	}
	
	private void tryRequiredScaleFactor(double l, double r, double t, double b){
		
		double reqWidth = r - l + xBuffer * scale;
		double reqHeight = b - t + yBuffer * scale;
		double reqWScale = reqWidth / ((double)main.WIDTH);
		double reqHScale = reqHeight / ((double)main.HEIGHT);
		
		double reqScale =  Math.max(reqWScale, reqHScale);
		
		if(reqScale == scale)
			return;

		if(reqScale < 1){
			if(scale > 1)
				scale(1);
		}
		else
			scale(scale + (reqScale - scale) / scaleTime);
	}
	
	private void tryRequiredCameraPosition(double l, double r, double t, double b){
		double reqX = (l + r) / 2.0;
		double reqY = (t + b) / 2.0;
		
		if(camX != reqX || camY != reqY)
			setCamera(camX + (reqX - camX) / cameraTime, camY + (reqY - camY) / cameraTime);
	}
	
	private boolean sameColor(Sprite one, Sprite two){
		return (one.isRed() && two.isRed()) || (one.isGreen() && two.isGreen()) || (one.isBlue() && two.isBlue());
	}
	
	public void draw(GraphicsContext gc){
		gc.setFill(background);
		gc.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		for(Sprite sprite: sprites) sprite.draw(gc);
	}
	
	public void scale(double factor){
		for(Sprite sprite : sprites){
			sprite.setxPosition(sprite.getxPosition() * scale / factor);
			sprite.setyPosition(sprite.getyPosition() * scale / factor);
			sprite.setWidth(sprite.getWidth() * scale / factor);
			sprite.setHeight(sprite.getHeight() * scale / factor);
		}
		
		this.SVX = this.SVX * scale / factor;
		this.SVY = this.SVY * scale / factor;
		
		this.scale = factor;
	}
	
	public void setCamera(double x, double y){
		double dx = x - camX;
		double dy = y - camY;
		
		for(Sprite sprite : sprites){
			sprite.setxPosition(sprite.getxPosition() - dx);
			sprite.setyPosition(sprite.getyPosition() - dy);
		}
		
		camX = x;
		camY = y;
	}
}
