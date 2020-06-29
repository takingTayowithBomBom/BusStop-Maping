package makingGUI;

import java.net.Socket;

public class ClientSocket {
	public static void main(String[] args) {
		Socket clientSocket = new Socket("123.456.0.1",8888);
	}
}
