package TestNewDesign;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

import mainApp.Individual;

public class ChromosomeComponent extends JComponent{
	private Individual individual;
	
	public ChromosomeComponent(Individual individual) {
		this.individual = individual;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		this.individual.drawOn(g2);
	}

}
