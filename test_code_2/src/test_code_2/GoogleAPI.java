package test_code_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.ImageIcon;


public class GoogleAPI {
	
	public void downloadMap(String location) {
		try {
			//String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center="
			//				+ URLEncoder.encode(location, "UTF-8") + "&zoom=11&size=612x612&scale=2&key=AIzaSyDKH_oO4T_L-bD3172qdi0mmmHV_ez1orY";
			String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=Brooklyn+Bridge,New+York,NY&zoom=13&size=612x612&maptype=roadmap&markers=color:blue%7Clabel:S%7C40.702147,-74.015794&markers=color:green%7Clabel:G%7C40.711614,-74.012318&markers=color:red%7Clabel:C%7C40.718217,-73.998284&key=AIzaSyDKH_oO4T_L-bD3172qdi0mmmHV_ez1orY";
			URL url = new URL(imageURL);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location);
			byte[] b = new byte[2048];
			int length;
			while((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ImageIcon getMap(String location) {
		return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(612,612,java.awt.Image.SCALE_SMOOTH));
	}
	
	public void fileDelete (String fileName) {
		File f = new File(fileName); 
		f.delete();
	}
}
