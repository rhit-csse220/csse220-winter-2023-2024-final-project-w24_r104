package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;

public class SimulatorComponent extends JComponent {
	
	private Population population;
	private int numGenerations;
	private boolean hasFoundSolution;

	public SimulatorComponent(int popSize) {
		this.population = new Population();
		this.numGenerations = 0;
		this.hasFoundSolution = false;
	}
	
	public void runEvolutionaryLoop() {
		this.population.truncationSelection();
//		this.population.selectionByRouletteWheel("Simple");
		this.population.crossover();
		this.population.mutate();
		this.population.createNewGeneration();
		numGenerations++;
		repaint();
		System.out.println(numGenerations + "th generation");
		System.out.println("Best Individual: " + this.population.getFittestIndividual());
		if (this.population.getFittestIndividual().getFitness("Simple") > 90)
			this.hasFoundSolution = true;
	}

	public void initializeRandomPop(int populationSize, int chromosomeLength) {
		population.initializeRandomly(populationSize, chromosomeLength, 0.01);
		repaint();
	}
	
	public void initializePopFromFile(int popSize, String filename) throws InvalidChromosomeFormatException, FileNotFoundException, IOException {
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
