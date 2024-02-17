package expMainApp;

import java.util.Random;

public class ExpIndividual {
	private int[] genotype;
	private int[] phenotype;
	private int numLearningTrialsLeft = 1000;
	public static final int GENOME_LENGTH = 20;
	
	public ExpIndividual(int[] genotype) {
		this.genotype = genotype;
		this.phenotype = genotype.clone();
	}
	
	public ExpIndividual() {
		Random r = new Random();
		int[] chromosome = new int[GENOME_LENGTH];
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

	public int[] getAllelesCount() {
		int count0s = 0;
		int count1s = 0;
		int countUnknowns = 0;
		for (int i : this.genotype) {
			if (i == 0)
				count0s++;
			else if (i == 1)
				count1s++;
			else
				countUnknowns++;
		}
		int[] allelesCount = {count0s, count1s, countUnknowns};
		return allelesCount;
	}

}
