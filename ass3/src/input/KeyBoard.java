package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class to manage and handle keyBoard events
 * @author COMP2911 Project
 *
 */
public class KeyBoard implements KeyListener{
	
	private boolean[] keys;
	
	public static boolean UP, LEFT, RIGHT, DOWN;
	
	/**
	 * Contructor
	 */
	public KeyBoard(){
		keys = new boolean[256];
		UP = false;
		DOWN = false;
		RIGHT = false;
		LEFT = false;
	}
	
	/**
	 * Method to update after listening to a keyevent
	 */
	public void update(){
		UP = keys[KeyEvent.VK_UP];
		LEFT = keys[KeyEvent.VK_LEFT];
		RIGHT = keys[KeyEvent.VK_RIGHT];
		DOWN = keys[KeyEvent.VK_DOWN];
	}
	
	/**
	 * Method to handle key pressed events 
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
	
	/**
	 * Method to handle key released events
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/**
	 * Method to handle keyTyped events
	 */
	public void keyTyped(KeyEvent arg0) {
		
	}

}
