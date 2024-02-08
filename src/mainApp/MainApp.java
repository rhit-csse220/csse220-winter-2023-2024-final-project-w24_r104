package mainApp;

public class MainApp {
	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.runApp();
	}

	private void runApp() {
		SimulatorViewer chromosomesViewer = new SimulatorViewer();
		DataVisualizationViewer graphViewer = new DataVisualizationViewer();
	}

}