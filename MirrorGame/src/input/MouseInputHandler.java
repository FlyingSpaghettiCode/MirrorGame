package input;

import java.util.HashSet;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * Basic mouse input handler. Doesn't actually do anything with inputs yet.
 * 
 * @author Adriano, Patrick
 * @version 0.1
 * @date 24 July 2016
 */
public class MouseInputHandler {

	private Scene scene;
	private static HashSet<MouseEventHandler> handlers = new HashSet<MouseEventHandler>();

	public MouseInputHandler(Scene scene) {
		this.scene = scene;
		init();
	}

	public void add(MouseEventHandler handler) {
		handlers.add(handler);
	}
	
	public static HashSet<MouseEventHandler> getHandlers(){
		return handlers;
	}

	private void init() {
		EventHandler<MouseEvent> mouseListener = new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				for (MouseEventHandler handler : MouseInputHandler.getHandlers())
					handler.handle(e);
			}
		};
		
		scene.setOnMouseClicked(mouseListener);
		scene.setOnMouseMoved(mouseListener);
	}
}
