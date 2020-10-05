package tictactoe.gui;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import tictactoe.Game;

//initializes methods and variables to utilize the display window

public class Window extends JFrame { 			//Extend a class that creates a window
	
	private static final long serialVersionUID = -8226876393710312161L;
	
	private Game game;		//instance of game

	public Window(Game game, String title, int width, int height) {		//Super constructor of JFrame
		super(title);							// title of window
		this.game = game;
		setResizable(false);					// window can be resized
		getContentPane().setPreferredSize(new Dimension(width, height)); //Set preferred size for display window
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // to close window from program
		setLocationRelativeTo(null); 				// sets location relative to no other components
		addWindowListener(new Listener());
		
		
	}
	
	class Listener extends WindowAdapter{			//Checks when window is about to close 
		
		@Override
		public void windowClosing(WindowEvent e) {
			game.close();
		}
	}

}
