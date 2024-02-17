package expMainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ExpMainApp {
	public static final int NUM_GENERATIONS = 50;

	public static void main(String[] args) {
		ExpMainApp app = new ExpMainApp();
		app.runApp();
	}

	private void runApp() {
		ExpPopulation population = new ExpPopulation();
		Timer t = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (population.getNumGenerations() < NUM_GENERATIONS) {
					population.runEvolutionaryLoop();
				}
			}
				
		});
		ExpAllelesViewer expAllelesViewer = new ExpAllelesViewer(population, t);
	}

}
