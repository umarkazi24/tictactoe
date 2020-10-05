package tictactoe;

import javax.swing.JOptionPane;

import tictactoe.gui.GameWindow;

public class main {

	public static void main(String[] args) {
		int choice = Integer.parseInt(JOptionPane.showInputDialog("1 for server | 2 for client"));
		
		if(choice == 1) {
			new ServerGame();
		}else if (choice == 2) {
			new ClientGame();
		} 
	}

}
