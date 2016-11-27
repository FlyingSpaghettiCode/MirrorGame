package levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import sprites.Lever;
import sprites.Portal;
import sprites.Sprite;
import sprites.Wall;
import javafx.scene.Scene;
import sounds.SoundPlayer;

/**
 * Parses a text document that tells how to build the level.
 * It also can write them.
 * 
 * This is basicallly a factory class that reads from a txt file.
 * The language is interpreted as a list of additions to the class that are analysed by line
 * sequentially.  More syntax information is on another dock in this folder labelled "SynaxInformation"
 * @author Adriano
 * @version 0.3
 * @date 5 September 2016
 *
 */
public class LevelReader implements LevelFactory{

	//basic shit
	private String PATH;
	private Scene scene;
	private Game main;
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
	
	//return true if ls contains the string s (can be a substring)
	private boolean contains(List<String> ls, String s){
		for(String str: ls) if(str.contains(s)) return true;
		return false;
	}
	//creates a standard level
	public Level createLevel(){
		return editLevel(new StandardLevel(scene, main));
	}
	public Level editLevel(Level lvl){
		List<String> lines = new ArrayList<String>();
		//get the lines of text from the level file
		try {lines = docText();} 
		catch (IOException e) {e.printStackTrace();}
		simplifyLines(lines);
		for(String line: lines){
			if(line.indexOf("\n") != -1){	//to avoid confusing paths and the like
				readLine(lvl,line.substring(0,line.indexOf("\n")));
			}
			else readLine(lvl,line);
		}
		return lvl;
	}
	//simplifies the lines into easier code for the machine to read
	//moves includes to the front
	//turns multi lined commands into single lines
	
	private void simplifyLines(List<String> ls){
		for(int i = ls.size()-1; i >= 0; i--){
			//REMOVE COMMENTS
			if(ls.contains("#"))
				ls.set(i, ls.get(i).substring(0, ls.get(i).indexOf("#")));// take out comments
			//REMOVE MULTI LINED STATEMENTS
			if(i > 0 && ls.get(i).substring(0, 1).equals("\\")){
				ls.set(i-1, ls.get(i-1) + ls.get(i));
				ls.remove(i);
			}
			//REMOVE DUAL STATEMENTS
			if(ls.get(i).contains("&&")){
				String nl = ls.get(i).substring(ls.get(i).indexOf("&&")+2);
				ls.set(i, ls.get(i).substring(0, ls.get(i).indexOf("&&")));
				ls.add(i,nl);
			}
			
			
			//REMOVE EMPTY LINES
			if(ls.get(i).length() < 1)
				ls.remove(i);
		}
	}
	
	//will read a line and analyze it (this is a line by line script)
	private void readLine(Level lvl, String line){
		if(line.contains("include")){
			//this is crude, but should be the path
			String include_PATH = line.substring(line.indexOf("include " + 8)); //get the path
			LevelReader alt_reader = new LevelReader(include_PATH); //read from the alt path
			lvl = alt_reader.editLevel(lvl); //edit the level 
			return;
		}
		else if(line.contains("add")){
			
			if(line.contains("wall")){
				Wall w = new Wall(0, 0, 50, 50, true, false, false, "red");
				
			}
			if(line.contains("portal")){
				String data = line.substring(line.indexOf("portal")+6);
				
				//Portal p = new Portal(0, 0, false, false, false, p, false);
				
			}
			if(line.contains("lever")){
				String data = line.substring(line.indexOf("lever")+5);
				//Lever lv = new Lever(0, 0, false, false, false, lvl);
			}
			else if(line.contains("playertree")){
				
			}
			if(line.contains("button")){
				
			}
			else if(line.contains("sound")){
				lvl.getSounds().add(new SoundPlayer(
						line.substring(line.indexOf("sound ")+6)));
			}
		}
		else{
			return;
		}
	}
	
	/////////////////////////////////////////////////////
	//getters
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}

	
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}


	public Game getMain() {
		return main;
	}
	public void setMain(Game main) {
		this.main = main;
	}


	/**
	 * This is a standard level.  We can write other implementations of our
	 * abstract level somewhere else.
	 * @author Adriano Hernandez
	 *
	 */
	private class StandardLevel extends Level{

		public StandardLevel(Scene scene, Game main) {
			super(scene, main);
		}

	}
}
