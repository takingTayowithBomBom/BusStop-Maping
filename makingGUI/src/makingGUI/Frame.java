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
	final int FRAME_WIDTH = 720;
	final int FRAME_HEIGHT = 856;
	
	ImageIcon MarkerImg;
	JButton [] Markers = new JButton[13];
	int [][] busLocation = {
			{320,125},{450,375},{600,400},{500,410},{500,550},
			{300,550},{200,550},{140,650},{110,550},{250,470},
			{350,340},{423,280},{300,260}
	};

	
	//JButton btn1 = new JButton("Á¦´ë");
	
	public Frame() {
		setTitle("takingTayoWithBomBom");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		super("takingTayoWithBomBom");
		
		cPane = getContentPane();
		cPane.setLayout(null);
		
		img = new ImageIcon("./img/jejuMap.jpg");
		Imgbox = new JLabel(img);
		Imgbox.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		cPane.add(Imgbox, 1, 0);
		
		MarkerImg = new ImageIcon("./img/C_busStop.png");
		for(int i = 0; i < 13; i++) {
			Markers[i] = new JButton(MarkerImg);
			Markers[i].setBounds(busLocation[i][0], busLocation[i][1], MarkerImg.getIconWidth(), MarkerImg.getIconHeight());
			cPane.add(Markers[i], i+2, 0);
		}
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Frame();

	}

}
