package chatRoom;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ChatManager{

	@SuppressWarnings("unused")
	private int limitNbrRooms;
	private Map<Integer, Room> listRooms;
	private int nbrRooms = 0;
	private boolean isRunning = true;
	private PrintWriter writer;

	
	

	public ChatManager(int limitNbrRooms) throws UnknownHostException, IOException {
		this.limitNbrRooms = limitNbrRooms;
		this.listRooms = new HashMap<Integer, Room>();
	}
	
	public int getNbrRooms() {
		return limitNbrRooms;
	}

	
	
	public Map<Integer, Room> getListRooms() {
		return listRooms;
	}

	public void addUserToRoom(String name,User user) {
		if(this.listRooms.size() > 0) {
			for(Map.Entry<Integer, Room> entry : this.listRooms.entrySet()) {
				if(entry.getValue().getName().equals(name)) {
					entry.getValue().addUser(user);
				}
			}
		}
	}


	
	

}
