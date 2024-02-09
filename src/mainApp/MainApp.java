package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class MainApp {
	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.runApp();
	}

	private void runApp() {
		Population population = new Population();
		Timer t = new Timer(100, new ActionListener() {

			private int numGenerations = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!population.hasFoundSolution()) {
					population.runEvolutionaryLoop();
					numGenerations++;
				} else {
					System.out.println("Found solution after " + numGenerations + " generations: " + population.getFittestIndividual());
				}
			}
		});
		ChromosomeViewer chromosomesViewer = new ChromosomeViewer(population, t);
		DataVisualizationViewer dataViewer = new DataVisualizationViewer(population, t);
	}

}