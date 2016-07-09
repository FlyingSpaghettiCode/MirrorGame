/**
 * The tree heirarchy of Players.
 * @author Adriano Hernandez
 * @version 0.1
 * @date 8 July 2016
 * 
 * 
 **/ 
public abstract class PlayerTree{
  private int size;
  private int layers;
  private PlayerTreeNode root; //topmost node of the tree
  private PlayerTreeNode[][] layers; //an array of layers (nodes stored in arrays)
  
  public PlayerTree(){}
  
  public abstract PlayerTreeNode[] atLayer(int layer);
  public PlayerTreeNode root(){return root;}
}
