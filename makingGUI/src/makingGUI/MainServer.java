package makingGUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainServer {
	
	public static List<Map<String, Object>> LIST;
	
	private static Object lock = new Object();
	
	public static void addList(Map<String, Object> addmap) {
		synchronized(lock) {
			LIST.add(addmap);
		}	
	}
	
	public static void removeList(Map<String, Object> addmap) {
		synchronized(lock) {
			LIST.remove(addmap);
		}
	}
	
	public static void main(String[] args) {
		
		LIST = new ArrayList<Map<String, Object>>();
		
		try {
			System.out.println("서버 준비 중...");
			ServerSocket serverSocket = new ServerSocket(8808);
			System.out.println("서버가 준비 되었습니다.");
			
			while (true) {
				
				System.out.println("클라이언트 접속 대기 중...");
				Socket socket = serverSocket.accept();
				System.out.println("클라이언트가 접속 하였습니다.");
				
				ConnectServer connectServer = new ConnectServer(socket);
				connectServer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}