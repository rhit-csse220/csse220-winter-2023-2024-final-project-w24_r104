package mainApp;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	private void runApp() throws IOException {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JButton mutateButton = new JButton("Mutate");
		mutateButton.addActionListener(new MutationListener());
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new LoadListener());
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveListener());

		JLabel chromosomeFileLabel = new JLabel();
		JLabel mRate = new JLabel("M Rate: _/N");
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter chromosome file:  (e.g. scores.txt)");

		boolean isDone = false;
		while (!isDone) {
			String filename = JOptionPane.showInputDialog("Enter file name:");
			chromosomeFileLabel.setText(filename);
			try {
				simComp.initializePop(10, filename);
				isDone = true;
				frame.setTitle(filename);
			} catch (InvalidChromosomeFormatException e) {
				JOptionPane.showMessageDialog(panel, "Invalid file content", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.err.println("Invalid file content: 0s and 1s only");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(panel, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.err.println("File not found. Please try again.");

			} catch (IOException e) {
				e.printStackTrace();
			}
			s.close();
		}

		frame.add(this.simComp);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		
		buttonPanel.add(mutateButton, BorderLayout.NORTH);
		buttonPanel.add(mRate, BorderLayout.NORTH);
		buttonPanel.add(loadButton, BorderLayout.SOUTH);
		buttonPanel.add(saveButton, BorderLayout.SOUTH);
		
		frame.add(chromosomeFileLabel, BorderLayout.NORTH);
//		frame.pack();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		System.out.println("App terminated");

	} // runApp

	/**
	 * ensures: runs the application
	 * 
	 * @param args unused
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SimulatorViewer mainApp = new SimulatorViewer();
		mainApp.runApp();
	} // main

}