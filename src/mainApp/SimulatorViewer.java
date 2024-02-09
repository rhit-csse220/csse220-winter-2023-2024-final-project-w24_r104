package mainApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * Class: MainApp
 * 
 * @author W24_R104 <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 */
public class SimulatorViewer {

	public SimulatorViewer(Population population, Timer t) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		SimulatorComponent simComp = new SimulatorComponent(100, population);

		JLabel chromosomeFileLabel = new JLabel();
		JLabel mRate = new JLabel("M Rate: _/N");
		JTextField promptMRate = new JTextField(10);

		JButton mutateButton = new JButton("Mutate");
		mutateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!promptMRate.getText().equals("")) {
					String userInput = promptMRate.getText();
					simComp.setPopMutationRateAndMutate(Double.parseDouble(userInput));
					if (!chromosomeFileLabel.getText().endsWith(" (mutated)"))
						chromosomeFileLabel.setText(chromosomeFileLabel.getText() + " (mutated)");
				}
			}
		});

		chromosomeFileLabel.setText("Randomly Generated Chromsome");

		JButton loadButton = new JButton("Load");
		JButton saveButton = new JButton("Save");

		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = System.getProperty("user.dir");
				JFileChooser chooser = new JFileChooser(s);
				int result = chooser.showOpenDialog(panel);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					String newFileName = selectedFile.getAbsolutePath();
					chromosomeFileLabel.setText(newFileName);
					try {
						simComp.initializePopFromFile(10, newFileName);
					} catch (InvalidChromosomeFormatException exception) {
						JOptionPane.showMessageDialog(frame, "Invalid file content", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						System.err.println("Invalid file content: 0s and 1s only");
					} catch (FileNotFoundException exception) {
						JOptionPane.showMessageDialog(frame, "File not found", "ERROR", JOptionPane.ERROR_MESSAGE);
						System.err.println("File not found. Please try again.");

					} catch (IOException exception) {
						exception.printStackTrace();
					}
				}
			}
		});

		simComp.addMouseListener(new MutationClickListener(chromosomeFileLabel, simComp));

		frame.add(simComp, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.EAST);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(mutateButton, BorderLayout.NORTH);
		buttonPanel.add(mRate, BorderLayout.NORTH);
		buttonPanel.add(promptMRate, BorderLayout.NORTH);
		buttonPanel.add(loadButton, BorderLayout.SOUTH);
		buttonPanel.add(saveButton, BorderLayout.SOUTH);

		frame.add(chromosomeFileLabel, BorderLayout.NORTH);
		frame.setTitle("Chromosome Viewer");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		System.out.println("App terminated");

	}
}