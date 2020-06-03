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
	ImageIcon tayoimg = new ImageIcon("./img/tayo.jpg");
	@Override
    public void mouseClicked(MouseEvent e) {
		MarkerButton button = (MarkerButton)e.getSource();
		String tempStr = "";
		try {
			File file = new File("./dataBase/BusTime.csv");
			FileReader filereader = new FileReader(file);
		    BufferedReader bufReader = new BufferedReader(filereader);
		    String line = "";
		    int i = 0;
		    
		    while((line = bufReader.readLine()) != null){
		        database[i] = line;
		        i++;
		    }
		    bufReader.close();
		}catch(FileNotFoundException e2) {
            // TODO: handle exception
        }catch(IOException e2){
            System.out.println(e2);
        }
		
		System.out.println(button.getNum());
		for(int i = 1 + 24*button.getNum(); i < 24*button.getNum()+24; i++) {
			tempStr += database[i];
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

