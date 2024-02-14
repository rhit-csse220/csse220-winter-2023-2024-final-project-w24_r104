package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Individual implements Comparable<Individual> {

	public static final int NUM_COLUMNS = 10;
	private int[] chromosome;

	public Individual(int[] chromosome) {
		this.chromosome = chromosome;
	}

	public Individual() {
		Random r = new Random();
		int[] chromosome = new int[100];
		for (int i = 0; i < 100; i++) {
			chromosome[i] = r.nextInt(1);
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

	public int calculateMatchingSmileyFitness() {
		int[] smileyChromosome = new int[100];
		for (int i = 0; i < smileyChromosome.length; i++)
			smileyChromosome[i] = 1;
		smileyChromosome[22] = 0;
		smileyChromosome[27] = 0;
		smileyChromosome[71] = 0;
		smileyChromosome[78] = 0;
		for (int i = 81; i < 89; i++)
			smileyChromosome[i] = 0;

		if (smileyChromosome.length != this.chromosome.length) {
			return 0;
		} else {
			int fitnessScore = 0;
			for (int i = 0; i < smileyChromosome.length; i++) {
				if (this.chromosome[i] == smileyChromosome[i]) {
					fitnessScore++;
				}
			}
			return fitnessScore;
		}
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
		int thisX = x;
		int thisY = y;
		for (int i = 0; i < chromosome.length / NUM_COLUMNS; i++) { // iterates through rows
			for (int j = 0; j < NUM_COLUMNS; j++) { // iterates through columns
				if (this.chromosome[NUM_COLUMNS * i + j] == 0) {
					g2.setColor(Color.BLACK);
					Rectangle geneRect = new Rectangle(thisX, thisY, sideLength, sideLength);
					g2.fill(geneRect);
				} else {
					g2.setColor(Color.GREEN);
					Rectangle geneRect = new Rectangle(thisX, thisY, sideLength, sideLength);
					g2.fill(geneRect);
					g2.setColor(Color.BLACK);
				}
				thisX += sideLength;
			}
			thisX = x;
			thisY += sideLength;
		}
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
		return other.calculateSimpleFitness() - this.calculateSimpleFitness();
	}

	@Override
	public String toString() {
		return "Individual with Fitness=" + calculateSimpleFitness();
	}

	@Override
	protected Individual clone() {
		return new Individual(this.chromosome.clone());
	}

	public int getFitness(String fitnessMethodName) {
		if (fitnessMethodName.equals("Simple"))
			return calculateSimpleFitness();
		else if (fitnessMethodName.equals("Matching Smiley Face"))
			return calculateMatchingSmileyFitness();
		else
			return calculateMaxConsecutive1sFitness();
	}

}
