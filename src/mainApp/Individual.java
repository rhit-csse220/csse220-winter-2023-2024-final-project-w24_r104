package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Random;

public class Individual {

	public static final int NUM_COLUMNS = 10;

	private int[] chromosome;
	private int alleleSideLength;

	public Individual(int[] chromosome) {
		this.chromosome = chromosome;
		this.alleleSideLength = 50;
		calculateSimpleFitness();
	}

	public Individual() {
		Random r = new Random();
		int[] chromosome = new int[100];
		for (int i = 0; i < 100; i++) {
			chromosome[i] = r.nextInt(1);
		}
		this.chromosome = chromosome;
		calculateSimpleFitness();
	}

	public int calculateSimpleFitness() {
		int fitnessScore = 0;
		for (int allele : this.chromosome) {
			fitnessScore += allele;
		}
		return fitnessScore;
	}

	public int calculateMatchingFitness(int[] chromosome) {
		if (chromosome.length != this.chromosome.length) {
			return 0;
		} else {
			int fitnessScore = 0;
			for (int i = 0; i < chromosome.length; i++) {
				if (this.chromosome[i] == chromosome[i]) {
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
		for (int i = 0; i < chromosome.length; i++) {
			Random r = new Random();
			if (r.nextInt(chromosome.length) < chromosome.length * mutationRate) {
				if (chromosome[i] == 0)
					chromosome[i] = 1;
				else
					chromosome[i] = 0;
			}
		}
		calculateSimpleFitness();
	}

	public void mutateOneCell(int x, int y) {
		int xCoord = x / this.alleleSideLength;
		int yCoord = y / this.alleleSideLength;
		int cellCoord = xCoord + yCoord * NUM_COLUMNS;
		if (this.chromosome.length > cellCoord && xCoord <= NUM_COLUMNS - 1) {
			if (chromosome[cellCoord] == 0)
				chromosome[cellCoord] = 1;
			else
				chromosome[cellCoord] = 0;
		}
	}

	public void drawOn(Graphics2D g2) {
		System.out.println(this.chromosomeToString());
		int x = 0;
		int y = 0;
		for (int i = 0; i < chromosome.length / NUM_COLUMNS; i++) { // iterates through rows
			for (int j = 0; j < NUM_COLUMNS; j++) { // iterates through columns
				if (this.chromosome[NUM_COLUMNS * i + j] == 0) {
					g2.setColor(Color.BLACK);
					Rectangle geneRect = new Rectangle(x, y, this.alleleSideLength, this.alleleSideLength);
					g2.fill(geneRect);
					g2.setColor(Color.WHITE);
					g2.drawString("" + Integer.toString(NUM_COLUMNS * i + j), x, y + this.alleleSideLength);
				} else {
					g2.setColor(Color.GREEN);
					Rectangle geneRect = new Rectangle(x, y, this.alleleSideLength, this.alleleSideLength);
					g2.fill(geneRect);
					g2.setColor(Color.BLACK);
					g2.drawString("" + Integer.toString(NUM_COLUMNS * i + j), x, y + this.alleleSideLength);
				}
				x += this.alleleSideLength;
			}
			x = 0;
			y += this.alleleSideLength;
		}
	}

	public int[] getChromosome() {
		return this.chromosome;
	}

	public String chromosomeToString() {
		String chromosomeString = "";
		for (int i : this.chromosome) {
			chromosomeString += "" + i;
		}
		System.out.println(chromosomeString);
		return chromosomeString;
	}

}
