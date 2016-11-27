package images;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageLoader {
	
	static HashMap<String, Image> images = new HashMap<String, Image>();
	
	public static void load(String... names){
		
		images.put("red", loadImage("red"));
		images.put("blue", loadImage("blue"));
		images.put("green", loadImage("green"));
		
		for(String name : names){
			Image image = loadImage(name);
			for(Color color : ColorUtil.getAllColors())
				images.put(name+"."+color, getColoredImage(image, color));
		}
		
	}
	
	private static Image loadImage(String name){
		
		return new Image("/images/"+name+".png");
		
	}
	
	private static Image getColoredImage(Image im, Color c){
		PixelReader bi = im.getPixelReader();
		
		WritableImage image = new WritableImage((int)im.getWidth(), (int)im.getHeight());
		PixelWriter pw = image.getPixelWriter();
		
		for(int a = 0; a < (int)im.getWidth(); a++){
			for(int b = 0; b < (int)im.getHeight(); b++){
				Color color = bi.getColor(a, b);
				pw.setColor(a, b, color.getRed() > 0 ? c : color);
			}
		}
		
		return image;
	}
	
	public static HashMap<String, Image> getImages(){
		return images;
	}
	
	public static Image getImage(String name, Color color){
		return images.get(name+"."+color);
	}

}
