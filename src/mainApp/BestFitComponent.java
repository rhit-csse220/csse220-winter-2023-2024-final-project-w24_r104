package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
/**
 * Class: BestFitComponent
 * 
 * @author W24_R104 <br>
 *         Purpose: Inherits the ChromosomeComponent class to display only the individual with the highest fitness
 *         <br>
 *         Restrictions: None
 *         <br>For example: 
 * 		   <pre>
 *    	   BestFitComponent bestFitComponent = new BestFitComponent(Population new Population(), 5);
 * 		   </pre>
 */
public class BestFitComponent extends ChromosomeComponent {
	private Population population;
	private int sideLength;

	public BestFitComponent(Population population, int sideLength) {
		super(population);
		this.population = population;
		this.sideLength = sideLength;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (this.population.hasRunEvolutionaryLoop() == true) {
			this.population.getFittestIndividual().drawOn(g2, 0, 0, this.sideLength);
			g2.drawString("Fitness: " + this.population.getBestFitness(), 0, this.sideLength * 11);
		}
	}

	@Override
	public void mutateSquare(int x, int y, int sideLength) {
		super.mutateSquare(x, y, sideLength);
	}
}
