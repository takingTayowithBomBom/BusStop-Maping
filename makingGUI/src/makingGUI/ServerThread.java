package makingGUI;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable{
	private Socket socket;
	public void SeverThread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
			while(true) {
				OutputStream out = socket.getOutputStream();
				out.write(sc.next().getBytes());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}//갑자기 네트워크를 연결하는 이유가 궁금해져서 여기까지만 하겠어
//알아보고 오겠다. 일단 string입력, 보냄까지
