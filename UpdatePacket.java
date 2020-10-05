package tictactoe.packets;

import java.io.Serializable;

//Keeps track of the current state of the game

public class UpdatePacket implements Serializable {

	private static final long serialVersionUID = 4192497741994737923L;

	private int[][]fields;
	
	private int currentPlayer;

	public UpdatePacket(int[][] fields, int currentPlayer) {
		super();
		this.fields = fields;
		this.currentPlayer = currentPlayer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int[][] getFields() {
		return fields;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	

	
}
