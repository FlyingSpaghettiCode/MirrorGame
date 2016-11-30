package math;

public class Grid {
	
	public static double TILE_WIDTH = 40;
	static int reqTilesX = 25;
	static int reqTilesY = 25;
	public static double WIDTH;
	public static double HEIGHT;
	
	public static void init(double width, double height){
		TILE_WIDTH = Math.min(width/reqTilesX, height/reqTilesY);
		WIDTH = width / TILE_WIDTH;
		HEIGHT = height / TILE_WIDTH;
	}
	
	public static double tilesToPixels(double tiles){
		return tiles * TILE_WIDTH;
	}
	
	public static double pixelsToTiles(double pixels){
		return pixels / TILE_WIDTH;
	}

}
