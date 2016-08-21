package game;

import images.ResizableImage;

import java.util.Arrays;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import levels.Level;
import math.Action;
import math.Function;
import levels.LevelReader;
import levels.LevelWriter;
import players.Player;
import sounds.SoundPlayer;
import sprites.Lever;
import sprites.Portal;
import sprites.Wall;
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
	public final double WIDTH = 1920;
	public final double HEIGHT = 1080;
	
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
    Level level;
    int i;
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
        //double t = 0; //time
        i = 0; //iteration
        //This is the game loop.  There are different ways of doing this, but this is the easiest.
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
            	//LevelReader bob = new LevelReader();
            	//bob.createLevel(scene);
            	
            	//LevelWriter joe = new LevelWriter();
            	//joe.writeFile(new Level(scene));
            	
            	//time keeper
                @SuppressWarnings("unused")
                //btw, if you want to find the actual fps (make sure it isn't lagging)
                //divide the iteration by 60 to find what the time should be
                //and compare that with the value of t
				double t = (currentNanoTime - startNanoTime)/1000000000.0; 
                i++;
                if(i%60 == 0)new SoundPlayer("src/sounds/midClick1.mp3").playSound(); //60 for our 60 fps
                //System.out.println(t);//lets see the time; take out comment markers to view
                //System.out.println(i);
            	refresh(i);
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
        stage.setTitle("Mirror Game");
        
        canvas = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add( canvas );
     
        gc = canvas.getGraphicsContext2D();
        
        //choose some settings
        gc.setFill( Color.RED );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(2);
        font = Font.font( "Times New Roman", FontWeight.BOLD, 48 );
        
        //SPRITE AND INPUT AND SCENE
        level = new Level(scene, this);

        level.addSprite(new Lever(500 + 22 * 67, 268, true, false, false, level));
		
		Portal reciever = new Portal(500, 400, true, false, false, null, false);
		level.addSprite(reciever);
		level.addSprite(new Portal(500, -100, true, false, false, reciever, true));
		    
        for(int i = 0; i < 5; i++){
			Wall wall = new Wall();
	        wall.red = true;
	        wall.setImage(new ResizableImage("/images/goal.png"));
	        wall.setxPosition(500);
	        wall.setyPosition(i * 67);
	        level.addSprite(wall);
	        
	        wall = new Wall();
	        wall.green = true;
	        wall.setImage(new ResizableImage("/images/goal.png"));
	        wall.setxPosition(600);
	        wall.setyPosition(500 + i * 67);
	        level.addSprite(wall);
		}
		
		for(int i = 1; i <= 20; i++){
			Wall wall = new Wall();
	        wall.red = true;
	        wall.setImage(new ResizableImage("/images/goal.png"));
	        wall.setxPosition(500 + i * 67);
	        wall.setyPosition(268);
	        level.addSprite(wall);
		}
		
		Player p1 = new Player(level, "/images/red.png");
        p1.red = true;
        level.addPlayer(p1);
        
        Player p2 = new Player(level, "/images/green.png");
        p2.green = true;
        p2.setxPosition(900);
        p2.setyPosition(300);
        level.addPlayer(p2, p1, Arrays.asList(new Function(Action.MULTIPLY, 2)), Arrays.asList(new Function(Action.MULTIPLY, 2)));
        
        /*Player p3 = new Player(level, "/images/blue.png");
        p3.blue = true;
        p3.setxPosition(10);
        p3.setyPosition(300);
        level.addPlayer(p3, p1, Arrays.asList(new Function(Action.POWER, 1.1), new Function(Action.ADD, 3)), Arrays.asList(new Function(Action.POWER, 1.1), new Function(Action.ADD, 1)));
        */
		
        
		//new SoundPlayer("src/sounds/racing2.mp3").playSound();
        
        
        
    	//write /<package>/<image.png> to get the image path
    	//testImage.setImage(new Image("/images/testImage.png"));
    }
    //game loop methods
    //refreshes states of the game, can take input
    public void refresh(int i){
    	//take input and refresh
    	
    	//clear everything
    	gc.clearRect(0, 0, WIDTH, HEIGHT);
    	
        //refresh
        gc.setFont( font );
       	level.handle(i);
    }
    //all images manifest themselves on the screen
    public void draw(){
    	//draw stuff
    	gc.fillText( "DEMO", WIDTH/2-96, HEIGHT/2);
        gc.strokeText( "DEMO", WIDTH/2-96, HEIGHT/2 );
        
    	level.draw(gc);
    }
}
