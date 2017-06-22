package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import gfx.Assets;
import gfx.Text;
import input.MouseManager;
import main.Window;
import game.Level;
import ui.Button;
import ui.Click;

/**
 * Class to manage the Level selector page of the program
 * @author 
 *
 */
public class LevelSelectorState extends State {
	private final int DOUBLETILESIZE = 64;
	static Level[] levels = new Level[31];
	private final int xOffset = (Window.WIDTH - DOUBLETILESIZE*6)/2;
	private final int yOffset = (Window.HEIGHT - DOUBLETILESIZE*5)/2;
	
	private Button back;

	public static boolean adv;
	/**
	 * Contructor
	 * @param window : the window jframe for the game of type Window
	 */
	public LevelSelectorState(Window window) {
		super(window);
		try {
		    FileOutputStream fos = new FileOutputStream("SavedLevels.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos);   
		    oos.writeObject(levels); // write MenuArray to ObjectOutputStream
		    oos.close(); 
		} catch(Exception ex) {
		    ex.printStackTrace();
		}
		adv = window.getAdv();
		for(int i = 0; i < levels.length; i++){
			if(!adv) levels[i] = loadLevel("/levels/"+i+".txt");
			else if(adv){
				i = 30;
				loadLevel("res/levels/30.txt");
			}
		}
		back = new Button("BACK", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			/**
			 * Method to manage mouse click events
			 */
			public void onClick() {
				State.currentState = window.getMenuState();
			}
			
		}, Assets.font30);
		
		
	}
	
	/**
	 * Method to update level selector
	 */
	public void update(){
		back.update();
	}
	
	/**
	 * Method to render Graphics of level selector
	 */
	public void render(Graphics g){
		back.render(g);
		int counter = 1;
		g.setFont(Assets.font30);
		if(adv){
			State.currentState = window.getGameState();
		}else if(!adv){
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 6; j++){
					Rectangle bounds = new Rectangle(xOffset + j*DOUBLETILESIZE,
							yOffset + i*DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
					if(bounds.contains(MouseManager.x, MouseManager.y)){
						if(MouseManager.left && levels[counter-1].isSolved()){
							((GameState)window.getGameState()).setLevel(levels[counter-1]);
							State.currentState = window.getGameState();
						}
						g.drawImage(Assets.outline2, bounds.x, bounds.y, null);
						if(levels[counter-1].isSolved())
							Text.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.RED);
						else
							Text.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
									yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.RED);
					}else{
						g.drawImage(Assets.outline, bounds.x, bounds.y, null);
						if(levels[counter-1].isSolved())
							Text.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.GREEN);
						else
							Text.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + j*DOUBLETILESIZE,
									yOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE, true, Color.BLUE);
					}
					counter ++;
				}
			}
		}
		
	}
	
	/**
	 * Method to load level 
	 * @param path: path of level to be loaded of type String
	 * @return: level loaded
	 */
	public Level loadLevel(String path){
		
		String file = loadFileAsString(path);
		String[] numbers = file.split("\\s+");
		int cols = parseInt(numbers[0]);
		int rows = parseInt(numbers[1]);
		int player_col = parseInt(numbers[2]);
		int player_row = parseInt(numbers[3]);
		int[][] maze = new int[rows][cols];
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < cols; col++)
				maze[row][col] = parseInt(numbers[(col + (row*cols)) + 4]);
		
		return new Level(maze, player_row, player_col, this);
	}
	
	/**
	 * Method to return levels
	 * @return levels 
	 */
	public Level[] getLevels()
	{
		return levels;
	}
	
	/**
	 * Method to load a file  
	 * @param path the path of a file to be loaded as a string
	 * @return the file as a string of type String
	 */
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		InputStream in;
		String temp;
		if(path.substring(0,3).equals("res/")){
			temp = path.substring(4,path.length()); 
			path = temp;
		}
		try{
			in = LevelSelectorState.class.getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while((line = br.readLine()) != null){
				builder.append(line+ "\n");
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	/**
	 * Method to convert String to a integer
	 * @param numero : String to be converted to an integer
	 * @return the string-converted integer of type int
	 */
	public static int parseInt(String numero){
		try{
			return Integer.parseInt(numero);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public Level getLevelRand(){
		Level rndlvl = loadLevel("/levels/30.txt");
		return rndlvl;
	}

}
