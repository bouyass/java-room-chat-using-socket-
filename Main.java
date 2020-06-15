package chatRoom;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main {
	
	
	public static void createRoom(ChatManager chatManager, User user, String name, int limitUsers) throws UnknownHostException, IOException {
		if (user.isCreator()) {
			if (chatManager.getNbrRooms() > chatManager.getListRooms().size()) {
				Room randRoom = new Room(name,limitUsers,user.getPseudo());
				randRoom.addUser(user);
				chatManager.getListRooms().put(chatManager.getNbrRooms(), randRoom);
				randRoom.launch();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		
		ChatManager chatManager = new ChatManager(10);	
		
		User user1 = new User("lyes", true);
		User user2 = new User("karim", false);
		User user3 = new User("rachid", false);
		User user4 = new User("fafou", false);
		
		
		createRoom(chatManager, user1, "sport", 20);
		
		
		
		chatManager.addUserToRoom("sport", user2);
		chatManager.addUserToRoom("sport", user3);
		chatManager.addUserToRoom("sport", user4);
		
		
		
		
		user1.chat("sport","Hi i'm lyes");
		user2.chat("sport","Hi i'm karim");
		user3.chat("sport","Hi i'm rachid");
		user4.chat("sport","Hi i'm fafou");
		
		
		
		

	}

}
