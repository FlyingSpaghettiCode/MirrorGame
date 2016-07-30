package game;

import levels.Level;

/**
 * Game is handled in this class.
 * @author Adriano
 *
 */
public class Game {

	private Level[] standardLevels;
	private Level[] menus; //levels such as the main menu, settings etcetera
	
	public Game() 
	{
		// TODO Auto-generated constructor stub
	}

	public Level[] getStandardLevels() {
		return standardLevels;
	}

	public void setStandardLevels(Level[] standardLevels) {
		this.standardLevels = standardLevels;
	}

	public Level[] getMenus() {
		return menus;
	}

	public void setMenus(Level[] menus) {
		this.menus = menus;
	}

}
