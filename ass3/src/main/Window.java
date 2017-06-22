package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.Serializable;

import javax.swing.JFrame;

import gfx.Assets;
import input.KeyBoard;
import input.MouseManager;
import game.Level;
import game.LevelGen;
import states.GameState;
import states.LevelSelectorState;
import states.LoadingState;
import states.MenuState;
import states.State;

/**
 * Class to initialise the JFrame for the program
 * @author COMP2911 Project
 *
 */
public class Window extends JFrame implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	
	private GameState gameState;
	private LevelSelectorState levelSelectorState;
	private MenuState menuState;
	private LoadingState loadingState;
	
	private KeyBoard keyBoard;
	private MouseManager mouseManager;
	public boolean adventure = false;
	
	/**
	 * Constructor
	 */
	public Window()
	{
		setTitle("WAREHOUSE BLOCKS");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		canvas = new Canvas();
		keyBoard = new KeyBoard();
		mouseManager = new MouseManager();
		
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		addMouseMotionListener(mouseManager);
		addMouseListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addKeyListener(keyBoard);
		setVisible(true);
	}
	
	/**
	 * Main method to start the program
	 * @param args
	 */
	public static void main(String[] args) {
		new Window().start();
	}
	
	/**
	 * Method to update the current state of the game
	 */
	private void update(){
		if(State.currentState instanceof GameState)
			keyBoard.update();
		
		if(State.currentState != null)
			State.currentState.update();
	}

	/**
	 * Method to draw the window frame 
	 */
	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//-----------------------
		
		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < Window.WIDTH/Level.TILESIZE + 1; i++)
			for(int j = 0; j < Window.HEIGHT/Level.TILESIZE + 1; j++)
				g.drawImage(Assets.floor2, i*Level.TILESIZE, j*Level.TILESIZE, null);
		
		if(State.currentState != null)
			State.currentState.render(g);
	
		g.dispose();
		bs.show();
	}
	
	/**
	 * Method to initialise all the other states of the program
	 */
	private void init()
	{
		Assets.init();
		menuState = new MenuState(this);
		gameState = new GameState(this);
		loadingState = new LoadingState(this);
		levelSelectorState = new LevelSelectorState(this);
		State.currentState = loadingState;
	}
	
	
	/**
	 * Method to run the program
	 */
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1)
			{		
				update();
				draw();
				delta --;
				frames ++;
			}
			if(time >= 1000000000)
			{
				frames = 0;
				time = 0;
			}
		}
		
		stop();
	}
	
	/**
	 * Method to initiate a new thread
	 */
	private void start(){
		
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
	 * Method to stop a thread
	 */
	private void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to return gamestate
	 * @return game state of type GameState
	 */
	public State getGameState(){
		if(adventure){
			LevelGen.generate(8,8);
			gameState.setLevel(levelSelectorState.loadLevel("/levels/30.txt"));
		}
		return gameState;
	}
	/**
	 * Method to return the level selector state
	 * @return level selector state of type LevelSelectorState
	 */
	public State getLevelSelectorState(){
		return levelSelectorState;
	}
	
	/**
	 * Method to return menu state
	 * @return menu state if type MenuState
	 */
	public State getMenuState(){
		return menuState;
	}
	public void setAdv(boolean tf){ adventure = tf;}
	public boolean getAdv(){return adventure;}
	
	
}