package players;

import java.util.ArrayList;

import input.KeyboardInputHandler;

//a player tree
public class PlayerTree {

	private PlayerTreeNode root;
	private KeyboardInputHandler input;
	
	public PlayerTree() {
		// TODO Auto-generated constructor stub
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

	public KeyboardInputHandler getInput() {
		return input;
	}

	public void setInput(KeyboardInputHandler input) {
		this.input = input;
	}
}
