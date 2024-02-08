package TestNewDesign;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainApp.Population;

public class ChromosomesViewer {
	private Population population;

	public ChromosomesViewer(Population population) {
		this.population = population;
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();

		JLabel chromosomeFileLabel = new JLabel();
		JLabel mRate = new JLabel("M Rate: _/N");
		JTextField promptMRate = new JTextField(10);
		ChromosomeComponent chromosomeComp = new ChromosomeComponent(population.getFirstIndividual());

		JButton mutateButton = new JButton("Mutate");
		mutateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!promptMRate.getText().equals("")) {
					String userInput = promptMRate.getText();
					population.setMutationRate(Double.parseDouble(userInput));
					population.mutate();
					if (!chromosomeFileLabel.getText().endsWith(" (mutated)"))
						chromosomeFileLabel.setText(chromosomeFileLabel.getText() + " (mutated)");
				}
			}
		});

//		GridLayout gridLayout = new GridLayout(population.size()/10 + 1, 10, 3, 3);
//		panel.setLayout(gridLayout);

		panel.add(chromosomeComp);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(mutateButton, BorderLayout.NORTH);
		frame.add(chromosomeFileLabel, BorderLayout.NORTH);
		buttonPanel.add(mRate, BorderLayout.NORTH);
		buttonPanel.add(promptMRate, BorderLayout.NORTH);
		
//		buttonPanel.add(loadButton, BorderLayout.SOUTH);
//		buttonPanel.add(saveButton, BorderLayout.SOUTH);

		frame.setTitle("Chromosome Viewer");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
