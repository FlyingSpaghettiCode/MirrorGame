package levels;

import java.util.List;
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
		this.frame = frame;
		
		PlayerTreeNode root = this.getTree().getRoot();
		if(root != null){
			Player player = root.getPlayer();
			KeyboardInputHandler keyIn = this.getKeyIn();
			double velX = 0;
			double velY = 0;
			
			if(keyIn.isKeyPressed("UP")) velY = -1 * SV;
			if(keyIn.isKeyPressed("DOWN")) velY = SV;
			if(keyIn.isKeyPressed("LEFT")) velX = -1 * SV;
			if(keyIn.isKeyPressed("RIGHT")) velX = SV;
			
			if(velX != 0 && velY != 0){
				// Set the overall speed to SV
				double mag = Math.sqrt(Math.pow(velX, 2) + Math.pow(velY, 2));
				velX *= SV / mag;
				velY *= SV / mag;
			}
			
			if(keyIn.isKeyPressed("ESCAPE")){
				System.err.println("Game terminated.");
				System.exit(0);
			}
			
			root.calcVelocityX(velX);
			root.calcVelocityY(velY);
		}
		
		for(Sprite sprite: sprites) sprite.handle();
		if(root != null && !reached){
			root.translateX();
			root.translateY();
		}
		
		this.handleCollisions();
		
		camera.update();
		
	}
	
	private void handleCollisions(){
		for(int i = 0; i < sprites.size(); i++){
			
			Sprite sprite = sprites.get(i);
			
			if(!(sprite instanceof Collidable))
				continue;
		
			for(int j = i+1; j < sprites.size(); j++){
				
				Sprite otherSprite = sprites.get(j);
				
				if(!(otherSprite instanceof Collidable) || !sameColor(sprite, otherSprite))
					continue;;
				
				double[] mtv = ((Collidable) sprite).getHitbox().getMTV(((Collidable) otherSprite).getHitbox());
				
				if(mtv[0] <= 0 && mtv[1] <= 0)
					continue;
				
				// Collision
				boolean sM = sprite instanceof MoveableSprite;
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
				
				mtv[0] *= mod;
				mtv[1] *= mod;
				
				((Collidable)(sM ? otherSprite : sprite)).handle((Collidable)target, mtv);
			}
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
