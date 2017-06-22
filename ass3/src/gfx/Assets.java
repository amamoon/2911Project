package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Level;

/**
 * Class to manage the image resources for displaying the different entities on the map 
 * @author COMP2911 Project
 *
 */
public class Assets {
	
	public static Image playerLeft, playerBack, playerRight, PlayerFront;
	
	public static Image floor, floor2, wall, boxOn, boxOff, spot, outline, outline2;
	
	public static Font font48;
	public static Font font30;
	public static Font font22;
	
	/**
	 * Method to initiate the images of different entities
	 */
	public static void init()
	{
		playerLeft = loadImage("/player/mouseleft.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerBack = loadImage("/player/mouseup.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		PlayerFront = loadImage("/player/mousedown.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerRight = loadImage("/player/mouseight.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		floor = loadImage("/blocks/empty.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor2 = loadImage("/blocks/bg2.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		wall = loadImage("/blocks/wall.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOn = loadImage("/blocks/crate.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOff = loadImage("/blocks/crate.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		spot = loadImage("/blocks/spot.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		outline = loadImage("/blocks/outline.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		outline2 = loadImage("/blocks/outline2.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		
		font48 = loadFont("res/fonts/square.ttf", 48);
		font22 = loadFont("res/fonts/square.ttf", 22);
		font30 = loadFont("res/fonts/square.ttf", 30);
		
	}
	
	/**
	 * Method to assign buffered images to the entities depending on their directory path
	 * @param path : the url string of an image of type String
	 * @return a buffered image of an entity of type BufferedImage
	 */
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Method to load the fonts used in the game
	 * @param path : the url string of an image of type String
	 * @param size : the size of the font used of type integer
	 * @return the font used for a specific string of type Font
	 */
	public static Font loadFont(String path, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
