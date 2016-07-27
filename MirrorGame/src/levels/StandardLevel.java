package levels;

import javafx.scene.Scene;
import players.Player;

public class StandardLevel extends Level {

	public StandardLevel(Scene scene) {
		super(scene);
		
		Player root = new Player(this);
		root.setParent(null);
		this.addSprite(root);
	}

}
