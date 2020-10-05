package tictactoe;

import java.io.IOException;
import java.net.Socket;

import tictactoe.packets.ClientPlayPacket;
import tictactoe.packets.GameEndPacket;
import tictactoe.packets.UpdatePacket;

public class ClientGame extends Game {

	private Socket socket;
	
	private Connection connection;
	
	
	public ClientGame() {
	super(Game.PLAYER_TWO);
	
		try {
			socket = new Socket("localhost" , Game.PORT); //pass localhost as argument for current computer to access along with port number
			connection = new Connection(this, socket);	// create a new connection passing the socket for the endpoints between server and client
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	
	}

	@Override
	public void InputReceived(int x, int y) {
		if (isMyTurn()) {
			connection.sendPacket(new ClientPlayPacket(x, y));
		}
	}


	@Override
	public void packetReceived(Object object) {		//method to update the current game state
		if(object instanceof UpdatePacket) {	
			UpdatePacket packet = (UpdatePacket) object;
			 
			fields = packet.getFields();
			currentPlayer = packet.getCurrentPlayer();
		}else if(object instanceof GameEndPacket) {
			GameEndPacket packet = (GameEndPacket) object;
			showWinner(packet.getWinner());
		}
		
		gameWindow.repaint();
	}
	
	@Override
	public void close() {
		try {
			connection.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
