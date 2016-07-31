package levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.scene.Scene;

/**
 * Parses a text document that tells how to build the level.
 * It also can write them.
 * @author Adriano
 *
 */
public class LevelReader {

	//basic shit
	private String PATH;
	//PATH CAN GO ANYWHERE ON COMP
	public LevelReader(String PATH) {
		this.PATH = PATH;
		// TODO Auto-generated constructor stub
	}
	public LevelReader(){
		this.PATH = "C:\\Users\\" + 
			System.getProperty("user.name") + 
				"\\Documents\\MirrorGame\\LevelFiles\\test-level.txt";
	}
	private List<String> docText() throws IOException{
		return Files.readAllLines(Paths.get(PATH));
	}
	//level creation methods
	//if this returns null then you know it failed
	@SuppressWarnings("unused")
	public Level createLevel(Scene scene){
		Level level;
		try {
			List<String> lines = docText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // failure
		}
		//level creation code from txt
		level = new STDLevel(scene);
		
		
		
		//return
		return level;
	}
	
	
	
	//getters
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}

	
	
	@SuppressWarnings("unused")
	private class STDLevel extends Level{

		public STDLevel(Scene scene) {
			super(scene);
			// TODO Auto-generated constructor stub
		}
		
	}
}
