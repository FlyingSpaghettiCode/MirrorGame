package levels;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import game.Game;
import java.util.Stack;
import sprites.Lever;
import sprites.Portal;
import sprites.Sprite;
import sprites.Wall;
import javafx.scene.Scene;
import levels.parseutil.CactusStackMapNode;
import levels.parseutil.commands.Command;
import levels.parseutil.Form;
import levels.parseutil.StringIterator;
import levels.parseutil.Symbol;
import levels.parseutil.commands.Variable;
import levels.parseutil.commands.StdLibrary;
import sounds.SoundPlayer;

/**
 * Parses a text document that tells how to build the level.
 * It also can write them.
 * 
 * This is basicallly a factory class that reads from a txt file.
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
		text = simplifyLines(lines);
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
                    interpret(lvl, parse(code), new Stack());
                return lvl;
	}
	public void initializeInterpreter()
        {
            symMap = new CactusStackMapNode(null);
            StdLibrary.load(symMap);
        }
	// Removes line comments and consolidates into single string to ease parsing.
        // Leading and trailing spaces removed. Line breaks converted to spaces.
	private String simplifyLines(List<String> ls){
                String ret = "";
		for(String i:ls)
                {
                    String a = i.trim(); // removes leading and trailing whitespace
                    a = a.substring(0, a.indexOf('#')); // removes line comments
                    if(!a.equals(""))
                        ret += " " + a;
                }
                return ret;
	}
        public void interpret(Level lvl, Object ast, Stack stack)
        {
            if(ast instanceof Symbol)
            {
                if(symMap.containsKey(((Symbol) ast).getSymbol()))
                    stack.push(symMap.get(((Symbol)ast).getSymbol()));
                else
                {
                    Variable var = new Variable(null);
                    symMap.put(((Symbol) ast).getSymbol(), var);
                    stack.push(var);
                }
            }
            else if(ast instanceof Form)
            {
                interpret(lvl, ((Form) ast).get(0), stack);
                Command command = (Command) stack.pop();
                for(int i = ((Form) ast).size() - 1; i > 0 ; i--) // puts the contents of the form in the stack so that the first element (excluding the command) is on top.
                {
                    if(command.isEager(i))
                        interpret(lvl, ((Form) ast).get(i), stack);
                    else
                        stack.push(((Form) ast).get(i));
                }
                command.eval(stack, ((Form) ast).size()-1);
            }
            else
                stack.push(ast);
        }
        public static Object parse(StringIterator code)
        {
            code.trim();
            char first = (char) code.next();
            
            
            switch(first)
            {
                case '\"':
                    return parseString(code);
                case '(':
                    Form elements = new Form();
                    do
                    {
                        elements.add(parse(code));
                    }
                    while((char)code.next()!=')');
                    return elements;
                default:
                    String symbol = first + code.next(Math.min(//tokenizes to next space or close paren
                            code.content().indexOf(' ')&0xffff,//&0xffff cpnverts to unsigned so that error (-1) >> any real value
                            code.content().indexOf(')')&0xffff));
                    try
                    {
                        return Integer.parseInt(symbol);
                    }
                    catch(NumberFormatException e)
                    {
                        try
                        {
                            return Double.parseDouble(symbol);
                        }
                        catch(NumberFormatException f)
                        {
                            return new Symbol(symbol);
                        }
                    }
                    
            }
        }
	
        public static String parseString(StringIterator code)
        {
            String pstring = "";
            char next;
            do
            {
                pstring += code.next(Math.min( // gets the string up to the next escape or quote
                        code.content().indexOf("\\")&0xffff, // &0xffff converts to unsigned; this causes error (-1) to be >> than any value
                        code.content().indexOf("\"")&0xffff));
                next = (char) code.next();
                if(next == '\\')
                {
                    switch((char)code.next())
                    {
                        case '\"':
                            pstring += '\"';
                            break;
                        case '\\':
                            pstring += '\"';
                            break;
                    }
                }
                
            }
            while(next != '\"');
            
            return pstring;
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
