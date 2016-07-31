package players;

import java.util.ArrayList;

public class PlayerTreeNode {

	private PlayerTreeNode parent;
	private ArrayList<PlayerTreeNode> children;
	private Player player; //the player this node is associated with
	
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
	
	
	
	
	public void translateX(){
		
	}
	public void translateY(){
		
	}

}
