package mainApp;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Population {

	public static final int[] SMILEY_CHROMOSOME = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1 };

	private ArrayList<Individual> individuals;
	private double mutationRate;

	public Population(int populationSize) {
		this.individuals = new ArrayList<Individual>();
	}

	public void initializeRandomly(int populationSize, int chromosomeLength) {
		this.individuals.clear();
		Random r = new Random();
		for (int i = 0; i < populationSize; i++) {
			int[] chromosome = new int[chromosomeLength];
			for (int j = 0; j < chromosomeLength; j++) {
				chromosome[j] = r.nextInt(2);
			}
			this.individuals.add(new Individual(chromosome));
		}
	}

	public void initializeFromFile(int populationSize, String filename)
			throws FileNotFoundException, InvalidChromosomeFormatException {
		this.individuals.clear();
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
			this.individuals.add(new Individual(chromosome));
		}
		scanner.close();
	}

	public void setMutationRate(double rateOutOfN) {
		this.mutationRate = rateOutOfN / individuals.get(0).getChromosome().length;
	}

	public void truncationSelection() {
		Collections.sort(this.individuals);
		int originalPopSize = this.individuals.size();
		while (this.individuals.size() > originalPopSize / 2) {
			this.individuals.remove(originalPopSize / 2);
		}
	}

	public void replenishPopulation() {
		int targetPopulationSize = this.individuals.size() * 2;
		for (int i = 0; i < targetPopulationSize / 2; i++) {
			this.individuals.add(this.individuals.get(i).clone());
		}
	}

	public void selectionByRouletteWheel(String fitnessMethodName) {
		double totalPopulationFitness = 0.0;
		for (Individual curIndividual : individuals) {
			totalPopulationFitness += curIndividual.getFitness(fitnessMethodName);
		}
		Random rand = new Random();
		ArrayList<Individual> newIndividuals = new ArrayList<Individual>();
		for (int i = 0; i < this.individuals.size(); i++) {
			double probabilityOfBeingChose = this.individuals.get(i).getFitness(fitnessMethodName)
					/ totalPopulationFitness;
			boolean chooseThisIndividual = rand.nextDouble(1 - probabilityOfBeingChose) == 0;
			if (chooseThisIndividual == true)
				newIndividuals.add(this.individuals.get(i));
		}
		this.individuals = newIndividuals;
	}

	public void selectionByRank(String fitnessMethodName) {
		Collections.sort(this.individuals,
				(i1, i2) -> i2.getFitness(fitnessMethodName) - i1.getFitness(fitnessMethodName));
		ArrayList<Individual> newIndividuals = new ArrayList<Individual>();
		Random rand = new Random();
		double sumOfAllRanks = 0.0;
		for (int i = 0; i < this.individuals.size(); i++) {
			sumOfAllRanks += i + 1;
		}
		for (int j = 0; j < this.individuals.size(); j++) {
			double probabilityOfBeingChose = (j + 1.0) / sumOfAllRanks;
			boolean chooseThisIndividual = rand.nextDouble(1 - probabilityOfBeingChose) == 0;
			if (chooseThisIndividual == true)
				newIndividuals.add(this.individuals.get(j));
		}
		this.individuals = newIndividuals;
	}

	public void crossover() {

	}

	public void mutate() {
		for (Individual i : this.individuals) {
			i.mutate(this.mutationRate);
		}
	}

	public void mutateOneCell(int x, int y) {
		for (Individual i : this.individuals) {
			i.mutateOneCell(x, y);
		}
	}

	public void drawOn(Graphics2D g2) {
//		for (int i = 0; i < individuals.size(); i++) {
//			individuals.get(i).drawOn(g2);
//		}
		getFittestIndividual().drawOn(g2);
	}

	public String getFirstChromosomeString() {
		return this.individuals.get(0).chromosomeToString();
	}

	public Individual getFirstIndividual() {
		return this.individuals.get(0);
	}

	public Individual getFittestIndividual() {
		Individual fittest = this.getFirstIndividual();
		for (Individual individual : this.individuals) {
			if (individual.calculateSimpleFitness() > fittest.calculateSimpleFitness())
				fittest = individual;
		}
		return fittest;
	}

}
