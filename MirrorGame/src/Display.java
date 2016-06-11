import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class Display extends JComponent {

	
	
	private JFrame frame;
	Dimension screenSize;
	private int offX;
	private int offY;
	final private int windowWidth;
	final private int windowHeight;
	
	public Display(){
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowHeight = (int)(screenSize.getHeight()*4.0/5.0);
		//windowWidth = (int)screenSize.getWidth()/2;
		windowWidth = (int)(windowHeight * 7.0 / 12.0);
		
		initialize();
		
	}
	
	public static void main(String[] args){
		
		Display Display = new Display();
	
	}
	
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
