package chatRoom;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;

public class RoomRequestHandler implements Runnable{

	
	private PrintWriter writer;
	private BufferedInputStream bis;
	private boolean closeRoom = false;
	private String creator;
	private String name;
	private String response;
	private Room room;
	
	
	/* print colors  */
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RESET = "\u001B[0m";

	
	public RoomRequestHandler(Socket socketClient,String name, String creator,Room room) throws IOException {
		this.writer = new PrintWriter(socketClient.getOutputStream());
		this.bis = new BufferedInputStream(socketClient.getInputStream());
		this.name = name;
		this.creator = creator;
		this.room = room;
		
	}
	
	@Override
	public void run() {
		System.out.println("ROOM "+this.name+" created by "+this.creator);
		while(!closeRoom) {
			
			
			try {
				response  = read();
				String[] parts = response.split(" ");
				String[] message = new String[parts.length-2];
				System.arraycopy(parts, 0, message, 0, 3);
				System.out.println(ConsoleColors.GREEN+"Message "+String.join(" ", message)+" in the room "+parts[parts.length-2]+" by "+parts[parts.length-1]+ConsoleColors.RESET);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}	
	}
	
	
	private String read() throws IOException {
		String response = "";
		int stream;
		byte[] b = new byte[8195];
		stream = bis.read(b);
		response = new String(b, 0, stream);		
		return response;
	}
	
	private boolean userInRoom(String pseudo) {
		
		Iterator<User> it = this.room.getRegisteredUsers().iterator();
		
		while(it.hasNext()) {
			if(it.next().getPseudo().equals(pseudo)) {
				return true;
			}
		}
		return false;
	}



}
