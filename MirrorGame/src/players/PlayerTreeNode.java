package players;

import java.util.ArrayList;
import java.util.List;

import math.Function;

public class PlayerTreeNode {

	private PlayerTreeNode parent;
	private ArrayList<PlayerTreeNode> children = new ArrayList<PlayerTreeNode>();
	private Player player; //the player this node is associated with
	private ArrayList<Function> functionsX;
	private ArrayList<Function> functionsY;
	
	public PlayerTreeNode(Player player, List<Function> functionsX, List<Function> functionsY) {
		this.player = player;
		this.functionsX = new ArrayList<Function>(functionsX);
		this.functionsY = new ArrayList<Function>(functionsY);
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
	public ArrayList<PlayerTreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<PlayerTreeNode> children) {
		this.children = children;
	}

	public PlayerTreeNode getParent() {
		return parent;
	}

	public void setParent(PlayerTreeNode parent) {
		this.parent = parent;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
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
	
	public void calcVelocityX(double x){
		for(Function function : functionsX)
			x = function.execute(x);
		player.setVelocityX(x);
		for(PlayerTreeNode node : this.getChildren())
			node.calcVelocityX(x);
	}
	
	public void calcVelocityY(double y){
		for(Function function : functionsY)
			y = function.execute(y);
		player.setVelocityY(y);
		for(PlayerTreeNode node : this.getChildren())
			node.calcVelocityY(y);
	}
	
	public void translateX(){
		player.translateX(player.getVelocityX());
		for(PlayerTreeNode node : this.getChildren())
			node.translateX();
	}
	
	public void translateY(){
		player.translateY(player.getVelocityY());
		for(PlayerTreeNode node : this.getChildren())
			node.translateY();
	}

}
