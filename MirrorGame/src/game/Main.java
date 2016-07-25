package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import sprites.ControllableSprite;
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

//THIS IS RUNNING A DEMO APPLICATION RIGHT NOW
public class Main extends Application 
{
	//we will let this be mutated later on; for now this is the easy way out
	private final int WIDTH = 800;
	private final int HEIGHT = 450;
	
	//main
    public static void main(String[] args) 
    {
    	System.out.println("Move with arrow keys.");
    	//this is the entrypoint into the game
    	//if you want to know more about the specific inner workings of javafx go to oracle's docs
        launch(args);
    }

    ///////////////////////////////////////////////////////////
    //GAME OBJECTS AND GAME MANAGEMENT OBJECTS ARE DEFINED HERE
    Group root;
    Scene scene;
    Canvas canvas;
    GraphicsContext gc;
    Font font;
    ControllableSprite testImage;
    ///////////////////////////////////////////////////////////
    
    //this is a method called by launch that "starts" the game
    //the gameloop is within it
    @Override
    public void start(Stage stage)
    {
    	//initialize conditions
    	init(stage);
        
    	//seperating time from init since it doesn't fit with the rest of init functions
        final long startNanoTime = System.nanoTime();//start time
        //This is the game loop.  There are different ways of doing this, but this is the easiest.
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	//time keeper
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
                //System.out.println(t);//lets see the time; take out comment markers to view
            	refresh();
            	draw();
            }
        }.start();
        
        stage.show();
    }
    
    //initialization for the game's current state
    public void init(Stage stage){
    	//init is here for now
        stage.setTitle( "Canvas Example");
        
        root = new Group();
        scene = new Scene( root );
        
        stage.setScene( scene );
        
        canvas = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add( canvas );
     
        gc = canvas.getGraphicsContext2D();
        
        //choose some settings
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        font = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        
        //SPRITE AND INPUT
    	testImage = new ControllableSprite(scene);
    	//write /<package>/<image.png> to get the image path
    	testImage.setImage(new Image("/images/testImage.png"));
    }
    //game loop methods
    //refreshes states of the game, can take input
    public void refresh(){
    	//take input and refresh
    	
    	//clear everything
    	gc.clearRect(0, 0, WIDTH, HEIGHT);
    	
        //refresh
        gc.setFont( font );
        testImage.input();
    }
    //all images manifest themselves on the screen
    public void draw(){
    	//draw stuff
    	gc.fillText( "DEMO", WIDTH/2-96, HEIGHT/2);
        gc.strokeText( "DEMO", WIDTH/2-96, HEIGHT/2 );
        
    	testImage.draw(gc);
    }
}