package mainApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.JComponent;
import javax.swing.Timer;

public class ExpAllelesComponent extends JComponent {
	private ExpPopulation population;
	private Timer t;

	public static final int GRAPH_OFFSET_FROM_BORDER = 40;
	public static final int HORIZONTAL_UNIT_WIDTH = 2 + (ExpAllelesViewer.FRAME_WIDTH - 3 * GRAPH_OFFSET_FROM_BORDER) // add
																														// 2
																														// to
																														// make
																														// it
																														// divide
																														// nicely
			/ 10;
	public static final int VERTICAL_UNIT_WIDTH = (ExpAllelesViewer.FRAME_HEIGHT - 4 * GRAPH_OFFSET_FROM_BORDER) / 10;
	public static final int AXES_DIVISOR_LENGTH = 10;
	public static final int LINE_WIDTH = 3;

	public ExpAllelesComponent(ExpPopulation pop, Timer t) {
		this.population = pop;
		this.t = t;
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

		// Documenting each line
//				Stroke dotted = new Stroke() {
//					
//					@Override
//					public Shape createStrokedShape(Shape p) {
//						// TODO Auto-generated method stub
//						return null;
//					}
//				}
		BasicStroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
		
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

	}

	public void update() {

		repaint();
	}
}
