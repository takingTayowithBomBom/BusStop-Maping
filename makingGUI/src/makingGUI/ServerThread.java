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

}//���ڱ� ��Ʈ��ũ�� �����ϴ� ������ �ñ������� ��������� �ϰھ�
//�˾ƺ��� ���ڴ�. �ϴ� string�Է�, ��������
