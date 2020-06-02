package makingGUI;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
	Mypanel panel = new Mypanel();
	Container cPane;
	ImageIcon img;
	JLabel Imgbox;
	final int FRAME_WIDTH = 720;
	final int FRAME_HEIGHT = 856;
	
	ImageIcon MarkerImg;
	MarkerButton [] Markers = new MarkerButton[13];
	int [][] busLocation = {
			{320,125},{450,375},{600,400},{500,410},{500,550},
			{300,550},{200,550},{140,650},{110,550},{250,470},
			{350,340},{423,280},{300,260}
	};
	public TestFrame() {
		setTitle("TayoWithBomBom");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cPane = getContentPane();
		cPane.setLayout(null);
		
		
		img = new ImageIcon("./img/jejuMap.jpg");
		Imgbox = new JLabel(img);
		Imgbox.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		cPane.add(Imgbox, 1, 0);
		MarkerImg = new ImageIcon("./img/C_busStop.png");
		for(int i = 0; i < 13; i++) {
			Markers[i] = new MarkerButton();
			Markers[i].setIcon(MarkerImg);
			Markers[i].setNum(i);
			Markers[i].setBounds(busLocation[i][0], busLocation[i][1], MarkerImg.getIconWidth(), MarkerImg.getIconHeight());
			Markers[i].addMouseListener(new MarkerClickEvent());
			Markers[i].setBorderPainted(false); 
			cPane.add(Markers[i], i+2, 0);
		}
		
		cPane.add(panel);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Frame();
	}
	class Mypanel extends JPanel{
		int [][] pathStart = {
				{320, 125}, {336, 252}, {368, 303}, {410, 302}, {453, 329}, {443, 387}, {337, 396}, {420, 493}, {247, 523},
				{181, 522}, {127, 554}, {132, 648}, {146, 701}, {220, 696}, {220, 608}, {238, 598}, {338, 598}, {431, 599},
				{515, 587}, {514, 514}, {524, 454}, {610, 449}, {451, 407}, {439, 301},{382, 303}, {337, 286}, {336, 200}
		};
		int [][] pathEnd = {
				{380, 250}, {250, 375}, {396, 304}, {449, 302}, {453, 381}, {387, 387}, {337, 477}, {276, 514}, {192, 523},
				{141, 523}, {130, 623}, {136, 693}, {170, 701}, {221, 646}, {221, 609}, {299, 597}, {416, 595}, {501, 598},
				{515, 535}, {514, 452}, {610, 449}, {529, 449}, {453, 328}, {395, 302}, {343, 302}, {338, 206}, {333, 164}
		};
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.RED);
			for(int i = 0; i < 1; i++) {
				g.drawLine(pathStart[i][0], pathStart[i][1], pathEnd[i][0], pathEnd[i][1]);
			}
		}
	}
}
