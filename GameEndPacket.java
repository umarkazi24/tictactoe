package tictactoe.packets;

import java.io.Serializable;

public class GameEndPacket implements Serializable{

	private static final long serialVersionUID = 8701980859464032611L;

	private int winner;
	
	public GameEndPacket(int winner) {
		this.winner = winner;
	}

 
	public int getWinner() {
		return winner;
	}
	
	

}
