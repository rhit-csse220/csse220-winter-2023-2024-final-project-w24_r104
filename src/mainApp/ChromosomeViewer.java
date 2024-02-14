package mainApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
public class ChromosomeViewer {

	public ChromosomeViewer(Population population, Timer t) {
		JFrame frame = new JFrame();
		JPanel buttonPanel = new JPanel();
		ChromosomeComponent chromosomeComp = new ChromosomeComponent(population);

		JLabel chromosomeFileLabel = new JLabel("Randomly Generated Chromosome");

		JButton loadButton = new JButton("Load");
		JButton saveButton = new JButton("Save");

		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = System.getProperty("user.dir");
				JFileChooser chooser = new JFileChooser(s);
				int result = chooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					String newFileName = selectedFile.getAbsolutePath();
					chromosomeFileLabel.setText(newFileName);
					try {
						chromosomeComp.initializePopFromFile(10, newFileName);
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

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = System.getProperty("user.dir");
				JFileChooser chooser = new JFileChooser(s);
				int result = chooser.showSaveDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					if (chooser.getDialogType() == JFileChooser.SAVE_DIALOG) {
						try {
							if (selectedFile.exists()) {
								String newFile = selectedFile.getName();
								FileWriter fw = new FileWriter(newFile);
								fw.write(chromosomeComp.populationToString());
								fw.close();
							} else {
								File newFile = new File(selectedFile.getName());
								FileWriter fw = new FileWriter(newFile);
								fw.write(chromosomeComp.populationToString());
								fw.close();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		t.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chromosomeComp.repaint();
			}
		});

		frame.add(chromosomeComp, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(loadButton, BorderLayout.SOUTH);
		buttonPanel.add(saveButton, BorderLayout.SOUTH);

		frame.add(chromosomeFileLabel, BorderLayout.NORTH);
		frame.setTitle("Chromosome Viewer");
		frame.setSize(Population.ALLELE_SIDE_LENGTH * 120, Population.ALLELE_SIDE_LENGTH * 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Additional frame for visualizing the fittest
		// Best Fit Individual side length = 20
		JFrame bestFitFrame = new JFrame("Best Fit Individual");
		ChromosomeComponent bestFitComponent = new BestFitComponent(population, 20);
		JLabel bestFitLabel = new JLabel("Fittest Individual");
		bestFitComponent.addMouseListener(new MutationClickListener(bestFitLabel, bestFitComponent, 20));

		t.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bestFitComponent.repaint();
			}
		});

		bestFitFrame.add(bestFitComponent, BorderLayout.CENTER);
		bestFitFrame.add(bestFitLabel, BorderLayout.NORTH);
		bestFitFrame.setSize(Population.ALLELE_SIDE_LENGTH * 50, Population.ALLELE_SIDE_LENGTH * 55);
		bestFitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bestFitFrame.setVisible(true);
<<<<<<< HEAD

=======
>>>>>>> branch 'master' of https://github.com/rhit-csse220/csse220-winter-2023-2024-final-project-w24_r104.git
	}
}