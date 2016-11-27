package images;

import javafx.scene.paint.Color;
import sprites.Sprite;

public class ColorUtil {
	
	public static Color getColor(boolean red, boolean green, boolean blue){
		return new Color(red ? 1 : 0, green ? 1 : 0, blue ? 1 : 0, 1);
	}
	
	public static Color getColor(Sprite sprite){
		return getColor(sprite.red, sprite.green, sprite.blue);
	}
	
	public static Color[] getAllColors(){
		
		Color[] colors = new Color[8];
		
		for(int i = 0; i < 2; i ++)
			for(int j = 0; j < 2; j++)
				for(int k = 0; k < 2; k++)
					colors[i * 4 + j * 2 + k] = new Color(i, j, k, 1);
		
		return colors;
	}

}
