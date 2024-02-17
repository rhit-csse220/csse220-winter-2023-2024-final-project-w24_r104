package expMainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.Timer;

import mainApp.DataVisualizationViewer;

public class ExpAllelesComponent extends JComponent {
	private ExpPopulation population;
	private Timer t;

	public static final int GRAPH_OFFSET_FROM_BORDER = 40;
	public static final int HORIZONTAL_UNIT_WIDTH = 2 + (ExpAllelesViewer.FRAME_WIDTH - 3 * GRAPH_OFFSET_FROM_BORDER) / 10;
	public static final int VERTICAL_UNIT_WIDTH = (ExpAllelesViewer.FRAME_HEIGHT - 4 * GRAPH_OFFSET_FROM_BORDER) / 10;
	public static final int AXES_DIVISOR_LENGTH = 10;
	public static final int LINE_WIDTH = 3;

	private ArrayList<Point2D.Double> zeroAlleles = new ArrayList<Point2D.Double>();
	private ArrayList<Point2D.Double> oneAlleles = new ArrayList<Point2D.Double>();
	private ArrayList<Point2D.Double> unknownAlleles = new ArrayList<Point2D.Double>();

	public ExpAllelesComponent(ExpPopulation pop) {
		this.population = pop;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Graph bounding box
		g2.drawRect(GRAPH_OFFSET_FROM_BORDER, GRAPH_OFFSET_FROM_BORDER, HORIZONTAL_UNIT_WIDTH * 10,
				VERTICAL_UNIT_WIDTH * 10);

		// y-axis unit
		g2.drawString("Frequencies", GRAPH_OFFSET_FROM_BORDER - 5, GRAPH_OFFSET_FROM_BORDER - 10);

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
			g2.drawString(String.format("%1$,.1f", 0.1 * (10 - i)),
					GRAPH_OFFSET_FROM_BORDER - AXES_DIVISOR_LENGTH / 2 - 20,
					GRAPH_OFFSET_FROM_BORDER + i * VERTICAL_UNIT_WIDTH + 4);

			g2.drawLine(GRAPH_OFFSET_FROM_BORDER + i * HORIZONTAL_UNIT_WIDTH,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10 - AXES_DIVISOR_LENGTH / 2,
					GRAPH_OFFSET_FROM_BORDER + i * HORIZONTAL_UNIT_WIDTH,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10 + AXES_DIVISOR_LENGTH / 2); // x-axis
			// indexing
			g2.drawString("" + 5 * i, GRAPH_OFFSET_FROM_BORDER + i * HORIZONTAL_UNIT_WIDTH - 5,
					GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10 + AXES_DIVISOR_LENGTH / 2 + 15);
		}
//		Stroke originalStroke = g2.getStroke();
		BasicStroke stroke = new BasicStroke(LINE_WIDTH);
		g2.setStroke(stroke);

		g2.setColor(Color.GREEN);
		for (int i = 0; i < zeroAlleles.size() - 1; i++) {
			g2.drawLine((int) zeroAlleles.get(i).x, (int) zeroAlleles.get(i).y, (int) zeroAlleles.get(i + 1).x,
					(int) zeroAlleles.get(i + 1).y);
		}
		g2.setColor(Color.ORANGE);
		for (int i = 0; i < oneAlleles.size() - 1; i++) {
			g2.drawLine((int) oneAlleles.get(i).x, (int) oneAlleles.get(i).y, (int) oneAlleles.get(i + 1).x,
					(int) oneAlleles.get(i + 1).y);
		}
		g2.setColor(Color.RED);
		for (int i = 0; i < unknownAlleles.size() - 1; i++) {
			g2.drawLine((int) unknownAlleles.get(i).x, (int) unknownAlleles.get(i).y, (int) unknownAlleles.get(i + 1).x,
					(int) unknownAlleles.get(i + 1).y);
		}


		g2.setColor(Color.GREEN);
		g2.fill(new Rectangle.Double(GRAPH_OFFSET_FROM_BORDER + 8 * HORIZONTAL_UNIT_WIDTH,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 5, 15, 15));
		g2.setColor(Color.ORANGE);
		g2.fill(new Rectangle.Double(GRAPH_OFFSET_FROM_BORDER + 8 * HORIZONTAL_UNIT_WIDTH,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 6, 15, 15));
		g2.setColor(Color.RED);
		g2.fill(new Rectangle.Double(GRAPH_OFFSET_FROM_BORDER + 8 * HORIZONTAL_UNIT_WIDTH,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 7, 15, 15));

		g2.setColor(Color.BLACK);
		g2.drawString("0", GRAPH_OFFSET_FROM_BORDER + 8 * HORIZONTAL_UNIT_WIDTH + 20,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 5 + 10);
		g2.drawString("1", GRAPH_OFFSET_FROM_BORDER + 8 * HORIZONTAL_UNIT_WIDTH + 20,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 6 + 10);
		g2.drawString("?", GRAPH_OFFSET_FROM_BORDER + 8 * HORIZONTAL_UNIT_WIDTH + 20,
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 7 + 10);
		System.out.println("ZERO: " + zeroAlleles);
		System.out.println("ONE: " + oneAlleles);
		System.out.println("? :" + unknownAlleles);

	}

	public void update() {
		zeroAlleles.add(new Point2D.Double(GRAPH_OFFSET_FROM_BORDER + 8 * (zeroAlleles.size()),
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
						- this.population.getZeroAllelesFrequencies() * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH + 3));
		oneAlleles.add(new Point2D.Double(GRAPH_OFFSET_FROM_BORDER + 8 * (oneAlleles.size()),
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
						- this.population.getOneAllelesFrequencies() * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH + 3));
		unknownAlleles.add(new Point2D.Double(GRAPH_OFFSET_FROM_BORDER + 8 * (unknownAlleles.size()),
				GRAPH_OFFSET_FROM_BORDER + VERTICAL_UNIT_WIDTH * 10
						- this.population.getUnknownAllelesFrequencies() * VERTICAL_UNIT_WIDTH * 10 - LINE_WIDTH + 3));
		repaint();
	}
}
