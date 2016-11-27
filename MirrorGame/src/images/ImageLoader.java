package images;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageLoader {
	
	HashMap<String, Image> images = new HashMap<String, Image>();
	
	Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
	
	public ImageLoader(String... names){
		
		images.put("red", this.loadImage("red"));
		images.put("blue", this.loadImage("blue"));
		images.put("green", this.loadImage("green"));
		
		for(String name : names){
			Image image = this.loadImage(name);
			for(Color color : colors)
				images.put(name+"."+color, this.getColoredImage(image, color));
		}
		
	}
	
	private Image loadImage(String name){
		
		return new Image("/images/"+name+".png");
		
	}
	
	private Image getColoredImage(Image im, Color c){
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
	
	public HashMap<String, Image> getImages(){
		return images;
	}
	
	public Image getImage(String name, Color color){
		return images.get(name+"."+color);
	}

}
