package makingGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSendThread extends Thread {

	private Socket socket;
	
	public ClientSendThread (Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		super.run();
		
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		String sendString = null;
		
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			printWriter = new PrintWriter(socket.getOutputStream());
			sendString = null;
			
			while (true) {
				sendString = bufferedReader.readLine();

				if (sendString.equals("exit")) {
					break;
				}
				
				printWriter.println(sendString);
				printWriter.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if (printWriter != null) {
				printWriter.close();
			}
			
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}