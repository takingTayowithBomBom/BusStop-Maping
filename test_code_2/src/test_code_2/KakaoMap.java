package test_code_2;

import java.io.IOException; 
import java.net.URI; 
import java.net.URISyntaxException; 
import java.awt.Desktop; 
public class KakaoMap { 
	public static void main(String [] args){ 
		try { Desktop.getDesktop().browse(new URI("https://www.google.com/maps/@33.4556745,126.5626266,15.93z"));
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (URISyntaxException e) { 
			e.printStackTrace();
		} 
	} 
}
