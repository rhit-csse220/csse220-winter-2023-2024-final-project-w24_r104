package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.net.SocketImpl;
import java.util.Random;

/**
 * Class: Individual
 * 
 * @author W24_R104 <br>
 *         Purpose: Stores the chromosome data of an organism and the methods used to evaluate, modify, and draw it.
 *         <br>
 *         Restrictions: None
 */
public class Individual implements Comparable<Individual> {

	
	public static final int[] SMILEY_CHROMOSOME = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1 };
	public static final boolean IS_COLORFUL = false;
	
	public static final int NUM_COLUMNS = 10;
	private int[] chromosome;
	// all individuals evolve to match the same ideal chromosome, hence the static variable
	private static int[] idealChromosome = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

	public Individual(int[] chromosome) {
		this.chromosome = chromosome;
	}

	public Individual() {
		Random r = new Random();
		int[] chromosome = new int[100];
		for (int i = 0; i < 100; i++) {
			chromosome[i] = r.nextInt(2);
		}
		this.chromosome = chromosome;
	}

	public int calculateSimpleFitness() {
		int fitnessScore = 0;
		for (int allele : this.chromosome) {
			fitnessScore += allele;
		}
		return fitnessScore;
	}

	public int calculateMatchingFitness(int[] chromosomeToMatch) {
		int smallerChromosomeLength = Math.min(chromosomeToMatch.length, this.chromosome.length);
		int fitness = 0;
		for (int i = 0; i < smallerChromosomeLength; i++) {
			if (chromosomeToMatch[i] == this.chromosome[i])
				fitness++;
		}
		return fitness;
	}

	public int calculateMaxConsecutive1sFitness() {
		int fitnessScore = 0;
		int consecutive1s = 0;
		for (int i = 0; i < this.chromosome.length; i++) {
			if (this.chromosome[i] == 1) {
				consecutive1s += 1;
			} else {
				if (consecutive1s > fitnessScore)
					fitnessScore = consecutive1s;
				consecutive1s = 0;
			}
		}
		return fitnessScore;
	}

	public void mutate(double mutationRate) {
		Random r = new Random();
		for (int i = 0; i < chromosome.length; i++) {
			if (r.nextDouble() <= mutationRate)
				switchAlleleAtIndex(i);
		}
	}

	public void mutateOneCell(int x, int y, int sideLength) {
		int xCoord = x / sideLength;
		int yCoord = y / sideLength;
		int cellCoord = xCoord + yCoord * NUM_COLUMNS;
		if (this.chromosome.length > cellCoord && xCoord <= NUM_COLUMNS - 1)
			switchAlleleAtIndex(cellCoord);
	}

	public void switchAlleleAtIndex(int i) {
		if (chromosome[i] == 0)
			chromosome[i] = 1;
		else
			chromosome[i] = 0;
	}

	public void drawOn(Graphics2D g2, int x, int y, int sideLength) {
		if (IS_COLORFUL) {
			g2.setColor(getColor());
			g2.fillOval(x, y, 10*sideLength, 10*sideLength);
			
			g2.setColor(Color.BLACK);
			if (this.getFitness(Population.FITNESS_CALCULATION_METHOD) < Population.DESIRED_SOLUTION_FITNESS) {
				g2.fillOval(x + 3*sideLength, y + 3*sideLength, sideLength, sideLength);
				g2.fillOval(x + 6*sideLength, y + 3*sideLength, sideLength, sideLength);
			} else {
				g2.drawArc(x + 3*sideLength, y + 3*sideLength, sideLength, sideLength, 0, 180);
				g2.drawArc(x + 6*sideLength, y + 3*sideLength, sideLength, sideLength, 0, 180);
			}
		} else {
			int thisX = x;
			int thisY = y;
			for (int i = 0; i < chromosome.length / NUM_COLUMNS; i++) { // iterates through rows
				for (int j = 0; j < NUM_COLUMNS; j++) { // iterates through columns
					if (this.chromosome[NUM_COLUMNS * i + j] == 0)
						g2.setColor(Color.BLACK);
					else if (this.chromosome[NUM_COLUMNS * i + j] == 1)
						g2.setColor(Color.GREEN);
					else
						g2.setColor(Color.YELLOW);
					Rectangle geneRect = new Rectangle(thisX, thisY, sideLength, sideLength);
					g2.fill(geneRect);
					thisX += sideLength;
				}
				thisX = x;
				thisY += sideLength;
			}
		}
	}
	
	public Color getColor() {
		int r = Integer.parseInt(this.chromosomeToString().substring(0, 8), 2);
		int g = Integer.parseInt(this.chromosomeToString().substring(8, 16), 2);
		int b = Integer.parseInt(this.chromosomeToString().substring(16, 24), 2);
		return new Color(r, g, b);
	}

	public int[] getChromosome() {
		return this.chromosome;
	}

	public String chromosomeToString() {
		String chromosomeString = "";
		for (int i : this.chromosome) {
			chromosomeString += i;
		}
		return chromosomeString;
	}

	@Override
	public int compareTo(Individual other) {
		return other.getFitness(Population.FITNESS_CALCULATION_METHOD) - this.getFitness(Population.FITNESS_CALCULATION_METHOD);
	}

	@Override
	public String toString() {
		return "Individual with Fitness=" + getFitness(Population.FITNESS_CALCULATION_METHOD);
	}

	@Override
	protected Individual clone() {
		return new Individual(this.chromosome.clone());
	}

	public int getFitness(String fitnessMethodName) {
		if (fitnessMethodName.equals("Simple"))
			return calculateSimpleFitness();
		else if (fitnessMethodName.equals("Matching"))
			return calculateMatchingFitness(idealChromosome);
		else
			return calculateMaxConsecutive1sFitness();
	}
	
	public static void setIdealChromosome(int[] idealChromosome) {
		Individual.idealChromosome = idealChromosome;
	}

}
