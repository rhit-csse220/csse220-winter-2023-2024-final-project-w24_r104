package mainApp;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataVisualizationViewer {
	public DataVisualizationViewer(Population population) {
		JFrame frame = new JFrame();
		JLabel label = new JLabel("Fitness Over Generations");
		DataVisualizationComponent dataComp = new DataVisualizationComponent(population);
		
		JPanel buttonPanel = new JPanel();
		
		JLabel mRateText = new JLabel("Mutation Rate (N/pop)");
		JTextField promptMRate = new JTextField(10);
		
		JLabel selectionText = new JLabel("Selection");
		JMenuBar selectionBar = new JMenuBar();
		JMenu menu = new JMenu();
		

		frame.add(dataComp, BorderLayout.CENTER);
		frame.add(label, BorderLayout.NORTH);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setSize(700, 500);
		frame.setLocation(500, 0);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
