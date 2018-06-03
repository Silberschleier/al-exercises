
import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Authors: Maren Pielka, Christopher Schmidt
 * 
 * This Evolutionary algorithm is designed to find the maximum of the function
 * x_1 - x_2 + x_3 - x_4 ... +/- x_L, where x_1, x_2, ... , x_L are the genomes of the individual.
 * 
*/

public class Sheet07 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
        // Read parameter L from user
		int L = 0;
        System.out.print("Choose the parameter L (length of the individual vector): ");
        int tmp = reader.nextInt();
        if(tmp > 0){
        	L = tmp;
        }
        
        // Read parameter P from user
     	int P = 0;
        System.out.print("Choose the parameter P (size of the population): ");
        int tmp1 = reader.nextInt();
        if(tmp1 > 0){
        	P = tmp1;
        }

        float[][] population = new float[P][L];
        Random random = new Random();
        // randomly generate P individuals
        for(int i=0; i<P; i++) {
        	for(int j =0; j<L; j++) {
        		population[i][j] = random.nextFloat();
        	}
        }
        
        // EA loop (selection, mutation etc)
        float[] fitnesses = new float[P];
        float bestFitness = 0;
        int numGen = 1;
        while(true) {
        	System.out.println("\nGeneration " + numGen + ": \n");
        	numGen++;
        	
        	// fitness evaluation
        	System.out.println("Before selection:\n");
        	System.out.println("Individual / Fitness\n");
        	for(int i=0; i<P; i++) {
        		fitnesses[i] = evaluateFitness(population[i]);
        		System.out.println("Individual " + i + ": ");
        		if (fitnesses[i] > bestFitness){
        			bestFitness = fitnesses[i];
        		}
        		for(int j =0; j<L; j++) {
            		System.out.print("x_" + j + " = " + population[i][j] + ", ");
            	}
        		System.out.println(": " + fitnesses[i]);
        	}

        	// external selection (keep only the best individual)
        	float[] survivingInd = new float[L];
        	for(int i=0; i<P; i++) {
        		if (fitnesses[i] != bestFitness) {
        			// delete the worse individual
        			for(int j =0; j<L; j++) {
                		population[i][j] = 0;
                	}
        		}
        		else {
					for(int j =0; j<L; j++) {
                		survivingInd[j] = population[i][j];
                	}
        		}
        	}
        	
        	// check for stopping criterion
        	if(bestFitness > 2*L/3) {
        		break;
        	}
        	
        	// parent selection
        	// omitted for now
        	
        	// inheritance
        	int newIndIndex = 0;
        	for(int i=0; i<P; i++) {
        		if (fitnesses[i] != bestFitness) {
        			for(int j =0; j<L; j++) {
                		population[i][j] = survivingInd[j];
                		newIndIndex = i;
                	}
        		}
        	}
        	
        	// mutation
        	Random random2 = new Random();
        	float[] r = new float[L];
        	for(int j =0; j<L; j++) {
        		// generates random numbers in the range [-0.1, 0.1]
				r[j] = (random2.nextFloat()/5)-0.1f;
				population[newIndIndex][j] += r[j];
			}
        	
        	System.out.println("\nAfter selection and mutation:\n");
        	for(int i=0; i<P; i++) {
        		System.out.println("Individual " + i + ": ");
    			for(int j =0; j<L; j++) {
    				System.out.print("x_" + j + " = " + population[i][j] + ", ");
        		}
    			System.out.println(": " + fitnesses[i]);
        	}
        	
        }
        
	}
	
	public static float evaluateFitness(float[] individual) {
		float fitness = 0;
		for(int i=0; i<individual.length; i++) {
			if ((i % 2) == 0) {
				fitness += individual[i];
			}
			else {
				fitness -= individual[i];
			}
		}
		return fitness;
	}

}
