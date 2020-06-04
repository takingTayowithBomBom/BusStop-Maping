package makingGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bus {
	/*private String[][] database2 = new String[288][4];
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
	*/
	void BusAnimation(int [][]database2) {
		int curTime = new TimeManage().getTimeInt();
		int cnt = 0;
		
		for(int i = 1; i < busLocation.length(); i++) {
			for(int j = 1; j < 24; j++) {
				if(curTime == Integer.parseInt(database2[i][1]))
					ABus.setBounds(busLocation[i][0], busLocation[i][1], 30, 30);
				else if(curTime == Integer.parseInt(database2[i][3]))
					BBus.setBounds(busLocation[i][0], busLocation[i][1], 30, 30);
			}
		}
}
