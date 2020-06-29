package makingGUI;

import java.io.IOException;
import java.net.Socket;

public class MainClient {
	//public static void main(String[] args) {
	MainClient(){
		try {
			System.out.println("서버에 접속 중...");
			Socket socket = new Socket("127.0.0.1", 8808);
			System.out.println("서버에 접속하였습니다.");
			
			ClientReceiveThread clientReceiveThread = new ClientReceiveThread(socket);
			//ClientSendThread clientSendThread = new ClientSendThread(socket);
			
			//clientSendThread.start();
			clientReceiveThread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}