package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class ChromosomeComponent extends JComponent {
	private Individual individual;
	
	public ChromosomeComponent(Individual individual) {
		this.individual = individual;
	}
	
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
	}

	public void editChromosome() {

	}
	
	public void loadChromosomeFile(String filename) {
		
	}
	
	public void update() {
		
	}
}
