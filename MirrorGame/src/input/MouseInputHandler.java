package input;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * Basic mouse input handler.  Doesn't actually do anything with inputs yet.
 * @author Adriano
 * @version 0.1
 * @date 24 July 2016
 */
public class MouseInputHandler {

	private Scene scene;
	public MouseInputHandler(Scene scene) {
		this.scene = scene;
		init();
	}
	private void init(){
	    scene.setOnMouseClicked(
	        new EventHandler<MouseEvent>()
	        {
	            public void handle(MouseEvent e)
	            {
	                whenClicked();//define what to do when clicked in a child
	            }
	        });
	}
	//override this in a child
	protected void whenClicked(){
		
	}
}
