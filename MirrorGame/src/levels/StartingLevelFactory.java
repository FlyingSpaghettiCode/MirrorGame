/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

import java.util.ArrayList;

/**
 *
 * @author Edgar Lin
 */
public class StartingLevelFactory implements LevelFactory {

	@Override
	/**
	 * Currently creates an empty level.;
	 */
	public Level createLevel() {
		StartingLevel ret = new StartingLevel();
		return ret;
	}
	
	private class StartingLevel extends Level {
		public StartingLevel(){
			sprites = new ArrayList<>();
		}

	}
	
}
