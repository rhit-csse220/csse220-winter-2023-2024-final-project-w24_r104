package mainApp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class MutationClickListener implements MouseListener {

	private JLabel chromosomeFileLabel;
	private SimulatorComponent component;
	
	public MutationClickListener(JLabel chromosomeFileLabel, SimulatorComponent component) {
		this.chromosomeFileLabel = chromosomeFileLabel;
		this.component = component;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.component.mutateSquare(e.getX(), e.getY());
		this.component.repaint();
		if (!chromosomeFileLabel.getText().endsWith(" (mutated)"))
			chromosomeFileLabel.setText(chromosomeFileLabel.getText() + " (mutated)");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
