package tictactoe;
	
import javax.swing.JOptionPane;

import tictactoe.gui.GameWindow;
import tictactoe.gui.Window;

public abstract class Game {
	
	public static final int PORT = 55555;
	public static final int WIDTH = 600, HEIGHT = 600;
	public static final int FIELD_WIDTH = WIDTH/3, FIELD_HEIGHT = HEIGHT/3;
	
	public static final int NOBODY = 0, PLAYER_ONE = 1, PLAYER_TWO = 2;
	
	
	protected int[][] fields;
	
	
	private Window window;
	protected GameWindow gameWindow; // Jpanel that adds to the window (buttons etc.)
	
	protected int currentPlayer;
	protected int thisPlayer; 
	
	public Game(int thisPlayer) {
		
		this.thisPlayer = thisPlayer;
		window = new Window(this, "TicTacToe" , WIDTH, HEIGHT); //Initializing the title and dimensions of display window
		gameWindow = new GameWindow(this);
		fields = new int[3][3];
		window.add(gameWindow);
		window.setVisible(true);						// makes display window visible on screen
		currentPlayer = Game.PLAYER_ONE;
	}
	 
	protected void showWinner(int winner) {
		if(winner == Game.NOBODY) {
			JOptionPane.showMessageDialog(null, "IT IS A TIE!");
		} else {
			JOptionPane.showMessageDialog(null, "Player " + winner + " has won the game!");
		}
	}		
	protected boolean isMyTurn () {
		if (thisPlayer == currentPlayer) {
			return true;
		}
		return false;
	}
	public abstract void InputReceived(int x, int y);
	public abstract void close ();
	public abstract void packetReceived(Object object);
	
	public int[][] getFields () {		// Lets us access variables in game window
		return fields;
	}

}
