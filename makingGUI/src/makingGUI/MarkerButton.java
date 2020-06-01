package makingGUI;

import javax.swing.JButton;

public class MarkerButton extends JButton{
	private int markerNum;
	
	public void setNum (int num) {
		markerNum = num;
	}
	
	public int getNum () {
		return markerNum;
	}
}
