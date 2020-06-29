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
		
		// List에 socket 정보를 담을 Map 생성
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("socket", socket);
		try {
			map.put("bufferedReader", new BufferedReader(new InputStreamReader(socket.getInputStream())));
			map.put("printWriter", new PrintWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainServer.addList(map);
		
		// 모든 유저에게 접속 알림을 전송
		for (Map<String, Object> socketInfo : MainServer.LIST) {
			((PrintWriter) socketInfo.get("printWriter")).println(socket.getPort() + "님이 접속하였습니다. (총 " + MainServer.LIST.size() + "명)");
			((PrintWriter) socketInfo.get("printWriter")).flush();
		}
		
		try {
			
			String receiveString = null;
			
			while(true) {
				
				// 서버가 전달 받은 메세지
				receiveString = ((BufferedReader) map.get("bufferedReader")).readLine();
				receiveString = socket.getPort() + ": "+ receiveString;
				//System.out.println(receiveString);
				
				// 전송자를 제외한 모든 접속자에게 메세지 전달
				for (Map<String, Object> socketInfo : MainServer.LIST) {
					
					if (socketInfo.get("socket").equals(socket)) continue;
					((PrintWriter) socketInfo.get("printWriter")).println(receiveString);
					((PrintWriter) socketInfo.get("printWriter")).flush();
				}
				
			}
		} catch (IOException e) {

			MainServer.removeList(map);
			for (Map<String, Object> socketInfo : MainServer.LIST) {
				((PrintWriter) socketInfo.get("printWriter")).println(socket.getPort() + "님이 접속 종료하였습니다. (총 " + MainServer.LIST.size() + "명)");
				((PrintWriter) socketInfo.get("printWriter")).flush();
			}
		}
	}
}