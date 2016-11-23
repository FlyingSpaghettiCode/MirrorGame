package images;

import javafx.scene.image.Image;

//this class actually does nothing though........
public class ResizableImage extends Image {
	
	double height;
	double width;

	public ResizableImage(String arg0) {
		super(arg0);
		height = this.getHeight();
		width = this.getWidth();
	}

	// Gets the height and width that is actually used.
	public double getActualHeight(){
		return height;
	}
	
	public double getActualWidth(){
		return width;
	}
	
	public void setHeight(double h){
		height = h;
	}
	
	public void setWidth(double w){
		width = w;
	}
}
