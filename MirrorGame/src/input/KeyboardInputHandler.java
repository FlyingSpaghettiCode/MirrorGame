package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * This an input handler class. It hakes in input and handles it. It is up to
 * individual objects to choose how to interpret such input. This class simply
 * takes in the input and is able to give out information about it; nothing
 * more.
 * 
 * @author Adriano
 * @version 0.2
 * @date 24 July 2016
 */
public class KeyboardInputHandler {

	// instance variables
	HashSet<String> input = new HashSet<String>();;
	private Scene scene;
	private HashMap<String, Integer> noflylist = new HashMap<String, Integer>();

	// constructors
	public KeyboardInputHandler(Scene scene) {
		init(scene);
	}

	public void blockKey(String key, int ticks) {
		noflylist.put(key, ticks);
		input.remove(key);
	}

	public void handle(int frame) {
		for (String key : ((HashMap<String, Integer>) noflylist.clone()).keySet()) {
			int ticksLeft = noflylist.get(key);
			if (ticksLeft > 1)
				noflylist.put(key, ticksLeft - 1);
			else
				noflylist.remove(key);
		}
	}

	public boolean isKeyPressed(String key) {
		return input.contains(key);
	}

	// update our current inputs in our scene
	private void init(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();

				// only add once... prevent duplicates
				if (!noflylist.containsKey(code))
					input.add(code);
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				input.remove(code);
			}
		});
	}
}
