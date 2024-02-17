package expMainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Class: ExpDataVisualizationViewer
 * 
 * @author W24_R104 <br>
 *         Purpose: Displays the frame that shows the frequencies of 0, 1, and ? alleles of a
 *         population over 50 generations.
 *         <br>
 *         Restrictions: None
 */
public class ExpAllelesViewer {
	public static final int FRAME_WIDTH = 500;
	public static final int FRAME_HEIGHT = 500;
	
	public ExpAllelesViewer(ExpPopulation pop, Timer t) {
		JFrame frame = new JFrame();
		frame.setTitle("Alleles Frequencies Over 50 Generations");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ExpAllelesComponent component = new ExpAllelesComponent(pop, t);
		frame.add(component);
		
		t.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				component.update();
			}
		});
		
		
	}
}
