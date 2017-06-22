package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class to manage and handle mouse events
 * @author Daniel Al Mouiee
 *
 */
public class MouseManager extends MouseAdapter{
	
	public static int x, y;
	public static boolean left;
	
	/**
	 * Contructor
	 */
	public MouseManager(){
		x = 0;
		y = 0;
	}
	
	/**
	 * Method to handle mouse pressed events
	 */
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = true;
	}
	
	/**
	 * Method to handle mouse released events
	 */
	public void mouseReleased(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = false;
	}
	
	/**
	 * Method to handle mouse dragged events
	 */
	public void mouseDragged(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
	
	/**
	 * Method to handle mouse moved events
	 */
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
	
}
