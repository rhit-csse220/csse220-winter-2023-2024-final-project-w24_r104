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

	
	public void initializePop(int popSize, String filename) throws InvalidChromosomeFormatException, FileNotFoundException, IOException {
		population.initializeFromFile(popSize, filename);
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

	public void setPopMutationRateAndMutate(int rate) {
		population.setMutationRate(rate);
		population.mutate();
		repaint();
	}


	public void mutateSquare(int x, int y) {
		this.population.mutateOneCell(x, y);
	}
}
