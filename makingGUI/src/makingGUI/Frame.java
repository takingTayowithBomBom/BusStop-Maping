package makingGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Frame extends JFrame {
	Mypanel panel = new Mypanel();
	//Container cPane;
	ImageIcon img;
	JLabel Imgbox;
	final int FRAME_WIDTH = 720;
	final int FRAME_HEIGHT = 856;
	
	ImageIcon MarkerImg;
	MarkerButton [] Markers = new MarkerButton[13];
	
	ImageIcon img2 = new ImageIcon("./img/reverseclockwise2.png");
	RoundButton RB1 = new RoundButton("A노선", img2);
	
	ImageIcon img3 = new ImageIcon("./img/clockwise2.png");
	RoundButton RB2 = new RoundButton("B노선", img3);
	
	
	int [][] busLocation = {
			{320,125},{450,375},{600,400},{500,410},{500,550},
			{300,550},{200,550},{140,650},{110,550},{250,470},
			{350,340},{423,280},{300,260}
	};
	public Frame() {
		setTitle("TayoWithBomBom");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		panel.setLayout(null);
		
		
		img = new ImageIcon("./img/jejuMap.jpg");
		Imgbox = new JLabel(img);
		Imgbox.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		panel.add(Imgbox, 1, 0);
		MarkerImg = new ImageIcon("./img/C_busStop.png");
		for(int i = 0; i < 13; i++) {
			Markers[i] = new MarkerButton();
			Markers[i].setIcon(MarkerImg);
			Markers[i].setNum(i);
			Markers[i].setBounds(busLocation[i][0], busLocation[i][1], MarkerImg.getIconWidth(), MarkerImg.getIconHeight());
			Markers[i].addMouseListener(new MarkerClickEvent());
			Markers[i].setBorderPainted(false); 
			panel.add(Markers[i], i+2, 0);
		}
		
		RB1.setBounds(600, 90, 100, 30);
		RB1.setBorderPainted(false);
		RB1.addMouseListener(new MyButtonClickEvent());
		
		RB2.setBounds(600, 124, 100, 30);
		RB2.setBorderPainted(false);
		RB2.addMouseListener(new MyButtonClickEvent());
		
		panel.add(RB1, 15, 0);
		panel.add(RB2, 15, 0);
		
		setVisible(true);
	}

	class Mypanel extends JPanel{
		float dash3[] = {10,3f};
		int [][] pathStart = {
				{335, 125}, {352, 303}, {451, 335}, {446, 386},
				{337, 390}, {330, 488}, {240, 524}, {137, 527},
				{129, 603}, {142, 702}, {218, 698}, {230, 598}, 
				{242, 598}, {515, 569}, {552, 450}, {502, 442}
		};
		int [][] pathEnd = {
				{335, 260}, {434, 302}, {455, 372}, {347, 386},
				{335, 482}, {247, 525}, {140, 521}, {127, 544},
				{134, 696}, {220, 700}, {220, 598}, {298, 598},
				{498, 598}, {511, 460}, {626, 450}, {476, 422}
		};
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(new Color(255, 30, 30, 70));
			for(int i = 0; i < 16; i++) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(9, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1,dash3,0));
				g.drawLine(pathStart[i][0], pathStart[i][1], pathEnd[i][0], pathEnd[i][1]);
			}
		}
	}
}
