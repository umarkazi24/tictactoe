package tictactoe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tictactoe.packets.ClientPlayPacket;
import tictactoe.packets.GameEndPacket;
import tictactoe.packets.UpdatePacket;

public class ServerGame extends Game {

	private ServerSocket serverSocket;		//instance of server socket to host a new server
	private Socket socket;
	private Connection connection;
	
	public ServerGame() {			// Method to stop and wait until something connects to IP address and then returns the socket that connected
		super(Game.PLAYER_ONE);
		try {
			serverSocket = new ServerSocket(Game.PORT);
			socket = serverSocket.accept();		//This method stops and waits until something connects onto this port on this IP address and then returns socket that connected
			connection  = new Connection(this, socket); // create a new connection passing the socket for the endpoints between server and client
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void InputReceived(int x, int y) {
		if(isMyTurn()) {
			updateField(x, y);
		}
	}
	
	@Override
	public void packetReceived(Object object) {
		
		if(object instanceof ClientPlayPacket) {
			ClientPlayPacket packet = (ClientPlayPacket) object;
			
			updateField(packet.getX() , packet.getY());
		}
		
		
	}
	
	private void updateField(int x, int y) {
		if (fields[x][y] == Game.NOBODY) {
			fields[x][y] = currentPlayer;  
			
			if (currentPlayer == Game.PLAYER_ONE) {
				currentPlayer = Game.PLAYER_TWO;
			} else if(currentPlayer == Game.PLAYER_TWO) {
				currentPlayer = Game.PLAYER_ONE;
			}
			
			connection.sendPacket(new UpdatePacket(fields, currentPlayer));
			gameWindow.repaint();

			
			int winner = checkWin();
			
			if (winner != Game.NOBODY) {
				endGame(winner);
			} else {
				int emptyCount = 0;	
				
				for(int a = 0; a < 3; a++) {
					for(int b = 0; b < 3; b++) {
						if(fields[a][b] == Game.NOBODY) {
							emptyCount++; 
						}
					}
						
				}
				if (emptyCount == 9) {
					endGame(winner);
				}
			}
	}
}	
	
	private void endGame(int winner) {
		showWinner(winner);
		connection.sendPacket(new GameEndPacket(winner));
	}	
	
/* Method that is used to check if a player has won the game. 
 * Goes from player to player to see if either has won.
 * Checks each row and each column, if one row or column is filled with same player, that player wins
 * Checks the diagonals on the board to check for winner
 */
	private int checkWin() {			
		for(int player = Game.PLAYER_ONE; player <= Game.PLAYER_TWO; player++) {
			for(int y = 0; y < 3; y++) {
				int playerCount = 0;
				
				for(int x = 0; x < 3; x++) {
					if (fields[x][y] == player) {
						playerCount++;
					}
				}
				if (playerCount == 3) {
					return player;
				}
			}
			for (int x = 0; x < 3; x++) {
				int playerCount = 0;
				
				for(int y = 0; y < 3; y++) {
					if(fields[x][y] == player) {
						playerCount++;
					}
				}
				if(playerCount == 3) {
					return player;
				}
				
			}
			int playerCount = 0;
			for (int coordinate = 0; coordinate < 3; coordinate++) {
				if(fields[coordinate][coordinate] == player) {
					playerCount++;
				}
			}
			if (playerCount == 3) {
				return player;
			}
			playerCount = 0;
			
			for(int coordinate = 0; coordinate < 3; coordinate++) {
				if(fields[2 - coordinate][coordinate] == player) {
					playerCount++;
				}
			}
			
			if(playerCount == 3) {
				return player;
			}
		}
		
		return Game.NOBODY;
	}
	
	@Override
	public void close() {			
		try {
			connection.close();    	//closes the connection
			serverSocket.close();	//closes server socket
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
