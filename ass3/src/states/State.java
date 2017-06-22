package states;

import java.awt.Graphics;
import java.io.Serializable;

import main.Window;

/**
 * Class to manage the states of the game
 * @author Daniel Al Mouiee
 *
 */
public abstract class State implements Serializable {
	
	public static State currentState = null;
	
	public Window window;
	
	/**
	 * Constructor
	 * @param window: the window frame of the program of type Window
	 */
	public State(Window window){
		this.window = window;
	}
	
	/**
	 * Method to update the state screens
	 */
	public abstract void update();
	
	/**
	 * Method to render the graphics of the state screens
	 * @param g : the graphics of a state of type Graphics
	 */
	public abstract void render(Graphics g);
}
