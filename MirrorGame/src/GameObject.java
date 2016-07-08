/**
* All game objects are extended from this.
* It remembers information as a rectangular object for simplicity, but we can
* extend it to make use circular or polygonal hitboxes.
* @author Adriano Hernandez
* @version 0.1
* @date 8 July 2016
*
*
 **/

public abstract class GameObject{
  //private PlayerTreeNode node; // this will be left up to children classes to decide
  private double x; //x position at top left corner
  private double y; //y position at top left corner
  private double w; //width
  private double h; //height
  private WorldType type; //world or color we are at
  
  public Player(){}
  
  //getters
  public double xPosition(){return x;}
  public double yPosition(){return y;}
  public double width(){return height;}
  public double height(){return height;}
  public WorldType type(){return type;}
  
  public double area(){return w*h;}
  
  //setters
  protected void setX(double x){this.x = x;}
  protected void setY(double y){this.y = y;}
  protected void setWidth(double w){this.w = w;}
  protected void setHeight(doubel h){this.h = h;}
}
