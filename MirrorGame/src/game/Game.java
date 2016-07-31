package game;

import java.util.ArrayList;

import levels.Level;

/**
 * Game is handled in this class.
 * @author Adriano
 *
 */
public class Game {

	private Environment environment;
	private ArrayList<Level> playerLevels; //levels created by the player
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

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public ArrayList<Level> getPlayerLevels() {
		return playerLevels;
	}

	public void setPlayerLevels(ArrayList<Level> playerLevels) {
		this.playerLevels = playerLevels;
	}

}
