package players;

import java.util.ArrayList;
import sprites.MoveableSprite;

public class PlayerTreeNode {

	private PlayerTreeNode parent;
	private ArrayList<PlayerTreeNode> children;
	private MoveableSprite player; //the player this node is associated with
	
	public PlayerTreeNode() {
		// TODO Auto-generated constructor stub
	}

	
	//some functionality
	public boolean isRoot(){
		return parent == null;
	}
	public void addChild(PlayerTreeNode node){
		children.add(node);
	}
	public void removeChild(PlayerTreeNode node){
		children.remove(node);
	}
	
	//getters and setters
	public PlayerTreeNode getParent() {
		return parent;
	}

	public void setParent(PlayerTreeNode parent) {
		this.parent = parent;
	}

	public MoveableSprite getPlayer() {
		return player;
	}

	public void setPlayer(MoveableSprite player) {
		this.player = player;
	}

	
	//overriding some basic methods
	@Override
	public boolean equals(Object other){
		if(other instanceof PlayerTreeNode) 
			return this.getPlayer().equals(((PlayerTreeNode)other).getPlayer());
		else 
			return false;
	}
	
	
	
	
	public void translateX(double x){
		for(PlayerTreeNode i:children)	
			i.translateX(x);
		player.translateX(x);
	}
	public void translateY(double y){
		for(PlayerTreeNode i:children)	
			i.translateY(y);
		player.translateY(y);
	}

}
