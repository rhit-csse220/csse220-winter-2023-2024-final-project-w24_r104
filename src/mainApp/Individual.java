package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Individual {

	private int[] chromosome;
	private int fitnessScore;

	public Individual(int[] chromosome) {
		this.chromosome = chromosome;
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

	public void saveCurrentChromosome() {

	}

	public void loadChromosomeFromFile(String filename) {

	}

	public void drawOn(Graphics2D g2) {
		int x = 0;
		int y = 0;
		int sideLength = 50;
		for (int i = 0; i < chromosome.length / 10; i++) { // to be changed to be applicable for different chromosome
															// lengths
			for (int j = 0; j < 10; j++) {
				if (this.chromosome[10 * i + j] == 0) {
					g2.setColor(Color.BLACK);
					Rectangle geneRect = new Rectangle(x, y, sideLength, sideLength);
					g2.fill(geneRect);
					g2.setColor(Color.WHITE);
					g2.drawString("" + i + j, x, y + sideLength - 2);
					x += sideLength;
				} else {
					g2.setColor(Color.GREEN);
					Rectangle geneRect = new Rectangle(x, y, sideLength, sideLength);
					g2.fill(geneRect);
					g2.setColor(Color.BLACK);
					g2.drawString("" + i + j, x, y + sideLength);
					x += sideLength;

				}
			}
			x = 0;
			y += sideLength;
		}
	}

	public int[] getChromosome() {
		return this.chromosome;
	}

	public int getFitnessScore() {
		return fitnessScore;
	}

}
