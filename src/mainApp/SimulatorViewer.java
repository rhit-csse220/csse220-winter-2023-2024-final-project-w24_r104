package mainApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.stream.FileCacheImageInputStream;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

/**
 * Class: MainApp
 * 
 * @author W24_R104 <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 */
public class SimulatorViewer {

	public SimulatorViewer() {

	}

	private void runApp() throws IOException {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		SimulatorComponent simComp = new SimulatorComponent(10);

		JLabel chromosomeFileLabel = new JLabel();
		JLabel mRate = new JLabel("M Rate: _/N");
		JTextField promptMRate = new JTextField(10);

		JButton mutateButton = new JButton("Mutate");
		mutateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!promptMRate.getText().equals("")) {
					String userInput = promptMRate.getText();
					simComp.setPopMutationRateAndMutate(Integer.parseInt(userInput));
				}
			}
		});

		Scanner s = new Scanner(System.in);

		Individual firstIndividual = null;
		boolean isDone = false;
		String filename;

		while (!isDone) {
			filename = JOptionPane.showInputDialog("Enter file name:");
			chromosomeFileLabel.setText(filename);
			try {
				firstIndividual = simComp.initializePop(10, filename);
				isDone = true;
			} catch (InvalidChromosomeFormatException e) {
				JOptionPane.showMessageDialog(frame, "Invalid file content", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.err.println("Invalid file content: 0s and 1s only");
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
				System.err.println("File not found. Please try again.");

			} catch (IOException e) {
				e.printStackTrace();
			}
			s.close();
		}

		JButton loadButton = new JButton("Load");
		JButton saveButton = new JButton("Save");

		SaveLoadListener saveLoadListener = new SaveLoadListener(panel, chromosomeFileLabel);
		loadButton.addActionListener(saveLoadListener);
		saveButton.addActionListener(saveLoadListener);

		frame.add(simComp, BorderLayout.CENTER);
//		frame.add(new ChromosomeComponent(firstIndividual), BorderLayout.CENTER);
		frame.add(panel, BorderLayout.EAST);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(mutateButton, BorderLayout.NORTH);
		buttonPanel.add(mRate, BorderLayout.NORTH);
		buttonPanel.add(promptMRate, BorderLayout.NORTH);
		buttonPanel.add(loadButton, BorderLayout.SOUTH);
		buttonPanel.add(saveButton, BorderLayout.SOUTH);

		frame.add(chromosomeFileLabel, BorderLayout.NORTH);
		frame.setTitle("Chromosome Viewer");
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

	class SaveLoadListener implements ActionListener {
		private JPanel panel;
		private JLabel label;

		public SaveLoadListener(JPanel panel, JLabel label) {
			// TODO Auto-generated constructor stub
			this.panel = panel;
			this.label = label;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String s = System.getProperty("user.dir");
			JFileChooser chooser = new JFileChooser(s);
//					FileNameExtensionFilter filter = new FileNameExtensionFilter("txt");
//					chooser.setFileFilter(filter);
			int result = chooser.showOpenDialog(this.panel);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				this.label.setText(selectedFile.getAbsolutePath());
			}
		}
	}
}
