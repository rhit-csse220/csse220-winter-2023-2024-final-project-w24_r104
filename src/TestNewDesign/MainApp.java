package TestNewDesign;

import mainApp.DataVisualizationViewer;
import mainApp.Population;

public class MainApp {
	private Population population;
	
	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.runApp();
	}
	
	private void runApp() {
		this.population = new Population();
		
		ChromosomesViewer chromosomesViewer = new ChromosomesViewer(this.population);
//		DataVisualizationViewer graphViewer = new DataVisualizationViewer();
	}
}
