package makingGUI;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

	Frame frame = new Frame();
	
	public void createFrame() {
		super("takingTayoWithBomBom");
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.createFrame();

	}

}
