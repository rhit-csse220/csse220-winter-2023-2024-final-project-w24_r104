package expMainApp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ExpPopulation {

	private ArrayList<ExpIndividual> individuals = new ArrayList<ExpIndividual>();
	private int totalPopulationFitness;
	private boolean hasFoundSolution = false;
	
	private int numGenerations = 0;

	public ExpPopulation() {
		for (int i = 0; i < 1000; i++) {
			this.individuals.add(new ExpIndividual());
		}
	}

	public void runEvolutionaryLoop() {
		for (ExpIndividual individual : this.individuals) {
			individual.liveLife();
		}
		createNewGeneration();

		numGenerations++;
		System.out.println(numGenerations + "th generation");
		System.out.println();
	}

	public void createNewGeneration() {
		calculateTotalPopFitness();
		ArrayList<ExpIndividual> newGen = new ArrayList<ExpIndividual>();
		while (newGen.size() < this.individuals.size()) {
			newGen.add(crossover());
		}
	}

	public ExpIndividual crossover() {
		Random r = new Random();
		int crossoverPoint = r.nextInt(this.individuals.get(0).getGenotype().length);
		ExpIndividual parent1 = getParent();
		ExpIndividual parent2 = getParent();
		int[] childChromosome = new int[parent1.getGenotype().length];
		for (int i = 0; i < parent1.getGenotype().length; i++) {
			if (i <= crossoverPoint)
				childChromosome[i] = parent1.getGenotype()[i];
			else
				childChromosome[i] = parent2.getGenotype()[i];
		}

		ExpIndividual child = new ExpIndividual(childChromosome);
		return child;
	}

	public ExpIndividual getParent() {
		Random r = new Random();
		while (true) {
			for (int i = 0; i < this.individuals.size(); i++) {
				double individualFitness = this.individuals.get(i).calculateFitness();
				if (r.nextDouble() <= individualFitness / totalPopulationFitness)
					return this.individuals.get(i);
			}
		}
	}

	public void calculateTotalPopFitness() {
		for (ExpIndividual individual : this.individuals) {
			this.totalPopulationFitness += individual.calculateFitness();
		}
	}

	public boolean hasFoundSolution() {
		if (hasFoundSolution)
			System.out.println("Found solution after " + numGenerations + " generations.");
		return this.hasFoundSolution;
	}

	public double getZeroAllelesFrequencies() {
		int count0s = 0;
		for (ExpIndividual individual : this.individuals) {
			int[] allelesCount = individual.getAllelesCount();
			count0s += allelesCount[0];
		}
		return count0s / (this.individuals.size() * ExpIndividual.GENOME_LENGTH);
	}

	public double getOneAllelesFrequencies() {
		int count1s = 0;
		for (ExpIndividual individual : this.individuals) {
			int[] allelesCount = individual.getAllelesCount();
			count1s += allelesCount[1];
		}
		return count1s / (this.individuals.size() * ExpIndividual.GENOME_LENGTH);
	}
	
	public double getUnknownAllelesFrequencies() {
		int countUnknowns = 0;
		for (ExpIndividual individual : this.individuals) {
			int[] allelesCount = individual.getAllelesCount();
			countUnknowns += allelesCount[2];
		}
		return countUnknowns / (this.individuals.size() * ExpIndividual.GENOME_LENGTH);
	}
	
	public int getNumGenerations() {
		return numGenerations;
	}


}