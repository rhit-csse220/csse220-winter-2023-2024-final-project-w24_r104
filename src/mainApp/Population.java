package mainApp;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Population {
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

	public void selection() {

	}
	
	public void selectionByRouletteWheel(String fitnessMethodName) {
		int totalPopulationFitness = 0;
		for (Individual curIndividual : individuals) {
			totalPopulationFitness += curIndividual.getFitness(fitnessMethodName);
		}
		Random rand = new Random();
		// sort individuals based on fitness
		Collections.sort(individuals, (i1, i2) -> i1.getFitness(fitnessMethodName) - i2.getFitness(fitnessMethodName));
	}

	public void crossover() {

	}

	public void mutate() {
		for (Individual i : individuals) {
			i.mutate(this.mutationRate);
		}
	}

	public void mutateOneCell(int x, int y) {
		for (Individual i : individuals) {
			i.mutateOneCell(x, y);
		}
	}

	public void drawOn(Graphics2D g2) {
//		for (int i = 0; i < individuals.size(); i++) {
//			individuals.get(i).drawOn(g2);
//		}
		individuals.get(0).drawOn(g2);
	}

	public String getFirstChromosomeString() {
		return this.individuals.get(0).chromosomeToString();
	}

	public Individual getFirstIndividual() {
		return this.individuals.get(0);
	}

}
