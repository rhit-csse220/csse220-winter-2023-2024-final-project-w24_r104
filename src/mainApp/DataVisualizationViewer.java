package mainApp;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DataVisualizationViewer {
	public static final int TEXTFIELD_SIZE = 3;
	public static final int FRAME_WIDTH = 1100;
	public static final int FRAME_HEIGHT = 500;
	
	public DataVisualizationViewer(Population population) {
		JFrame frame = new JFrame();
		JLabel label = new JLabel("Fitness Over Generations");
		DataVisualizationComponent dataComp = new DataVisualizationComponent(population);

		JPanel buttonPanel = new JPanel();

		// Create a text field to get user input on mutation rate
		JLabel mRateText = new JLabel("Mutation Rate (N/pop)");
		JTextField promptMRate = new JTextField(TEXTFIELD_SIZE);

		// Create a drop down menu for user to choose selection method
		JLabel selectionText = new JLabel("Selection");
		String[] selectionChoices = { "Truncation", "Roulette Wheel", "Rank" };
		final JComboBox<String> selectionMenu = new JComboBox<String>(selectionChoices);

		// Ask if crossover is implemented
		JCheckBox crossoverCheckBox = new JCheckBox("Crossover?");
		crossoverCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);

		// Create a text field to get user input on population size
		JLabel populationSizeText = new JLabel("Population Size");
		JTextField promptPopSize = new JTextField(TEXTFIELD_SIZE);

		// Create a text field to get user input on number of generations
		JLabel generationSizeText = new JLabel("Generations");
		JTextField promptGenSize = new JTextField(TEXTFIELD_SIZE);

		// Create a text field to get user input on length of genome
		JLabel genomeLengthText = new JLabel("Genome Length");
		JTextField promptGenomeLength = new JTextField(TEXTFIELD_SIZE);

		// Create a text field to get user input on % of elitism
		JLabel elitismText = new JLabel("Elitism %");
		JTextField promptElitism = new JTextField(TEXTFIELD_SIZE);
		
		// Create a button to start evolution loop
		JButton startEvolutionButton = new JButton("Start Evolution");
		

		frame.add(dataComp, BorderLayout.CENTER);
		frame.add(label, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		buttonPanel.add(mRateText);
		buttonPanel.add(promptMRate);
		buttonPanel.add(selectionText);
		buttonPanel.add(selectionMenu);
		buttonPanel.add(crossoverCheckBox);
		buttonPanel.add(populationSizeText);
		buttonPanel.add(promptPopSize);
		buttonPanel.add(generationSizeText);
		buttonPanel.add(promptGenSize);
		buttonPanel.add(genomeLengthText);
		buttonPanel.add(promptGenomeLength);
		buttonPanel.add(elitismText);
		buttonPanel.add(promptElitism);
		buttonPanel.add(startEvolutionButton);

		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Evolution Viewer");
		frame.setLocation(0, 0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
