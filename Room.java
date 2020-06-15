package chatRoom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Room{
	
	@SuppressWarnings("unused")
	private String name;
	private int limitUsers;
	private List<User> registeredUsers;
	private String creator;
	private ServerSocket socketRoom;
	private Socket socketClient;
	
	public Room(String name,int limitUsers, String creator) throws UnknownHostException, IOException {
		this.name = name;
		this.limitUsers = limitUsers;
		this.creator = creator;
		this.registeredUsers = new ArrayList<User>();
		this.socketRoom = new ServerSocket(3333,100,InetAddress.getByName("127.0.0.1"));
		System.out.println("New room "+name+ " created by "+creator +" at "+DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()));
	}
	
	public int getLimitUsers() {
		return limitUsers;
	}

	public String getName() {
		return name;
	}

	
	public void addUser(User user) {
		if(this.getLimitUsers() > this.registeredUsers.size()) {
			this.registeredUsers.add(user);
			System.out.println("New user added to "+this.getName()+" room");
		}
	}
	


	
	public void launch() {
		
		System.out.println("Room "+this.getName()+" created by "+this.getCreator()+" at "+DateFormat.getTimeInstance(DateFormat.MEDIUM).format(new Date()));
		
		System.out.println("This room users can now send messages");
		
		Thread t = new Thread(new LaunchRoom( socketClient,  socketRoom, this,  name, creator));
		
		t.start();
		
		
	
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public String getCreator() {
		return creator;
	}
	

}
