package mainApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Class: DataVisualizationViewer
 * 
 * @author W24_R104 <br>
 *         Purpose: Displays the frame that shows the fitness tracking graph
 *         			and allows user to change evolution parameters at runtime.
 *         <br>
 *         Restrictions: None
 */
public class DataVisualizationViewer {
	public static final int TEXTFIELD_SIZE = 3;
	public static final int FRAME_WIDTH = 1100;
	public static final int FRAME_HEIGHT = 500;

	public DataVisualizationViewer(Population population, Timer t) {
		JFrame frame = new JFrame();
		JLabel label = new JLabel("Fitness Over Generations", SwingConstants.CENTER);
		DataVisualizationComponent dataComp = new DataVisualizationComponent(population);

		JPanel buttonPanel = new JPanel();

		// Create a text field to get user input on mutation rate
		JLabel mRateText = new JLabel("Mutation Rate (N/pop)");
		JTextField promptMRate = new JTextField(TEXTFIELD_SIZE);
		promptMRate.setText("1");

		// Create a drop down menu for user to choose selection method
		JLabel selectionText = new JLabel("Selection");
		String[] selectionChoices = { "Truncation", "Roulette Wheel", "Rank" };
		final JComboBox<String> selectionMenu = new JComboBox<String>(selectionChoices);

		// Ask if crossover is implemented
		JCheckBox crossoverCheckBox = new JCheckBox("Crossover?", true);
		crossoverCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);

		// Create a text field to get user input on population size
		JLabel populationSizeText = new JLabel("Population Size");
		JTextField promptPopSize = new JTextField(TEXTFIELD_SIZE);
		promptPopSize.setText("100");

		// Create a text field to get user input on number of generations
		JLabel generationSizeText = new JLabel("Generations");
		JTextField promptGenSize = new JTextField(TEXTFIELD_SIZE);
		promptGenSize.setText("200");

		// Create a text field to get user input on length of genome
		JLabel genomeLengthText = new JLabel("Genome Length");
		JTextField promptGenomeLength = new JTextField(TEXTFIELD_SIZE);
		promptGenomeLength.setText(Integer.toString(Population.DESIRED_SOLUTION_FITNESS));

		// Create a text field to get user input on % of elitism
		JLabel elitismText = new JLabel("Elitism %");
		JTextField promptElitism = new JTextField(TEXTFIELD_SIZE);
		promptElitism.setText("1");

		// Create a button to start evolution loop
		JButton startEvolutionButton = new JButton("Start Evolution");
		startEvolutionButton.addActionListener(new ActionListener() {

			private boolean firstTimeClicked = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (firstTimeClicked) {
					population.initializeRandomly(Integer.parseInt(promptPopSize.getText()),
							Integer.parseInt(promptGenomeLength.getText()), Double.parseDouble(promptMRate.getText()),
							crossoverCheckBox.isSelected(), Double.parseDouble(promptElitism.getText()),
							selectionMenu.getSelectedItem().toString());
					t.start();
					firstTimeClicked = false;
				} else if (!firstTimeClicked) {
					if (!t.isRunning()) {
						t.start();
					} else
						t.stop();
				}
			}
		});
		
		frame.setVisible(true);
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
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		t.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (population.getNumGenerations() < Integer.parseInt(promptGenSize.getText()))
					dataComp.update();
			}
		});
	}

}
