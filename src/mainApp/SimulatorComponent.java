package mainApp;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;

public class SimulatorComponent extends JComponent {
	private Population population;

	public SimulatorComponent(int popSize) {
		this.population = new Population(popSize);
	}

	
	public Individual initializePop(int popSize, String filename) throws InvalidChromosomeFormatException, FileNotFoundException, IOException {
		population.initializeFromFile(popSize, filename);
		return population.getFirstIndividual();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}

	private void logDataInfo() {

	}

	public void update() {

	}

	public void setPopMutationRateAndMutate(int rate) {
		population.setMutationRate(rate);
		population.mutate();
	}
}
