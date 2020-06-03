package makingGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class MarkerClickEvent implements MouseListener{
	@Override
    public void mouseClicked(MouseEvent e) {
		MarkerButton button = (MarkerButton)e.getSource();
		if(button == RB1)
			repaint();
		else if(button == RB2)
		JOptionPane.showMessageDialog(null, "mouseClicked" + button.getNum());
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

