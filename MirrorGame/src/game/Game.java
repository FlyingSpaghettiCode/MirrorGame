package game;

import images.ResizableImage;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.util.Arrays;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import levels.Level;
import levels.LevelReader;
import math.Camera;
import math.Function;
import players.Player;
import sounds.SoundPlayer;
import sprites.Lever;
import sprites.Portal;
import sprites.Wall;
import sprites.Menu;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import input.KeyboardInputHandler;

/**
 * This is the main class.
 * 
 * @authors Adriano Hernandez, Patrick Zhong
 * @version 0.2
 * @date 24 July 2016
 */

// THIS IS RUNNING A DEMO APPLICATION RIGHT NOW
public class Game extends Application {
	
	public double WIDTH = 1920;
	public double HEIGHT = 1080;

	// main
	public static void main(String[] args) {
		System.out.println("Move with arrow keys.");
		// this is the entrypoint into the game
		// if you want to know more about the specific inner workings of javafx
		// go to oracle's docs

		launch(args);
	}

	///////////////////////////////////////////////////////////
	// GAME OBJECTS AND GAME MANAGEMENT OBJECTS ARE DEFINED HERE
	LevelReader globalReader = new LevelReader(); // reads all the levels
	Group root;
	Scene scene;
	Canvas canvas;
	GraphicsContext gc;
	Font font;
	Level level;
	int i;
	boolean paused = false;
	KeyboardInputHandler keyIn;
	Menu menu;
	Camera camera;
	///////////////////////////////////////////////////////////

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public KeyboardInputHandler getKeyIn() {
		return keyIn;
	}

	// this is a method called by launch that "starts" the game
	// the gameloop is within it
	@Override
	public void start(Stage stage) {
		// initialize conditions
		init(stage);

		// seperating time from init since it doesn't fit with the rest of init
		// functions
		final long startNanoTime = System.nanoTime();// start time
		// double t = 0; //time
		i = 0; // iteration
		// This is the game loop. There are different ways of doing this, but
		// this is the easiest.
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				// time keeper
				@SuppressWarnings("unused")
				// btw, if you want to find the actual fps (make sure it isn't
				// lagging)
				// divide the iteration by 60 to find what the time should be
				// and compare that with the value of t
				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				i++;
				if (i % 60 == 0)
					new SoundPlayer("src/sounds/midClick1.mp3").playSound(); // 60
																				// for
																				// our
																				// 60
																				// fps
				// System.out.println(t);//lets see the time; take out comment
				// markers to view
				// System.out.println(i);
				refresh(i);
				draw();
			}
		}.start();

		stage.show();
	}

	// initialization for the game's current state
	public void init(Stage stage) {
		// init is here for now
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		WIDTH = size.getWidth();
		HEIGHT = size.getHeight();
		
		stage.setTitle("Canvas Example");

		root = new Group();
		scene = new Scene(root);

		keyIn = new KeyboardInputHandler(scene);
		menu = new Menu(this);

		stage.setScene(scene);
		stage.setTitle("Mirror Game");
		stage.setFullScreen(true);
		
		canvas = new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		gc = canvas.getGraphicsContext2D();

		// choose some settings
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		font = Font.font("Times New Roman", FontWeight.BOLD, 48);

		// SPRITE AND INPUT AND SCENE
		level = new Level(scene, this);

		Portal po1 = new Portal(500, 400, true, false, false);
		Portal po2 = new Portal(500, -100, true, false, false);
		po1.setExit(po2);
		level.addSprite(po1);
		level.addSprite(po2);

		for (int i = 0; i < 5; i++) {
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

		for (int i = 1; i <= 20; i++) {
			Wall wall = new Wall();
			wall.red = true;
			wall.setImage(new ResizableImage("/images/goal.png"));
			wall.setxPosition(500 + i * 67);
			wall.setyPosition(268);
			level.addSprite(wall);
		}
		
		level.addSprite(new Lever(500 + 22 * 67, 268, true, false, false, level, level.getSprites().indexOf(po1), "sprites.Sprite", "green", true, false));

		Player p1 = new Player(level, "/images/red.png");
		p1.red = true;
		level.addPlayer(p1);

		Player p2 = new Player(level, "/images/green.png");
		p2.green = true;
		p2.setxPosition(900);
		p2.setyPosition(300);
		level.addPlayer(p2, p1, new Function() {
			@Override
			public double[] execute(double x, double y) {
				double[] result = { x * 2, y * 2 };
				return result;
			}
		});
		
camera = new Camera(level, this);

		
	}

	// game loop methods
	// refreshes states of the game, can take input
	public void refresh(int i) {
		// take input and refresh

		// clear everything
		gc.clearRect(0, 0, WIDTH, HEIGHT);

		// refresh
		gc.setFont(font);

		keyIn.handle(i);

		if (!paused){
			level.handle(i);
			camera.update();
		}

		if (keyIn.isKeyPressed("P")) {
			this.setPaused(!this.isPaused());
			keyIn.blockKey("P", 15);
		} else if (keyIn.isKeyPressed("ESCAPE")) {
			System.err.println("Game terminated.");
			System.exit(0);
		}

	}

	// all images manifest themselves on the screen
	public void draw() {
		// draw stuff
		level.draw(gc);
		
		if (paused) {
			menu.draw(gc);
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			FontMetrics metrics = (new java.awt.Canvas()).getFontMetrics(new java.awt.Font("Verdana",java.awt.Font.BOLD, 24));
			gc.fillText("Paused", WIDTH / 2 - metrics.stringWidth("Paused")/2.0, HEIGHT / 2 - metrics.getAscent()/2.0);
		}
	}
}