package sprites;

import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Menu {

	Game main;

	public Menu(Game main) {
		this.main = main;
	}

	public void draw(GraphicsContext gc) {

		gc.setFill(Color.GAINSBORO);
		gc.fillRect(main.WIDTH / 4, main.HEIGHT / 4, main.WIDTH / 2, main.HEIGHT / 2);

	}

}