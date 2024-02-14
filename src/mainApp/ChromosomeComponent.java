package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;

public class ChromosomeComponent extends JComponent {

	private Population population;

	public ChromosomeComponent(Population population) {
		this.population = population;
	}

	public void initializePopFromFile(int popSize, String filename)
			throws InvalidChromosomeFormatException, FileNotFoundException, IOException {
		population.initializeFromFile(popSize, filename);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		population.drawOn(g2, Population.ALLELE_SIDE_LENGTH);
		if (population.hasRunEvolutionaryLoop())
			g2.drawString(
					"Average Hamming Distance: " + String.format("%.20f",
							population.calculateHammingDistance()
									/ population.getFittestIndividual().getChromosome().length),
					0, Population.ALLELE_SIDE_LENGTH * 112);
	}

	public void mutateSquare(int x, int y, int sideLength) {
		this.population.mutateOneCell(x, y, sideLength);
		repaint();
	}

	public Population getPopulation() {
		return this.population;
	}

	public String populationToString() {
		String populationString = "";
		// TODO Auto-generated method stub
		return null;
	}

}
