package states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.sound.sampled.*;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import game.Level;
import game.LevelGen;
import gfx.Assets;
import gfx.Text;
import main.Window;
import ui.Button;
import ui.Click;
import java.io.*;
import sun.audio.*;
/**
 * Class to manage the menu screen
 * @author COMP2911 Project
 *
 */
public class MenuState extends State{
	
	private Button button;
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	/**
	 * Constructor
	 * @param window : the window frame of the program
	 */
	public MenuState(Window window){
		super(window);
		playMusic();
		
		buttons.add(new Button("CLASSIC MODE", Window.WIDTH/2, Window.HEIGHT/2 - 100, new Click(){

			
			public void onClick() {
				State.currentState = window.getLevelSelectorState();
			}}, Assets.font48));
		
		buttons.add(new Button("ADVENTURE MODE", Window.WIDTH/2, Window.HEIGHT/2 , new Click(){

			
			@Override
			public void onClick() {
				LevelGen.generate(8,8);
				window.setAdv(true);
				State.currentState = window.getGameState();
			}}, Assets.font48));

		buttons.add(new Button("EXIT", Window.WIDTH/2, Window.HEIGHT/2 + 100, new Click(){

			
			public void onClick() {
				System.exit(1);
			}}, Assets.font48));
	}
	
	/**
	 * Method to update the menu screen
	 */
	public void update() {
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).update();
	}

	/**
	 * Method to render the graphics of the menu screen
	 */
	public void render(Graphics g) {
		g.setFont(Assets.font48);
		Text.drawString(g, "WAREHOUSE BOSS", Window.WIDTH/2, Window.HEIGHT/2 - 200, true, Color.DARK_GRAY);
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(g);
	}

	/**
	 * Method to play music
	 */
	public void playMusic()
	{
		try
		{
			InputStream in = new FileInputStream("res/music/Pewdiepie_Tuber_Simulator_-_Main_Theme_Oscilloscop.wav");
		    Clip clip = AudioSystem.getClip();
		    InputStream bufferedIn = new BufferedInputStream(in);
	        AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
	        clip.open(ais);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}