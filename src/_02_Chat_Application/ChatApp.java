package _02_Chat_Application;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import _00_Click_Chat.gui.ButtonClicker;
import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp {
	Server server;
	String severInput;
	Client client; 
	String clientInput;
	boolean messageSent;
	
public static void main(String[] args) {
	new ChatApp(); 
}
	
	public ChatApp(){
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Chat", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			System.out.println("Sever created");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			if(messageSent == false){
				severInput = JOptionPane.showInputDialog("Send a message:");
				server.sendMessage(severInput);
				messageSent = true;
			}else{
				severInput = JOptionPane.showInputDialog(clientInput);
				server.sendMessage(severInput);
				messageSent = true;
			}
			
			server.start();
			
		}else{
			String ipSTR = JOptionPane.showInputDialog("Enter IP address: ");
			String portSTR = JOptionPane.showInputDialog("Enter Port Number: ");
			int port = Integer.parseInt(portSTR);
			client = new Client(ipSTR, port);
			System.out.println("Client created");
			if(messageSent == false){
				clientInput = JOptionPane.showInputDialog("Send a message:");
				client.sendMessage(clientInput);
				messageSent = true;
			}else{
				clientInput = JOptionPane.showInputDialog(severInput);
				client.sendMessage(clientInput);
				messageSent =true;
			}
			
			client.start();
			
		}
		
	}
	
	
}