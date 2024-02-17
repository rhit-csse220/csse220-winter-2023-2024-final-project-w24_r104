package expMainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Random;

import mainApp.Population;

public class ExpIndividual {
	
	public static final int NUM_COLUMNS = 10;
	
	private int[] genotype;
	private int[] phenotype;
	private int numLearningTrialsLeft = 1000;
	
	public ExpIndividual(int[] genotype) {
		this.genotype = genotype;
		this.phenotype = genotype.clone();
	}
	
	public ExpIndividual() {
		Random r = new Random();
		int[] chromosome = new int[20];
		for (int i = 0; i < 20; i++) {
			int randomAllele = r.nextInt(4);
			if (randomAllele > 1)
				chromosome[i] = 2;
			else
				chromosome[i] = randomAllele;
		}
		this.genotype = chromosome;
		this.phenotype = chromosome.clone();
	}
	
	public void liveLife() {
		Random r = new Random();
		while (this.numLearningTrialsLeft > 0) {
			for (int i = 0; i < 20; i++) {
				if (this.genotype[i] == 2) {
					this.phenotype[i] = r.nextInt(2);
				}
			}
			if (this.phenotypeAllOnes())
				break;
			this.numLearningTrialsLeft--;
		}
	}

	public boolean phenotypeAllOnes() {
		for (int allele : this.phenotype) {
			if (allele == 0)
				return false;
		}
		return true;
	}
	
	public double calculateFitness() {
		return 1 + (19 * this.numLearningTrialsLeft / 1000);
	}

	public int[] getGenotype() {
		return this.genotype;
	}
	
	public void drawOn(Graphics2D g2, int x, int y, int sideLength) {
		int thisX = x;
		int thisY = y;
		for (int i = 0; i < this.genotype.length / NUM_COLUMNS; i++) { // iterates through rows
			for (int j = 0; j < NUM_COLUMNS; j++) { // iterates through columns
				if (this.genotype[NUM_COLUMNS * i + j] == 0)
					g2.setColor(Color.BLACK);
				else if (this.genotype[NUM_COLUMNS * i + j] == 1)
					g2.setColor(Color.GREEN);
				else						g2.setColor(Color.YELLOW);
					Rectangle geneRect = new Rectangle(thisX, thisY, sideLength, sideLength);
					g2.fill(geneRect);
					thisX += sideLength;
				}
				thisX = x;
				thisY += sideLength;
			}
		}
	
}

