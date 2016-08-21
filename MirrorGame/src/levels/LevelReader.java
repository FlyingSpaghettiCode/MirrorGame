package levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.Main;
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
	public Level createLevel(Scene scene, Main main){
		Level level;
		List<String> lines;
		try {
			lines = docText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // failure
		}
		if(lines.size() == 0) return null; //there is nothing there
		//level creation code from txt
		simplify(lines);
		if(!lines.get(0).contains("type")) throw new RuntimeException("specify type in level file");
		//we analyse this seperately
		String currentLine = lines.get(0);
		{
			int i;
			for(i = currentLine.indexOf("type")+5; currentLine.substring(i, i+1) == " "; i++);
			if(currentLine.substring(i,i+1).equals("\n")) throw new RuntimeException("specify type");
			
			if(i == currentLine.indexOf("standard"))level = new STDLevel(scene, main);
			else return null;
		}
		//analyse the 
		for(int line = 1; line < lines.size(); line++){
			currentLine = lines.get(line);
			readLine(currentLine);
		}
		//return
		return level;
	}
	//read one line analysing the command and such
	private void readLine(String line){
		//break into tokens
		List<String> tokens = tokenize(line);
		
		//analyse the line
		
	}
	private List<String> tokenize(String str){
		List<String> ls = new ArrayList<String>();
		for(int i = 0; i < str.length(); i++){
			if(!str.substring(i,i+1).equals(" ") && !str.substring(i,i+1).equals("/n")){
				String s = "";
				while(!str.substring(i,i+1).equals(" ") && !str.substring(i,i+1).equals("/n")){
					s+= str.substring(i,i+1);
					i++;
				}
				ls.add(s);
			}
		}
		return ls;
	}
	//return without any \ or && or anything like that
	//(just break up into one command per line format for ease of parsing)
	//won't break on null, but won't do anything
	//it ignores empty lines and ignores caps
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
		for(int i = original.size()-1; i >= 0; i--){
			original.set(i, original.get(i).toLowerCase());
			if(original.get(i).equals("")) original.remove(i);
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

		public STDLevel(Scene scene, Main main) {
			super(scene, main);
			// TODO Auto-generated constructor stub
		}
		
	}
}
