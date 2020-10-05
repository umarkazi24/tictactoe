package tictactoe.packets;

import java.io.Serializable;

public class ClientPlayPacket implements Serializable {

	
	private static final long serialVersionUID = 3242996705550731449L;
	
	private int x;
	private int y;
	
	public ClientPlayPacket(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
	
}
