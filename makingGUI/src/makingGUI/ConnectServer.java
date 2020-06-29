package makingGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ConnectServer extends Thread {

	private Socket socket;
	
	public ConnectServer(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		super.run();
		
		// List�� socket ������ ���� Map ����
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("socket", socket);
		try {
			map.put("bufferedReader", new BufferedReader(new InputStreamReader(socket.getInputStream())));
			map.put("printWriter", new PrintWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainServer.addList(map);
		
		// ��� �������� ���� �˸��� ����
		for (Map<String, Object> socketInfo : MainServer.LIST) {
			((PrintWriter) socketInfo.get("printWriter")).println(socket.getPort() + "���� �����Ͽ����ϴ�. (�� " + MainServer.LIST.size() + "��)");
			((PrintWriter) socketInfo.get("printWriter")).flush();
		}
		
		try {
			
			String receiveString = null;
			
			while(true) {
				
				// ������ ���� ���� �޼���
				receiveString = ((BufferedReader) map.get("bufferedReader")).readLine();
				receiveString = socket.getPort() + ": "+ receiveString;
				//System.out.println(receiveString);
				
				// �����ڸ� ������ ��� �����ڿ��� �޼��� ����
				for (Map<String, Object> socketInfo : MainServer.LIST) {
					
					if (socketInfo.get("socket").equals(socket)) continue;
					((PrintWriter) socketInfo.get("printWriter")).println(receiveString);
					((PrintWriter) socketInfo.get("printWriter")).flush();
				}
				
			}
		} catch (IOException e) {

			MainServer.removeList(map);
			for (Map<String, Object> socketInfo : MainServer.LIST) {
				((PrintWriter) socketInfo.get("printWriter")).println(socket.getPort() + "���� ���� �����Ͽ����ϴ�. (�� " + MainServer.LIST.size() + "��)");
				((PrintWriter) socketInfo.get("printWriter")).flush();
			}
		}
	}
}