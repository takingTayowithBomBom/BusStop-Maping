package makingGUI;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientReceiveThread extends Thread {

	private Socket socket;
	public TextAreaEx TA;
	
	public ClientReceiveThread(Socket socket) {
		this.socket = socket;
		TA = new TextAreaEx(socket);
	}

	@Override
	public void run() {
		
		super.run();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String receiveString = null;
			
			while (true) {
				receiveString = bufferedReader.readLine();
				TA.setTextTA(receiveString);
				System.out.println(receiveString);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class TextAreaEx extends JFrame { 
	private JTextField tf = new JTextField(20); 
	private JTextArea ta = new JTextArea(7, 20);
	
	ClientSendThread clientSendThread;
	
	public TextAreaEx(Socket socket) { 
		clientSendThread = new ClientSendThread(socket);
		clientSendThread.start();
		
		
		
		setTitle("자유 채팅방!!");
		tf.setText("콘솔에서 채팅입력!!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container c = getContentPane(); 
		c.setLayout(new FlowLayout());
		c.add(new JLabel("입력 후 <Enter> 키를 입력하세요")); 
		c.add(tf); 
		c.add(new JScrollPane(ta));
		tf.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				JTextField t = (JTextField)e.getSource(); 
				//ta.append(t.getText() + "\n");
				//t.setText(""); 
				}
			}); 
		setSize(300,300); 
		setVisible(true);
	} 
	
	void setTextTA(String targetStr)
	{
		String tempStr = ta.getText();
		ta.setText(tempStr + targetStr + '\n');
	}
}