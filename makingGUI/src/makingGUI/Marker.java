package makingGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Marker {
	
	private String[] database = new String[25];
	
	Marker(){
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
		}catch(FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
	}
	
	public void printData() {
		for(int i = 0; i < database.length && database[i] != null; i++)
			System.out.println(database[i]);
	}
	
	public static void main(String [] args) {
		Marker M = new Marker();
		M.printData();
	}
}
