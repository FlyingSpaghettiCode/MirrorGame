package levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.scene.Scene;

/**
 * Parses a text document that tells how to build the level.
 * It also can write them.
 * 
 * This is basicallly a factory class
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
		List<String> lines;
		try {
			lines = docText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // failure
		}
		//level creation code from txt
		level = new STDLevel(scene);
		simplify(lines);
		
		
		//return
		return level;
	}
	//return without any \ or && or anything like that
	//(just break up into one command per line format for ease of parsing)
	//won't break on null, but won't do anything
	private void simplify(List<String> original){
		if(original == null) return;
		for(int i = original.size(); i > 0 ; i--){
			if(original.get(i).substring(0, 1).equals("\\")){
				original.set(i-1, original.get(i-1) + 
						original.get(i).substring(1));
				original.remove(i);
			}
		}
		int firstAnd = 0;
		while(contains(original,"&&")){
			for(int i = firstAnd; i < original.size(); i++){
				String ii = original.get(i);
				int index = ii.indexOf("&&");
				if(index != -1){
					original.add(ii.substring(ii.indexOf("&&")+2));
					original.set(i, ii.substring(0, ii.indexOf("&&")));
				}
			}
		}
	}
	private boolean contains(List<String> ls, String s){
		for(String str: ls) if(str.contains(s)) return true;
		return false;
	}
	
	//getters
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}

	
	/**
	 * This is a standard level.  We can write other implementations of our
	 * abstract level somewhere else.
	 * @author Adriano Hernandez
	 *
	 */
	private class STDLevel extends Level{

		public STDLevel(Scene scene) {
			super(scene);
			// TODO Auto-generated constructor stub
		}
		
	}
}
