package _02_Chat_Application;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Server {
	private int port;

	private ServerSocket socket;
	private Socket kinect;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Server(int port) {
		this.port = port;
	}

	public void start(){
		try {
			socket = new ServerSocket(port, 100);

			kinect = socket.accept();

			os = new ObjectOutputStream(kinect.getOutputStream());
			is = new ObjectInputStream(kinect.getInputStream());

			os.flush();

			while (kinect.isConnected()) {
				try {
					
					System.out.println(is.readObject());
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "Connection Lost");
					System.exit(0);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage() {
		try {
			if (os != null) {
				os.writeObject(ChatApp.message);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
