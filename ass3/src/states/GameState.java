package states;

import java.awt.Graphics;

import main.Window;
import game.Level;

/**
 * Class to manage the state of the game
 * @author Daniel Al Mouiee
 *
 */
public class GameState extends State{
	
	private Level level;
	
	/**
	 * Contructor
	 * @param window : the main jframe window of type Window
	 */
	public GameState(Window window) {
		super(window);
	}
	
	/**
	 * Method to update the game's level
	 */
	public void update() {
		level.update();
	}

	/**
	 * Method to draw the graphics of the game's level
	 */
	public void render(Graphics g) {
		level.render(g);
	}
	
	/**
	 * Method to set the game's level
	 * @param level
	 */
	public void setLevel(Level level){
		this.level = level;
	}
	
	public Level getLevel(){ return level;}
}
