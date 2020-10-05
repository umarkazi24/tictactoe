package tictactoe.gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import game.res.Resources;
import tictactoe.Game;

/*This class will render and receive input*/

public class GameWindow extends JPanel {

	private static final long serialVersionUID = -6667207052705398658L;
	
	private Game game;

	public GameWindow(Game game) {
		  this.game = game;
		  addMouseListener(new Input());
		  
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2D = (Graphics2D) g;	//import graphics class to use 2D Graphics
		g2D.setStroke(new BasicStroke(10)); //import Basic Stroke to increase line attributes such as width, patter, etc.
		
		for (int x = Game.FIELD_WIDTH; x <= Game.FIELD_WIDTH * 2; x += Game.FIELD_WIDTH) { //creates the vertical lines on the display window
			g2D.drawLine(x, 0, x, Game.HEIGHT);
		}
		for (int y = Game.FIELD_HEIGHT; y <= Game.FIELD_HEIGHT * 2; y += Game.FIELD_HEIGHT) { //creates horizontal lines on display window
			g2D.drawLine(0, y, Game.WIDTH, y);
		} 
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int field = game.getFields()[x][y];
				if (field !=Game.NOBODY) {
					g2D.drawImage(Resources.letters[field - 1], x * Game.FIELD_WIDTH, y * Game.FIELD_HEIGHT, Game.FIELD_WIDTH, 
					Game.FIELD_HEIGHT, null);	
				}
			}
		}
	}
	class Input extends MouseAdapter{
		
		@Override 
		public void mousePressed(MouseEvent e) {	// method called every time mouse is clicked
			
			if(e.getButton() == MouseEvent.BUTTON1) {
				game.InputReceived(e.getX() / Game.FIELD_WIDTH, e.getY() / Game.FIELD_HEIGHT); //To position based on what field was clicked instead of pixels, e.get is divided by Game
			}
		}
	}
	
}
