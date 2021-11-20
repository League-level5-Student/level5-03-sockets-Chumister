package _02_Chat_Application;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame{
	static JTextArea ta1 = new JTextArea(2,45);
	static JTextArea ta2 = new JTextArea(2,45);
	static JTextArea label= new JTextArea();
	static JButton button=new JButton("SEND");
	static JPanel panel=new JPanel();
	Server server;
	Client client;
	private static String message="";
	static String fullmessage="";
	public static void main(String[] args) {
		new ChatApp();
	}
	ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(ta1);
			panel.add(button);
			panel.add(label);
			add(panel);
			label.setEditable(false);
			button.addActionListener((e)->{
				setMessage(ta1.getText());
				fullmessage+="\n"+"Server: "+getMessage();
				ta1.setText("");
				server.sendMessage();
				label.setText(fullmessage);
			});
			setVisible(true);
			setSize(600, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel.add(ta2);
			panel.add(button);
			panel.add(label);
			add(panel);
			label.setEditable(false);
			button.addActionListener((e)->{
				setMessage(ta2.getText());
				fullmessage+="\n"+"Server: "+getMessage();
				ta2.setText("");
				client.sendMessage();
				label.setText(fullmessage);
			});
			setVisible(true);
			setSize(600, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		ChatApp.message = message;
	}
}