import images.ResizableImage;
import sprites.*;

public class Animator{
  private List<ResizableImage> images;
  public Animator(List<ResizableImage> images){
    this.images = images;
  }
  public void setImage(Sprite sprite, int n){
    sprite.setImage(images.get(n));
  }
  public void setImage(Sprite sprite, ResizableImage image){
    sprite.setImage(image);
  }
}
