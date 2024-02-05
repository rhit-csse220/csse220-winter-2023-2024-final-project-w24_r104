package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;

public class SimulatorComponent extends JComponent {
	
	private Population population;

	public SimulatorComponent(int popSize) {
		this.population = new Population(popSize);
	}
	
	public void runEvolutionaryLoop() {
		int numGenerations = 0;
		while (this.population.getFittestIndividual().calculateSimpleFitness() < 100) {
			this.population.truncationSelection();
//			this.population.selectionByRouletteWheel("Simple");
			this.population.replenishPopulation();
			this.population.crossover();
			this.population.mutate();
			numGenerations++;
			System.out.println(numGenerations + "th generation");
			System.out.println("Best Individual: " + this.population.getFittestIndividual());
		}
		System.out.println("Found solution after " + numGenerations + " generations!");
		System.out.println("Solution has fitness of " + this.population.getFittestIndividual());
	}

	public void initializeRandomPop(int populationSize, int chromosomeLength) {
		population.initializeRandomly(populationSize, chromosomeLength);
		repaint();
	}
	
	public void initializePopFromFile(int popSize, String filename) throws InvalidChromosomeFormatException, FileNotFoundException, IOException {
		population.initializeFromFile(popSize, filename);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
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
	
	public Individual getFirstIndividual() {
		return this.population.getFirstIndividual();
	}

}
