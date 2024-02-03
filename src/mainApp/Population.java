package mainApp;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Population {
	private ArrayList<Individual> individuals;
	private double mutationRate;

	public Population(int populationSize) {
		this.individuals = new ArrayList<Individual>();
	}
	
	public void initializeFromFile(int populationSize, String filename)
			throws FileNotFoundException, InvalidChromosomeFormatException {
		individuals.clear();
		File f = new File(filename);
		Scanner scanner = new Scanner(f);
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			int[] chromosome = new int[line.length()];
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '0' || line.charAt(i) == '1')
					chromosome[i] = (int) line.charAt(i) - 48; // Convert to the correct decimal by referencing the
																// ASCII table
				else
					throw new InvalidChromosomeFormatException();
			}
			individuals.add(new Individual(chromosome));
		}
		scanner.close();
	}

	public void setMutationRate(double rateOutOfN) {
		this.mutationRate = rateOutOfN/individuals.get(0).getChromosome().length;
	}

	public void selection() {
		
	}

	public void crossover() {

	}

	public void mutate() {
		for (Individual i: individuals) {
			i.mutate(this.mutationRate);
		}
	}
	
	public void mutateOneCell(int x, int y) {
		for (Individual i: individuals) {
			i.mutateOneCell(x, y);
		}
	}

	public void drawOn(Graphics2D g2) {
		for (int i = 0; i < individuals.size(); i++) {
			individuals.get(i).drawOn(g2);
		}
	}

	public String getFirstChromosomeString() {
		return this.individuals.get(0).chromosomeToString();
	}

	public Individual getFirstIndividual() {
		return this.individuals.get(0);
	}
	
	
}
