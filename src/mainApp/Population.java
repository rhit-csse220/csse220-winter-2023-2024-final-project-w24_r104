package mainApp;

import java.awt.Graphics2D;
import java.awt.GridLayout;
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

	public static final int ALLELE_SIDE_LENGTH = 5;

	private ArrayList<Individual> individuals = new ArrayList<Individual>();
	private double mutationRate;
	private int numGenerations = 0;
	private boolean hasFoundSolution = false;
	private boolean hasRunEvolutionaryLoop = false;

	public Population() {
		this.mutationRate = 0;
		this.individuals = new ArrayList<Individual>();
	}

	public void initializeRandomly(int populationSize, int chromosomeLength, double mutationRate) {
		this.individuals.clear();
		Random r = new Random();
		for (int i = 0; i < populationSize; i++) {
			int[] chromosome = new int[chromosomeLength];
			for (int j = 0; j < chromosomeLength; j++) {
				chromosome[j] = r.nextInt(2);
			}
			this.individuals.add(new Individual(chromosome));
		}
		this.mutationRate = mutationRate / this.individuals.size();
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

	public void runEvolutionaryLoop() {
		this.hasRunEvolutionaryLoop = true;
		this.truncationSelection();
		this.createNewGeneration();
		this.mutate();
		numGenerations++;
		System.out.println(numGenerations + "th generation");
		System.out.println("All Individuals: " + this.individuals);
		System.out.println("Best Individual: " + this.getFittestIndividual());
		if (this.getFittestIndividual().getFitness("Simple") >= 100)
			this.hasFoundSolution = true;
	}

	public void setMutationRate(double rateOutOfN) {
		this.mutationRate = rateOutOfN / individuals.get(0).getChromosome().length;
	}

	public void createNewGeneration() {
		ArrayList<Individual> newGen = new ArrayList<Individual>();
//		for (Individual individual : this.individuals) {
//			newGen.add(individual);
//		}
//		for (Individual individual : this.individuals) {
//			individual.mutate(this.mutationRate);
//			newGen.add(new Individual(individual.getChromosome()));	
//		}
		for (Individual individual : this.individuals) {
			newGen.add(individual.clone());
			newGen.add(individual.clone());
		}
		this.individuals = newGen;
	}

	public double calculateHammingDistance() {
		// get num of pairs
		int pairs = this.individuals.size() * (this.individuals.size() - 1) / 2;
		int pairwiseDiffSum = 0;
		int num0s = 0;
		int num1s = 0;
		for (int position = 0; position < getFittestIndividual().getChromosome().length; position++) {
			for (Individual i : this.individuals) {
				int[] chromosome = i.getChromosome();
				if (chromosome[position] == 0) {
					num0s++;
				} else {
					num1s++;
				}
			}
			pairwiseDiffSum += num0s * num1s;
			num0s = 0;
			num1s = 0;
		}
		double hammingDistance = pairwiseDiffSum / pairs;
		return hammingDistance;
	}

	public void truncationSelection() {
		Collections.sort(this.individuals);
		int originalPopSize = this.individuals.size();
		while (this.individuals.size() > originalPopSize / 2) {
			this.individuals.remove(this.individuals.size() - 1);
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

	public void mutateOneCell(int x, int y, int sideLength) {
		for (Individual i : this.individuals) {
			i.mutateOneCell(x, y, sideLength);
		}
	}

	public void drawOn(Graphics2D g2, int sideLength) {
		for (int i = 0; i < individuals.size() / 10; i++) {
			for (int j = 0; j < 10; j++) {
				individuals.get(10 * i + j).drawOn(g2, 10 + j * sideLength * 11, i * sideLength * 11, sideLength);
			}
		}
	}

	public Individual getFittestIndividual() {
		Collections.sort(this.individuals);
		return this.individuals.get(0);
	}

	/*
	 * ensures: return the number of individuals in population
	 */
	public int size() {
		return this.individuals.size();
	}

	public int getBestFitness() {
		int max = 0;
		for (Individual i : individuals) {
			if (i.calculateSimpleFitness() > max) {
				max = i.calculateSimpleFitness();
			}
		}
		return max;
	}

	public int getAverageFitness() {
		int totalFitness = 0;
		for (Individual i : individuals) {
			totalFitness += i.calculateSimpleFitness();
		}
		return totalFitness / individuals.size();
	}

	public int getLeastFitness() {
		int min = getBestFitness();
		for (Individual i : individuals) {
			if (i.calculateSimpleFitness() < min) {
				min = i.calculateSimpleFitness();
			}
		}
		return min;
	}

	public boolean hasFoundSolution() {
		return this.hasFoundSolution;
	}

	public void printIndividuals() {
		System.out.println(this.individuals);
	}

	public int getNumGenerations() {
		return this.numGenerations;
	}

	public boolean hasRunEvolutionaryLoop() {
		return this.hasRunEvolutionaryLoop;
	}

}
