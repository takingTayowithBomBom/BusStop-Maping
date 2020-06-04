package makingGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MarkerClickEvent implements MouseListener{
	private String[] database = new String[288];
	private String[][] database2 = new String[288][4];
	private String[][][] database3 = new String[288][4][4];
	ImageIcon tayoimg = new ImageIcon("./img/tayo.jpg");
	
	MarkerClickEvent(){
		try {
			File file = new File("./dataBase/BusTime.csv");
			FileReader filereader = new FileReader(file);
		    BufferedReader bufReader = new BufferedReader(filereader);
		    String line = "";
		    int i = 0;
		    
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
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
		MarkerButton button = (MarkerButton)e.getSource();
		String tempStr = "A노선   :   B노선\n";
		int curTime = new TimeManage().getTimeInt();
		for(int i = 1 + 24*button.getNum(); i < 24*button.getNum()+24; i++) {
			database3[i][1] = database2[i][1].split("");
			database3[i][3] = database2[i][3].split("");
			if(database2[i][1].length() % 2 == 0) {
				tempStr += database3[i][1][0];
				tempStr += database3[i][1][1];
				tempStr += ":";
				tempStr += database3[i][1][2];
				tempStr += database3[i][1][3];
				if(curTime > Integer.parseInt(database2[i][1])) 
					tempStr += " 운행 종료!!     ";
				else
					tempStr += "        ";
			}
			else {
				tempStr += "  ";
				tempStr += database3[i][1][0];
				tempStr += ":";
				tempStr += database3[i][1][1];
				tempStr += database3[i][1][2];
				if(curTime > Integer.parseInt(database2[i][1])) 
					tempStr += " 운행 종료!!     ";
				else
					tempStr += "         ";
			}
			
			if(database2[i][3].length() % 2 == 0) {
				tempStr += database3[i][3][0];
				tempStr += database3[i][3][1];
				tempStr += ":";
				tempStr += database3[i][3][2];
				tempStr += database3[i][3][3];
			}
			else {
				tempStr += database3[i][3][0];
				tempStr += ":";
				tempStr += database3[i][3][1];
				tempStr += database3[i][3][2];
			}
			if(curTime > Integer.parseInt(database2[i][3]))
				tempStr += " 운행 종료!! ";
			tempStr += "\n";
		}
		JOptionPane.showMessageDialog(null, tempStr, "BusStopInfo", JOptionPane.PLAIN_MESSAGE, tayoimg);
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

