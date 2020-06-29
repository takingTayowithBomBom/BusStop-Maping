package makingGUI;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import java.net.Socket;

public class ConnetClient {
	public static void main(String[] args) throws UnknownHostException, IOException{
		Socket socket = new Socket("172.19.3.117", 8888);
		System.out.println("Server's Socket connected with Client = "+socket);
		socket.close();
	}
}
