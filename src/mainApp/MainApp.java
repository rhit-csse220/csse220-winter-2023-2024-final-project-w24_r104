package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Class: MainApp
 * 
 * @author W24_R104 <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 */
public class MainApp {
	
	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.runApp();
	}

	private void runApp() {
		Population population = new Population();
		Timer t = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Individual.IS_COLORFUL) {
					population.runEvolutionaryLoop();
				} else {
					if (!population.hasFoundSolution()) {
						population.runEvolutionaryLoop();
					} else {
						((Timer) e.getSource()).stop();
					}
				}
			}
		});
		ChromosomeViewer chromosomesViewer = new ChromosomeViewer(population, t);
		DataVisualizationViewer dataViewer = new DataVisualizationViewer(population, t);
	}

}