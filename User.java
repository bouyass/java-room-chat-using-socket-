package chatRoom;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("rawtypes")
public class User implements Comparable {

	private String pseudo;

	@SuppressWarnings("unused")
	private boolean creator = false;
	private String message = "";
	private Socket socketUser;
	private PrintWriter writer;
	private BufferedInputStream bis;

	public User(String pseudo, boolean creator) throws UnknownHostException, IOException {
		this.pseudo = pseudo;
		this.creator = creator;
	}

	@Override
	public int compareTo(Object obj) {
		User user = (User) obj;
		if (this.getPseudo().equals(user.getPseudo())) {
			return 1;
		}
		return 0;
	}

	public boolean isCreator() {
		return creator;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void chat(String roomName, String messageToSend) throws UnknownHostException, IOException {
		this.socketUser = new Socket(InetAddress.getByName("127.0.0.1"),3333);
		this.writer = new PrintWriter(socketUser.getOutputStream());
		this.bis = new BufferedInputStream(socketUser.getInputStream());
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
			
					
					message= messageToSend+" "+pseudo+" "+roomName;
					
					writer.write(message);
					writer.flush();
					
					//socketUser.close();
					
					
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}

		});
		
		t.start();
	}

}
