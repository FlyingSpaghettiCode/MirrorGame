/**
 * PlayerTreeNode handles calculations and dependencies on the player tree on a local scale
 * between nodes.  Unlike the player class it does not deal with moving or display, just the abstract.
 * 
 * @author Adriano Hernandez
 * @version 0.1
 * @date 8 July 2016
 * 
 **/ 
public abstract class PlayerTreeNode{
  private Player player; //its player
  
  private PlayerTreeNode parent; //the parent node
  private PlayerTreeNode[] children; //children of this node (can have more than two)
  
  public PlayerTreeNode(){}
  
  //getters and basic methods
  public PlayerTreeNode parent(){
    return parent;
  }
  public Player player(){
    return player;
  }
  public PlayerTreeNode[] children(){
    return children;
  }
  public boolean isRoot(){
    return parent == null;
  }
  
  //translation function
  public abstract double translateFunctionX(double input);
  public abstract double translateFunctionY(double input);
}
