package makingGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class MarkerClickEvent implements MouseListener{
	private String[] database = new String[25];
	@Override
    public void mouseClicked(MouseEvent e) {
		MarkerButton button = (MarkerButton)e.getSource();
		JOptionPane.showMessageDialog(null, "mouseClicked" + button.getNum());
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
		for(int i = 0; i < database.length && database[i] != null; i++)
			System.out.println(database[i]);
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

