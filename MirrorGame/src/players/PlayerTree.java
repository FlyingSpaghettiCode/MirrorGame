package players;

import java.util.ArrayList;

import input.KeyboardInputHandler;
import levels.StandardLevel;

//a player tree
/**
 * This ia player tree class that we find in standard levels
 * @author Adriano
 *
 */
public class PlayerTree {

	private PlayerTreeNode root;
	private StandardLevel level;
	
	public PlayerTree(StandardLevel level) {
		// TODO Auto-generated constructor stub
		this.level = level;
		root = new PlayerTreeNode();
	}

	public PlayerTreeNode getRoot() {
		return root;
	}

	public void setRoot(PlayerTreeNode root) {
		this.root = root;
	}

	//some basic functionality
	public ArrayList<PlayerTreeNode> getNodes(){
		ArrayList<PlayerTreeNode> ls = new ArrayList<PlayerTreeNode>();
		addNodesToArrayList(ls, root);
		return ls;
	}
	private void addNodesToArrayList(ArrayList<PlayerTreeNode> ls, PlayerTreeNode root){
		if(!ls.contains(root)) ls.add(root);
		for(PlayerTreeNode child: root.getChildren())
			addNodesToArrayList(ls,child);
	}
	
	public ArrayList<Player> getPlayers(){
		ArrayList<PlayerTreeNode> nodes = getNodes();
		ArrayList<Player> players = new ArrayList<Player>();
		for(PlayerTreeNode node: nodes)
			players.add(node.getPlayer());
		return players;
	}
	public int size(){
		return getNodes().size();
	}

	//translate methods
	//these will tell the root to translate, which in turn tells it's children to translate
	public void translateX(){
		root.translateX();
	}
	public void translateY(){
		root.translateX();
	}
	public void input(){
		KeyboardInputHandler keyIn = level.getKeyIn();
		if(keyIn.isKeyPressed("UP")) this.translateY();
		if(keyIn.isKeyPressed("DOWN")) this.translateY();
		if(keyIn.isKeyPressed("LEFT")) this.translateX();
		if(keyIn.isKeyPressed("RIGHT")) this.translateX();
	}
}
