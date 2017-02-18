package levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import java.util.Stack;
import javafx.scene.Scene;
import levels.interpreter.CactusStackMapNode;
import levels.interpreter.commands.Command;
import levels.interpreter.Interpreter;
import levels.interpreter.parse.Parser;
import levels.interpreter.parse.StringIterator;
import levels.interpreter.commands.StdLibrary;

/**
 * Parses a text document that tells how to build the level.
 * It also can write them.
 * 
 * This is basically a factory class that reads from a txt file.
 * The language is interpreted as a list of additions to the class that are analysed by line
 * sequentially.  More syntax information is on another dock in this folder labelled "SyntaxInformation"
 * @author Adriano
 * @version 0.3
 * @date 5 September 2016
 *
 */
public class LevelReader implements LevelFactory{

	//basic shit
	private String PATH;
        private String text;
	private Scene scene;
	private Game main;
        private CactusStackMapNode<String, Command> symMap;
	//PATH CAN GO ANYWHERE ON COMP
	public LevelReader(String PATH) {
		this.PATH = PATH;
                List<String> lines = new ArrayList<String>();
		//get the lines of text from the level file
		try {lines = docText();} 
		catch (IOException e) {e.printStackTrace();}
		text = Parser.simplifyLines(lines);
                initializeInterpreter();
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
		StringIterator code = new StringIterator(text);
                while(!code.hasNext())
                    Interpreter.interpret(Parser.parse(code), new Stack(), symMap);
                return lvl;
	}
	public void initializeInterpreter()
        {
            symMap = new CactusStackMapNode(new StdLibrary(null));
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
