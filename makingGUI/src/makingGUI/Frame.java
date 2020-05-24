package makingGUI;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {
	
	Container cPane;
	ImageIcon img;
	JLabel Imgbox;
	Frame frame = new Frame();
	final int FRAME_WIDTH = img.getIconWidth();
	final int FRAME_HEIGHT = img.getIconHeight();
	
	//JButton btn1 = new JButton("Á¦´ë");
	
	public Frame() {
		frame.setTitle("takingTayoWithBomBom");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//		super("takingTayoWithBomBom");
		
		cPane = getContentPane();
		cPane.setLayout(null);
		
		img = new ImageIcon(".\\img\\jejuMap.jpg");
		Imgbox = new JLabel(img);
		Imgbox.setBounds(0, 50, img.getIconWidth(), img.getIconHeight());
		
		cPane.add(Imgbox);
	}

	public static void main(String[] args) {
		new Frame();

	}

}
