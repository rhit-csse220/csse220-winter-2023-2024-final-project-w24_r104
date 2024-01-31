package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Population {
	private double mutationRate;
	private ArrayList<Individual> individuals;

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

	public void setMutationRate(double rate) {

	}

	public void selection() {

	}

	public void crossover() {

	}
}
