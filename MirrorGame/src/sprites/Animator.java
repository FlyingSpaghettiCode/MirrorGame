package sprites;


import java.util.List;

import images.ResizableImage;

public class Animator {

	private List<ResizableImage> images;
	public Animator(List<ResizableImage> images) {
		// TODO Auto-generated constructor stub
		this.setImages(images);
	}
	public List<ResizableImage> getImages() {
		return images;
	}
	public void setImages(List<ResizableImage> images) {
		this.images = images;
	}
	
	public void setImage(Sprite sprite, ResizableImage image){
		sprite.setImage(image);
	}
	public void setImage(Sprite sprite, int index) throws IndexOutOfBoundsException{
		setImage(sprite,images.get(index));
	}
	

}
