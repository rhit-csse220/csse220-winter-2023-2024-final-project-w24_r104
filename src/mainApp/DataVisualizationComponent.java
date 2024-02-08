package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class DataVisualizationComponent extends JComponent {
	private Population population;
	
	public DataVisualizationComponent(Population population) {
		this.population = population;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawRect(50, 50, 500, 300);
	}
	
	public void addEntry() {
		
	}
	
	public void reset() {
		
	}
	
	public void update() {
		
	}
	
}
