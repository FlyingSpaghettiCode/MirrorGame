package game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This is the main class.
 * @author Adriano Hernandez
 * @version 0.2
 * @date 24 July 2016
 */

public class Main extends Application 
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    //this is a method called by launch that "starts" the game
    //the gameloop is within it
    @Override
    public void start(Stage theStage) 
    {
    	//SPRITE
    	Sprite testImage = new Sprite();
    	testImage.setxPosition(0);
    	testImage.setyPosition(0);
    	//write /<package>/<image.png> to get the image path
    	testImage.setImage(new Image("/images/testImage.png"));
    	
    	
    	//init is here for now
        theStage.setTitle( "Canvas Example");
        
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        
        Canvas canvas = new Canvas( 400, 200 );
        root.getChildren().add( canvas );
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        //choose some settings
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        Font theFont = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        
        final long startNanoTime = System.nanoTime();//start time
        //This is the game loop.  There are different ways of doing this, but this is the easiest.
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	//time keeper
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
                System.out.println(t);//lets see the time
                
                //refresh
                gc.setFont( theFont );
                gc.fillText( "Hello, World!", 60, 50 );
                gc.strokeText( "Hello, World!", 60, 50 );
                
                
                //SPRITE TEST
                testImage.draw(gc);
                
            }
        }.start();
        
        theStage.show();
    }
    
    //initialization for the game's current state
    public void init(){
    	
    }
    //game loop methods
    //refreshes states of the game, can take input
    public void refresh(){
    	//take input and refresh
    }
    //all images manifest themselves on the screen
    public void draw(){
    	//draw stuff
    }
}