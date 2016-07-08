/**
 * This is a simple player class.
 * 
 * @author Adriano Hernandez
 * @version .1
 * @date 9 July 2016
 **/
public abstract class Player{
  private double x; //x position at top left corner
  private double y; //y position at top left corner
  private double w; //width
  private double h; //height
  private WorldType type; //world or color we are at
  
  public Player(){}
  public Player(double x, double y){}
  
  public void translateX(double x);
  public void translateY(double y);
  public void display();
}
