package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * Class to manage the text drawn in the game
 * @author COMP2911 Project
 *
 */
public class Text {
	
	/**
	 * Method to draw the text used in a specific location in the game
	 * @param g : Graphics used to draw the jframe components of type Grpahics
	 * @param text : Test to be printed out of type String
	 * @param posX : the x co-ord of the text to be printed if type integer
	 * @param posY : the y co-ord of the text to be printed if type integer
	 * @param center : whether text is centred or not of type boolean
	 * @param c : the colour of the text printed of type Color
	 */
	public static void drawString(Graphics g, String text, int posX, int posY, boolean center, Color c)
	{
		g.setColor(c);
		int x = posX;
		int y = posY;
		FontMetrics fm = g.getFontMetrics();
		if(center)
		{
			
			x = x - fm.stringWidth(text)/2;
			y = (y - fm.getHeight()/2) + fm.getAscent();
		}
		
		g.drawString(text, x, y);
	}
}