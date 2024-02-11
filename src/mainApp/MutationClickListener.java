package mainApp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class MutationClickListener implements MouseListener {

	private JLabel chromosomeFileLabel;
	private ChromosomeComponent component;
	private int sideLength;
	
	
	public MutationClickListener(JLabel chromosomeFileLabel, ChromosomeComponent component, int sideLength) {
		this.chromosomeFileLabel = chromosomeFileLabel;
		this.component = component;
		this.sideLength = sideLength;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.component.mutateSquare(e.getX(), e.getY(), this.sideLength);
		if (!chromosomeFileLabel.getText().endsWith(" (mutated)"))
			chromosomeFileLabel.setText(chromosomeFileLabel.getText() + " (mutated)");
		this.component.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
