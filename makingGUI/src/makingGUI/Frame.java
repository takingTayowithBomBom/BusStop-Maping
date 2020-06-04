package makingGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
	RoundButton TimePanel;
	final int FRAME_WIDTH = 720;
	final int FRAME_HEIGHT = 856;
	int pathNum = 0;

	
	ImageIcon MarkerImg;
	MarkerButton [] Markers = new MarkerButton[12];
	ImageIcon img2 = new ImageIcon("./img/reverseclockwise2.png");
	RoundButton RB1 = new RoundButton("A노선", img2);
	ImageIcon img3 = new ImageIcon("./img/clockwise2.png");
	RoundButton RB2 = new RoundButton("B노선", img3);
	ImageIcon Abus = new ImageIcon("./img/busred.png");
	ImageIcon Bbus = new ImageIcon("./img/busblue.png");
	
	int [][] resetStart = {
			{335, 125}, {352, 303}, {451, 335}, {446, 386},
			{337, 390}, {330, 488}, {240, 524}, {137, 527},
			{129, 603}, {142, 702}, {218, 698}, {230, 598}, 
			{242, 598}, {515, 569}, {552, 450}, {502, 442}
	};
	int [][] resetEnd = {
			{335, 260}, {434, 302}, {455, 372}, {347, 386},
			{335, 482}, {247, 525}, {140, 521}, {127, 544},
			{134, 696}, {220, 700}, {220, 598}, {298, 598},
			{498, 598}, {511, 460}, {626, 450}, {476, 422}
	};
	int [][] pathStart = {
			{0,0}, {0,0}, {0,0}, {0,0},
			{0,0}, {0,0}, {0,0}, {0,0},
			{0,0}, {0,0}, {0,0}, {0,0}, 
			{0,0}, {0,0}, {0,0}, {0,0}
	};
	int [][] pathEnd = {
			{0,0}, {0,0}, {0,0}, {0,0},
			{0,0}, {0,0}, {0,0}, {0,0},
			{0,0}, {0,0}, {0,0}, {0,0}, 
			{0,0}, {0,0}, {0,0}, {0,0}
	};
	
	int [][] busLocation = {
			{320,125},{450,375},{500,410},{500,550},
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
		for(int i = 0; i < 12; i++) {
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
		
		TimePanel = new RoundButton(new TimeManage().getTime());
		TimePanel.setForeground(Color.black);
		TimePanel.setBounds(30, 30, 150, 50);
		panel.add(TimePanel, 16, 0);

		setVisible(true);
		JLabel timerLabel = new JLabel();
		TimerThread th = new TimerThread(timerLabel);
		th.start();
		
		JLabel ABus = new JLabel();
		JLabel BBus = new JLabel();
		BusAnimation Abus= new BusAnimation();
		
	}
	class BusAnimation extends Thread{
		private JLabel ABus;
		private JLabel BBus;
		private String[] database = new String[288];
		private String[][] database2 = new String[288][4];
		
		File file = new File("./dataBase/BusTime.csv");
		FileReader filereader = new FileReader(file);
	    BufferedReader bufReader = new BufferedReader(filereader);
	    String line = "";
	    int k = 0;
	    
	    while((line = bufReader.readLine()) != null){
	        database[k] = line;
	        database2[k] = database[k].split(",");
	        k++;
	    }
	    bufReader.close();
	    
		public BusAnimation() {
			int curTime = new TimeManage().getTimeInt();
			
		    
			for(int i = 1; i < busLocation.length; i++) {
				for(int j = 1; j < 24; j++) {
					if(curTime == Integer.parseInt(database2[i][1]))
						ABus.setBounds(busLocation[i][0], busLocation[i][1], 30, 30);
					else if(curTime == Integer.parseInt(database2[i][3]))
						BBus.setBounds(busLocation[i][0], busLocation[i][1], 30, 30);
				}
			}
		}
	}
	

	class TimerThread extends Thread { 
		private JLabel timerLabel;
		
		public TimerThread(JLabel timerLabel) { 
			this.timerLabel = timerLabel; 
		}
		@Override public void run() { 
			while(true) {
				TimePanel.setText(new TimeManage().getTime());
				try { 
					Thread.sleep(1000); 
				} 
				catch(InterruptedException e) {
					return; 
				}
			} 
		}
	}
	
	class Mypanel extends JPanel{
		float dash3[] = {10,3f};
		
		public void paint(Graphics g) {
			super.paint(g);
			if(pathNum == 0)
				g.setColor(new Color(255, 30, 30, 70));
			else
				g.setColor(new Color(30, 30, 255, 70));
			for(int i = 0; i < 16; i++) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(9, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,1,dash3,0));
				g.drawLine(pathStart[i][0], pathStart[i][1], pathEnd[i][0], pathEnd[i][1]);
			}
		}
	}
	
	public class MyButtonClickEvent implements MouseListener{
		@Override
	    public void mouseClicked(MouseEvent e) {
			RoundButton button = (RoundButton)e.getSource();
			if(button.getText().equals("A노선")) {
				if(button.bool == 0) {
					for(int i = 0; i < 16; i++) {
						pathStart[i][0] = 0;
						pathStart[i][1] = 0;
						pathEnd[i][0] = 0;
						pathEnd[i][1] = 0;
					}
					panel.repaint();
					button.bool = 1;
				}
				else {
					for(int i = 0; i < 16; i++) {
						pathStart[i][0] = resetStart[i][0];
						pathStart[i][1] = resetStart[i][1];
						pathEnd[i][0] = resetEnd[i][0];
						pathEnd[i][1] = resetEnd[i][1];
					}
					panel.repaint();
					button.bool = 0;
				}
				pathNum = 0;
			}
			else if(button.getText().equals("B노선")) {
				if(button.bool == 0) {
					for(int i = 0; i < 16; i++) {
						pathStart[i][0] = 0;
						pathStart[i][1] = 0;
						pathEnd[i][0] = 0;
						pathEnd[i][1] = 0;
					}
					panel.repaint();
					button.bool = 1;
				}
				else {
					for(int i = 0; i < 16; i++) {
						pathStart[i][0] = resetStart[i][0];
						pathStart[i][1] = resetStart[i][1];
						pathEnd[i][0] = resetEnd[i][0];
						pathEnd[i][1] = resetEnd[i][1];
					}
					panel.repaint();
					button.bool = 0;
				}
				pathNum = 1;
			}
	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {}

	    @Override
	    public void mouseExited(MouseEvent e) {}

	    @Override
	    public void mousePressed(MouseEvent e) {}

	    @Override
	    public void mouseReleased(MouseEvent e) {}
	}
}
