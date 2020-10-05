	package tictactoe;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import tictactoe.Game;

public class Connection implements Runnable {		//implements runnable so we can use threads

	private ObjectOutputStream outputStream;	//initialization of output stream object that will write to its destination
	private ObjectInputStream inputStream; 		//initialization of input stream object to get data that has been written from the server
	private boolean running;					//This is what our loop checks while running
	
	private Game game;
	
	public Connection(Game game, Socket socket) {
		this.game = game;
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new Thread (this).start();			
		
	}
	
	@Override
	public void run() {					// method to receive the object that has been written
	
		running = true;
		while(running) {
			try {
				Object object = inputStream.readObject();	//input stream gets the objects and reads it
				game.packetReceived(object);				// game receives the packets
				
				
			} catch(EOFException | SocketException e) {
				running = false;
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public void sendPacket(Object object) {
		
		
		try {
			outputStream.reset(); 			//Reset the stream to make sure the same object isn't being used twice
			outputStream.writeObject(object);	//pass in the object of the output stream that we want to write
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws IOException {
		
		running = false;
		inputStream.close();
		outputStream.close();
	}

}
