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
	
	public void initializePop(int popSize, String filename) throws FileNotFoundException, IOException {
		population.initializeFromFile(popSize, filename);
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
}
