/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package levels;

/**
 *
 * @author Edgar Lin
 */
public interface LevelFactory {
	// Returns a ready to play level.
	public Level createLevel();	
}
