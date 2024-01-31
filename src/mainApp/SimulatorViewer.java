package mainApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JButton mutateButton = new JButton("Mutate");
		
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter chromosome file:  (e.g. scores.txt)");

		boolean isDone = false;
		while (!isDone) {
			String filename = null;
			filename = s.nextLine();
			try {
				simComp.initializePop(10, filename);
				isDone = true;
				frame.setTitle(filename);
			} catch (InvalidChromosomeFormatException e) {
				System.err.println("Invalid file content: 0s and 1s only");
			} catch (FileNotFoundException e) {
				System.err.println("File not found. Please try again.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		s.close();
		

		frame.add(this.simComp);
		frame.add(panel);
		panel.add(mutateButton);
		mutateButton.addActionListener(new MutationListener());

		frame.setSize(600, 600);
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