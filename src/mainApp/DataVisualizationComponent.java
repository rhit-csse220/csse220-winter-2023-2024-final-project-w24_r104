package mainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class DataVisualizationComponent extends JComponent {
	private Population population;
	private int generations;
	public static final int GRAPH_OFFSET_FROM_BORDER = 40;
	public static final int HORIZONTAL_UNIT_WIDTH = (DataVisualizationViewer.FRAME_WIDTH - 3 * GRAPH_OFFSET_FROM_BORDER)
			/ 10;
	public static final int VERTICAL_UNIT_WIDTH = (DataVisualizationViewer.FRAME_HEIGHT - 4 * GRAPH_OFFSET_FROM_BORDER)
			/ 10;
	public static final int AXES_DIVISOR_LENGTH = 10;
	public static final int LINE_WIDTH = 6;
	
	private ArrayList<Line2D.Double> bestLines = new ArrayList<Line2D.Double>();
	private ArrayList<Line2D.Double> averageLines = new ArrayList<Line2D.Double>();
	private ArrayList<Line2D.Double> leastLines = new ArrayList<Line2D.Double>();
	private Point2D.Double lastBestPoint;
	private Point2D.Double lastAveragePoint;
	private Point2D.Double lastLeastPoint;

	public DataVisualizationComponent(Population population) {
		this.population = population;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Graph bounding box
		g2.drawRect(GRAPH_OFFSET_FROM_BORDER, GRAPH_OFFSET_FROM_BORDER, HORIZONTAL_UNIT_WIDTH * 10,
				VERTICAL_UNIT_WIDTH * 10);

		// y-axis unit
		g2.drawString("Fitness", GRAPH_OFFSET_FROM_BORDER - 5, GRAPH_OFFSET_FROM_BORDER - 10);

		// x-axis unit
		g2.drawString("Generation", DataVisualizationViewer.FRAME_WIDTH - 2 * GRAPH_OFFSET_FROM_BORDER + 5,
				(DataVisualizationViewer.FRAME_HEIGHT - 3 * GRAPH_OFFSET_FROM_BORDER) - 5);

		// Draw axes
		for (int i = 0; i < 11; i++) {
			g2.drawLine(GRAPH_OFFSET_FROM_BORDER - AXES_DIVISOR_LENGTH / 2,
					GRAPH_OFFSET_FROM_BORDER + i * VERTICAL_UNIT_WIDTH,
					GRAPH_OFFSET_FROM_BORDER + AXES_DIVISOR_LENGTH / 2,
					GRAPH_OFFSET_FROM_BORDER + i * VERTICAL_UNIT_WIDTH); // y-axis
			// indexing
			g2.drawString("" + 20 * (10 - i), GRAPH_OFFSET_FROM_BORDER - AXES_DIVISOR_LENGTH / 2 - 20,
					GRAPH_OFFSET_FROM_BORDER + i * VERTICAL_UNIT_WIDTH + 4);

			g2.drawLine(GRAPH_OFFSET_FROM_BORDER + i * HORIZONTAL_UNIT_WIDTH,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10 - AXES_DIVISOR_LENGTH / 2,
					GRAPH_OFFSET_FROM_BORDER + i * HORIZONTAL_UNIT_WIDTH,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10 + AXES_DIVISOR_LENGTH / 2); // x-axis
			// indexing
			g2.drawString("" + 20 * i, GRAPH_OFFSET_FROM_BORDER + i * HORIZONTAL_UNIT_WIDTH - 5,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10 + AXES_DIVISOR_LENGTH / 2 + 15);
		}
//
//		Stroke originalStroke = g2.getStroke();
//		BasicStroke stroke = new BasicStroke(10);
//		g2.setStroke(stroke);
		this.population.printIndividuals();
		g2.setColor(Color.GREEN);
		Ellipse2D.Double bestLine = new Ellipse2D.Double(GRAPH_OFFSET_FROM_BORDER,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
						- this.population.getBestFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH,
				LINE_WIDTH, LINE_WIDTH);
		g2.fill(bestLine);
		g2.setColor(Color.ORANGE);
		Ellipse2D.Double avgLine = new Ellipse2D.Double(GRAPH_OFFSET_FROM_BORDER,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
						- this.population.getAverageFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH,
				LINE_WIDTH, LINE_WIDTH);
		g2.fill(avgLine);
		g2.setColor(Color.RED);
		Ellipse2D.Double leastLine = new Ellipse2D.Double(GRAPH_OFFSET_FROM_BORDER,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
						- this.population.getLeastFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH,
				LINE_WIDTH, LINE_WIDTH);
		g2.fill(leastLine);
		
		if (this.population == null) {
			lastBestPoint = new Point2D.Double(GRAPH_OFFSET_FROM_BORDER,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
					- this.population.getBestFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH);
			lastAveragePoint = new Point2D.Double(GRAPH_OFFSET_FROM_BORDER,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
					- this.population.getAverageFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH);
			lastLeastPoint  = new Point2D.Double(GRAPH_OFFSET_FROM_BORDER,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
					- this.population.getLeastFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH);
		} else {
			Line2D.Double newBestLine = new Line2D.Double();
			Point2D.Double newBestPoint = new Point2D.Double(lastBestPoint.x + GRAPH_OFFSET_FROM_BORDER, GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
					- this.population.getBestFitness() / 100.0 * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH);
			newBestLine.setLine(lastBestPoint, newBestPoint);
			bestLines.add(newBestLine);
			lastBestPoint = newBestPoint;
			g2.setColor(Color.GREEN);
			for (Line2D.Double line : bestLines) {
				g2.draw(line);
			}
		}
	}

	public void setGeneration(int generations) {
		this.generations = generations;
	}

	public void addEntry() {

	}

	public void reset() {

	}

	public void update() {
		repaint();
	}

}
