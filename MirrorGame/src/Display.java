//importing all important stuff to make this work
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * @author Patrick Zhong, Adriano Hernandez
 * @version 0.11
 * @date 7 July 2016
 * 
 * This is a display class that will display the game in a window.  It uses Java swing & awt.
 **/
public class Display extends JComponent {

	
	
	private JFrame frame;
	private Dimension screenSize;
	//the offset for x and y
	//when you display the window, it will be offset from each side of the scren by these amounts
	private int offX;
	private int offY;
	//the width of the window and the height of the window
	final private int windowWidth;
	final private int windowHeight;
	
	//constructor automatically initializes a generic window in the center of the screen
	public Display(){
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//these are optimal for 16:9, I assume -Adriano
		windowHeight = (int)(screenSize.getHeight()*4.0/5.0);
		windowWidth = (int)(windowHeight * 7.0 / 12.0);
		
		initialize();
		
	}
	//initialize the window in the middle of the screen
	public void initialize(){
		
		offX = (int)((screenSize.getWidth()-windowWidth)/2.0);
		offY = (int)((screenSize.getHeight()-windowHeight)/2.0);
		
		
		frame = new JFrame("Game");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(this);
	    
	    	this.setPreferredSize(new Dimension(windowWidth, windowHeight));
		
	    	this.setFocusable(true);
		
	    
		frame.pack();
		frame.setLocation(offX, offY);
		frame.setVisible(true);
	
	}
	
	
	
	
	
}
