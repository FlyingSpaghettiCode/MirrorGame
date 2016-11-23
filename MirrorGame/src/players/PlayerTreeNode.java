package players;

import java.util.ArrayList;
import java.util.List;

import math.Function;

public class PlayerTreeNode {

	private PlayerTreeNode parent;
	private ArrayList<PlayerTreeNode> children = new ArrayList<PlayerTreeNode>();
	private Player player; // the player this node is associated with
	private Function function;

	public PlayerTreeNode(Player player, Function function) {
		this.player = player;
		this.function = function;
	}

	// some functionality
	public boolean isRoot() {
		return parent == null;
	}

	public void addChild(PlayerTreeNode node) {
		children.add(node);
	}

	public void removeChild(PlayerTreeNode node) {
		children.remove(node);
	}

	// getters and setters
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

	// overriding some basic methods
	@Override
	public boolean equals(Object other) {
		if (other instanceof PlayerTreeNode)
			return this.getPlayer().equals(((PlayerTreeNode) other).getPlayer());
		else
			return false;
	}

	public void calcVelocity(double x, double y) {
		double result[] = function.execute(x, y);
		player.setVelocityX(result[0]);
		player.setVelocityY(result[1]);

		for (PlayerTreeNode node : this.getChildren())
			node.calcVelocity(result[0], result[1]);
	}

	public void translateX() {
		player.translateX(player.getVelocityX());
		for (PlayerTreeNode node : this.getChildren())
			node.translateX();
	}

	public void translateY() {
		player.translateY(player.getVelocityY());
		for (PlayerTreeNode node : this.getChildren())
			node.translateY();
	}

}
