package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Individual {

	public static final int NUM_COLUMNS = 10;
	
	private int[] chromosome;
	private int fitnessScore;
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

	public void calculateSimpleFitness() {
		int fitnessScore = 0;
		for (int allele : this.chromosome) {
			fitnessScore += allele;
		}
		this.fitnessScore = fitnessScore;
	}

	public void calculateMatchingFitness() {

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
		int xCoord = x/this.alleleSideLength;
		int yCoord = y/this.alleleSideLength;
		int cellCoord = xCoord + yCoord*NUM_COLUMNS;
		if (this.chromosome.length > cellCoord && xCoord <= NUM_COLUMNS - 1) {
			if (chromosome[cellCoord] == 0)
				chromosome[cellCoord] = 1;
			else
				chromosome[cellCoord] = 0;
		}
	}

	public void saveCurrentChromosome() {

	}

	public void loadChromosomeFromFile(String filename) {

	}

	public void drawOn(Graphics2D g2) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < chromosome.length / NUM_COLUMNS; i++) { // iterates through rows
			for (int j = 0; j < NUM_COLUMNS; j++) { // iterates through columns
				if (this.chromosome[NUM_COLUMNS * i + j] == 0) {
					g2.setColor(Color.BLACK);
					Rectangle geneRect = new Rectangle(x, y, this.alleleSideLength, this.alleleSideLength);
					g2.fill(geneRect);
					g2.setColor(Color.WHITE);
					g2.drawString("" + Integer.toString(NUM_COLUMNS*i + j), x, y + this.alleleSideLength);
				} else {
					g2.setColor(Color.GREEN);
					Rectangle geneRect = new Rectangle(x, y, this.alleleSideLength, this.alleleSideLength);
					g2.fill(geneRect);
					g2.setColor(Color.BLACK);
					g2.drawString("" + Integer.toString(NUM_COLUMNS*i + j), x, y + this.alleleSideLength);
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

	public int getFitnessScore() {
		return fitnessScore;
	}

}
