package images;

import javafx.scene.image.Image;

//this class actually does nothing though........
public class ResizableImage extends Image {
	
	double height;
	double width;
        double xRatio;
        double yRatio;
        
	public ResizableImage(String arg0) {
		super(arg0);
		height = this.getHeight();
		width = this.getWidth();
                xRatio = 1.;
                yRatio = 1.;
	}

	// Gets the height and width that is actually used.
	public double getActualHeight(){
		return height;
	}
	
	public double getActualWidth(){
		return width;
	}
        public double getXRatio()
        {
            return xRatio;
        }
        public double getYRatio()
        {
            return yRatio;
        }
	
	public void setHeight(double h){
		height = h;
                yRatio = getHeight()/height;
	}
	
	public void setWidth(double w){
		width = w;
                xRatio = getWidth()/width;
	}
}
