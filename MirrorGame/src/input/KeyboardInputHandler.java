package input;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * This an input handler class.  It hakes in input and handles it.
 * It is up to individual objects to choose how to interpret such input.
 * This class simply takes in the input and is able to give out information
 * about it; nothing more.
 * 
 * @author Adriano
 * @version 0.2
 * @date 24 July 2016
 */
public class KeyboardInputHandler {

	//instance variables
	ArrayList<String> input;
	@SuppressWarnings("unused")
	private Scene scene;
	
	//constructors
	public KeyboardInputHandler(Scene scene) {
		init(scene);
	}
	
	public boolean isKeyPressed(String key){
		return input.contains(key);
	}
	//update our current inputs in our scene
	private void init(Scene scene){
		input = new ArrayList<String>();
	        scene.setOnKeyPressed(
	                new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();
	     
	                        // only add once... prevent duplicates
	                        if ( !input.contains(code) )
	                            input.add( code );
	                    }
	                });
	     
	            scene.setOnKeyReleased(
	                new EventHandler<KeyEvent>()
	                {
	                    public void handle(KeyEvent e)
	                    {
	                        String code = e.getCode().toString();
	                        input.remove( code );
	                    }
	                });
	}
}
