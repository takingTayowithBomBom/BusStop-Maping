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
			System.out.println("���� �غ� ��...");
			ServerSocket serverSocket = new ServerSocket(8808);
			System.out.println("������ �غ� �Ǿ����ϴ�.");
			
			while (true) {
				
				System.out.println("Ŭ���̾�Ʈ ���� ��� ��...");
				Socket socket = serverSocket.accept();
				System.out.println("Ŭ���̾�Ʈ�� ���� �Ͽ����ϴ�.");
				
				ConnectServer connectServer = new ConnectServer(socket);
				connectServer.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}