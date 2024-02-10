package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;

public class ChromosomeComponent extends JComponent {

	private Population population;
	private boolean hasFoundSolution;

	public ChromosomeComponent(Population population) {
		this.population = population;
		this.hasFoundSolution = false;
	}

	public void initializeRandomPop(int populationSize, int chromosomeLength) {
		population.initializeRandomly(populationSize, chromosomeLength, 0.01);
		repaint();
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
		population.drawOn(g2);

	}

	private void logDataInfo() {

	}

	public void update() {

	}

	public void setPopMutationRateAndMutate(double d) {
		population.setMutationRate(d);
		population.mutate();
		repaint();
	}

	public void mutateSquare(int x, int y) {
		this.population.mutateOneCell(x, y);
	}

	public String getFirstChromosomeString() {
		return this.population.getFirstChromosomeString();
	}

	public boolean hasFoundSolution() {
		return this.hasFoundSolution;
	}

	public Population getPopulation() {
		return this.population;
	}

}
