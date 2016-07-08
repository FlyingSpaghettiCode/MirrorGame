/**
 * This is a simple player class.
 * 
 * @author Adriano Hernandez
 * @version 0.1
 * @date 9 July 2016
 **/
public abstract class Player{
  //private PlayerTreeNode node; // this will be left up to children classes to decide
  private double x; //x position at top left corner
  private double y; //y position at top left corner
  private double w; //width
  private double h; //height
  private WorldType type; //world or color we are at
  
  public Player(){}
  
  public double xPosition(){return x;}
  public double yPosition(){return y;}
  public double width(){return height;}
  public double height(){return height;}
  public WorldType type(){return type;}
  
  public 
  
  public void translateX(double x);
  public void translateY(double y);
  public void display();
}
