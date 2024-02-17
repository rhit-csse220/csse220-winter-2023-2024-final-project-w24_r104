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
<<<<<<< HEAD
				while (population.getNumGenerations() <= NUM_GENERATIONS)
=======
				if (population.getNumGenerations() < 20) {
>>>>>>> branch 'master' of https://github.com/rhit-csse220/csse220-winter-2023-2024-final-project-w24_r104.git
					population.runEvolutionaryLoop();
			}
		});
		ExpAllelesViewer expAllelesViewer = new ExpAllelesViewer(population, t);
	}
	
}
