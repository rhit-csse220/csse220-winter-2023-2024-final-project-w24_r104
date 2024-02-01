package mainApp;

import java.awt.Color;
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
		paintChromosomeGrid(g2);
	}
	
	public void paintChromosomeGrid(Graphics2D g2) {
		int[] chromosome = this.individual.getChromosome();
		int x = 0;
		int y = 0;
		int sideLength = 40;
		for (int i = 0; i < 10; i++) { // to be changed to be applicable for different chromosome lengths
			for (int j = 0; j < 10; j++) {
				if (chromosome[10*i + j] == 0)
					g2.setColor(Color.BLACK);
				else
					g2.setColor(Color.GREEN);
				g2.fillRect(x, y, sideLength, sideLength);
				x += sideLength;
			}
			x = 0;
			y += sideLength;
		}
	}

	public void editChromosome() {

	}
	
	public void loadChromosomeFile(String filename) {
		
	}
	
	public void update() {
		
	}
}
