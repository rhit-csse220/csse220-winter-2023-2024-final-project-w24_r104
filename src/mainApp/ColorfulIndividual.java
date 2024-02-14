package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;

public class ColorfulIndividual extends Individual {
	
	private Color color;
	
	public ColorfulIndividual(int[] chromosome) {
		super(chromosome);
		this.color = getColor();
	}

	@Override
	public void drawOn(Graphics2D g2, int x, int y, int sideLength) {
		g2.setColor(this.color);
		g2.fillOval(x, y, 10*sideLength, 10*sideLength);
	}
	
	public Color getColor() {
		int r = Integer.parseInt(this.chromosomeToString().substring(0, 8), 2);
		int g = Integer.parseInt(this.chromosomeToString().substring(8, 16), 2);
		int b = Integer.parseInt(this.chromosomeToString().substring(16, 24), 2);
		return new Color(r, g, b);
	}
	
}
