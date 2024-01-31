package mainApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Class: MainApp
 * 
 * @author W24_R104 <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 */
public class SimulatorViewer {
	private SimulatorComponent simComp;

	public SimulatorViewer() {
		this.simComp = new SimulatorComponent(10);
	}

	private void runApp() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter chromosome file:  (e.g. scores.txt)");

		boolean isDone = false;
		while (!isDone) {
			String filename = null;
			filename = s.nextLine();
			try {
				simComp.initializePop(10, filename);
				isDone = true;
			} catch (InvalidChromosomeFormatException e) {
				System.err.println("Invalid file content: 0s and 1s only");
			} catch (FileNotFoundException e) {
				System.err.println("File not found. Please try again.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		s.close();
		
		JFrame frame = new JFrame();

		frame.setSize(600, 600);
		frame.setTitle("Hi");

		frame.add(this.simComp);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		System.out.println("App terminated");

	} // runApp

	/**
	 * ensures: runs the application
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		SimulatorViewer mainApp = new SimulatorViewer();
		mainApp.runApp();
	} // main

}