package chatRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class LaunchRoom implements Runnable{

	private Room room;
	private Socket socketClient;
	private ServerSocket socketRoom;
	private String name;
	private String creator;
	
	public LaunchRoom(Socket socketClient, ServerSocket socketRoom,Room room, String name,String creator) {
		this.room = room;
		this.socketClient = socketClient ;
		this.socketRoom = socketRoom;
		this.name = name;
		this.creator = creator;
	}

	@Override
	public void run() {
		try {
			
			while(true) {
			
			socketClient = socketRoom.accept();
		
			System.out.println("Recieved entrant connection ");
			
			Thread t = new Thread(new RoomRequestHandler(socketClient,this.name,this.creator,this.room));
			t.start();
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
