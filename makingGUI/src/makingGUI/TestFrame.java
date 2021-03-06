package makingGUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestFrame extends JFrame {
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

	JButton ABus = new JButton();
	JButton BBus = new JButton();
	
	
	String[] database = new String[288];
	String[][] database2 = new String[288][4];
	
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
			/*
			{320,125},{450,375},{500,410},{500,550},
			{300,550},{200,550},{140,650},{110,550},{250,470},
			{350,340},{423,280},{300,260}
			*/
			{320,95},{300,260},{423,280},{350,340},
			{250,470},{110,550},{140,650},{200,550},
			{300,550},{500,550},{500,410},{450,375}
			
			
	};
	public TestFrame() {
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
		
		ABus.setIcon(Abus);
		BBus.setIcon(Bbus);
		
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
		panel.add(ABus, 17, 0);
		panel.add(BBus, 18, 0);
		
		setVisible(true);
		JLabel timerLabel = new JLabel();
		TimerThread th = new TimerThread(timerLabel);
		th.start();
		JLabel timerLabel2 = new JLabel();
		BusAnimation bth = new BusAnimation(timerLabel2);
		bth.start();
		setVisible(true);
		
	}
	class BusAnimation extends Thread{
		int i = 0;
		private JLabel timerLabel2;
		
		public BusAnimation(JLabel timerLabel2) {
			try {
				File file = new File("./dataBase/BusTime.csv");
				FileReader filereader = new FileReader(file);
			    BufferedReader bufReader = new BufferedReader(filereader);
			    String line = "";
			    
			    while((line = bufReader.readLine()) != null){
			        database[i] = line;
			        database2[i] = database[i].split(",");
			        i++;
			    }
			    bufReader.close();
			}catch(FileNotFoundException e2) {
	            // TODO: handle exception
	        }catch(IOException e2){
	            System.out.println(e2);
	        }
			this.timerLabel2 = timerLabel2; 
		}
		
		@Override 
		public void run() { 
			while(true) {

				int k = 0;
				int k2 = 0;
				int curtime = new TimeManage().getTimeInt();
				
				for(k = 1; k < 24*11+23; k++) {
					//System.out.println(database2[k][1] + Integer.toString(curtime));
					if(k%24 == 0 )
						continue;
					if( Integer.parseInt(database2[k][1]) == curtime) {
						break;
					}
				}
				for(k2 = 1; k2 < 24*11+23; k2++) {
					//System.out.println(database2[k][1] + Integer.toString(curtime));
					if(k2%24 == 0 )
						continue;
					if( Integer.parseInt(database2[k2][1]) == curtime+1) {
						break;
					}
				}
				if(k2 == 24*11+23) {
					k2 = 0;
				}
				if(k == 24*11+23) {
					ABus.setBorderPainted(false);
					ABus.setBounds(0, 0, 30, 30);
				}
				else {
					k = k / 24;
					k2 = k2 / 24;
					//System.out.println(k);
					ABus.setBorderPainted(false);
					float sec = 60.000F;
					float curLoc = (new TimeManage().getSecInt()) / sec;
					System.out.println(curLoc);
					ABus.setBounds(Math.round(busLocation[k][0] + (busLocation[k2][0] - busLocation[k][0])*curLoc),
									Math.round((busLocation[k][1] + (busLocation[k2][1] - busLocation[k][1])*curLoc)), 30, 30);
				}
				//System.out.println(k);
				for(k = 1; k < 24*11+23; k++) {
					//System.out.println(database2[k][1] + Integer.toString(curtime));
					if(k%24 == 0 )
						continue;
					if( Integer.parseInt(database2[k][3]) == curtime) {
						break;
					}
				}
				for(k2 = 1; k2 < 24*11+23; k2++) {
					//System.out.println(database2[k][1] + Integer.toString(curtime));
					if(k2%24 == 0 )
						continue;
					if( Integer.parseInt(database2[k2][3]) == curtime + 1) {
						break;
					}
				}
				if(k2 == 24*11+23) {
					k2 = 0;
				}
				if(k == 24*11+23) {
					BBus.setBorderPainted(false);
					BBus.setBounds(0, 0, 30, 30);
				}
				else {
					k = k / 24;
					k2 = k2 / 24;
					//System.out.println(k);
					BBus.setBorderPainted(false);
					float sec = 60.000F;
					float curLoc = (new TimeManage().getSecInt()) / sec;
					System.out.println(curLoc);
					BBus.setBounds(Math.round(busLocation[k][0] + (busLocation[k2][0] - busLocation[k][0])*curLoc),
								Math.round((busLocation[k][1] + (busLocation[k2][1] - busLocation[k][1])*curLoc)), 30, 30);
				}
				panel.repaint();
				try { 
					Thread.sleep(1000); 
				} 
				catch(InterruptedException e) {
					return; 
				}
			} 
		}
	}
	

	class TimerThread extends Thread { 
		private JLabel timerLabel;
		
		public TimerThread(JLabel timerLabel) { 
			this.timerLabel = timerLabel; 
		}
		@Override 
		public void run() { 
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
