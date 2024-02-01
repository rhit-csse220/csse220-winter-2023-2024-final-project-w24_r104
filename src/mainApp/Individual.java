package mainApp;

import java.awt.Graphics2D;
import java.util.Random;

public class Individual {

	private int[] chromosome;
	
	public Individual(int[] chromosome) {
		// TODO Auto-generated constructor stub
		this.chromosome = chromosome;
	}

	public void calculateFitness() {
		
	}
	
	public void mutate(double rate) {
		for (int num : chromosome) {
			Random r = new Random();
			if (r.nextInt(100) > 100*rate) {
				if (num == 0)
					num = 1;
				else
					num = 0;
			}
		}
	}
	
	public void saveCurrentChromosome() {
		
	}
	
	public void loadChromosomeFromFile(String filename) {
		
	}
	
	public void drawOn(Graphics2D g2d) {
		
	}
	
	public int[] getChromosome() {
		return this.chromosome;
	}
	
}
