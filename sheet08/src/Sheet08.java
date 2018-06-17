import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/*
 * Authors: Maren Pielka, Christopher Schmidt
 * 
 * This Evolutionary algorithm is designed to find the maximum path visiting all points from a given set,
 * with the constraint to visit each point exactly twice.
 * 
 * The parameters P and My are to be set by the user, while lambda is implicitly equal to P - My, to keep the population size 
 * the same.
 * The stopping criterion is the maximal fitness (the longest path) being greater than 42000.
 * The mutation probability is set to 0.4.
 * 
 * There is no parent selection or crossover done in this algorithm, because it is not easily applicable to that problem.
 * All random changes in the subsequent generations are therefore done by mutation.
 * 
*/

public class Sheet08 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
        // Read parameter P from user
     	int P = 0;
        System.out.print("Choose the parameter P (size of the population): ");
        int tmp1 = reader.nextInt();
        if(tmp1 > 0){
        	P = tmp1;
        }
        
        // Read parameter My from user
 		int my = P/3;
		System.out.print("Choose the parameter My (number of individuals to be kept after external selection): ");
		int tmp2 = reader.nextInt();
		if(tmp2 > 0 && tmp2 <= P){
			my = tmp2;
		}
        
        // read data from file
        File file = new File("C:/Users/Maren/eclipse-workspace/Evolutionary Algorithm/src/Positions_PA-F.txt");
        
        // save the city coordinates in a 2 dim array
        int[][] cities = new int[46][3];
        
        BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
	        int i = 0;
	        try {
	        	String[] splitList = null;
	        	String st = "";
	        	for (st = br.readLine(); st != null; st = br.readLine()) {
	        		// ignore the first and last line
					if(st != null && i != 0) {
						splitList = st.split("\t");
						if(splitList[1].trim() != "" && splitList != null) {
							cities[i-1][0] = i;
							cities[i-1][1] = Integer.parseInt(splitList[1].trim());
							cities[i-1][2] = Integer.parseInt(splitList[2].trim());					
						}
					}	
					i ++;
	        	}
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	      
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		// individual: a sequence of indices, indicating the order of visit
        
        // population: random order of visits that obey the constraints (visit each city twice)
        int[][] population = new int[P][92];
        // randomly generate P individuals
        for(int i=0; i<P; i++) {
        	population[i] = produceValidIndividual();
        }

       
        // EA loop (selection, mutation etc)
        float[] fitnesses = new float[P];
        float bestFitness = 0;
        float worstFitness = 100000;
        float meanFitness = 0;
        int numGen = 1;
        int[] bestIndividual = new int[92];

        try {
        	FileWriter fw = new FileWriter("results.txt");
			PrintWriter writer = new PrintWriter(fw, true);
			writer.println("Results for P=" + P +", my=" + my + "\n");
			writer.println("Generation\tMax\t\tMin\t\tMean");
	        while(true) {
	        	System.out.println("\nGeneration " + numGen + ": \n");
	        	
	        	// fitness evaluation: measure the length of the path
	        	for(int i=0; i<P; i++) {
	        		fitnesses[i] = evaluateFitness(population[i], cities);
	        		if (fitnesses[i] > bestFitness){
	        			bestFitness = fitnesses[i];
	        		}
	        		if (fitnesses[i] < worstFitness){
	        			worstFitness = fitnesses[i];
	        		}
	        		meanFitness += fitnesses[i];
	        	}
	        	meanFitness /= P;
	        	
	        	// sort the fitnesses
	        	float[] oldFitnesses = fitnesses;
	        	int[] sortedIndices = new int[P];
	        	for(int i=0; i<P; i++) {
	        		sortedIndices[i] = i;
	        	}
	        	for(int i=0; i<P-1; i++) {
	        		for(int j=0; j<P-1; j++) {
	        			if(fitnesses[j] < fitnesses[j+1]) {
	        				int tmp = sortedIndices[j];
	        				float tmp21 = fitnesses[j];
	        				sortedIndices[j] = sortedIndices[j+1];
	        				sortedIndices[j+1] = tmp;
	        				fitnesses[j] = fitnesses[j+1];
	        				fitnesses[j+1] = tmp21;
	        			}
	            	}
	        	}
	        	
	        	bestIndividual = population[sortedIndices[0]];
	        	// TODO output best individual in the very end
	        	
	        	// output max, min and mean fitness
	        	System.out.println("Max\t\tMin\t\tMean");
	        	System.out.println(bestFitness + "\t" + worstFitness + "\t" + meanFitness);
	        	writer.println(numGen + "\t\t" + bestFitness + "\t" + worstFitness + "\t" + meanFitness);
	        	
	        	numGen++;
	        	
	        	fitnesses = oldFitnesses;
	        	
	        	// external selection (keep the best my individuals)
	        	int[][] newPopulation = new int[P][92];
	        	for(int i=0; i<my; i++) {
	        		newPopulation[i] = population[sortedIndices[i]];
	        	}
	        	
	        	// check for stopping criterion
	        	if(bestFitness>42000) {
	        		break;
	        	}
	        	
	        	// parent selection omitted, because not applicable to this scenario.
	        	
	        	// mutation: randomly alter a route (swap indices)
	        	
	        	// first produce (P - my) copies from the reduced population
	        	Random random2 = new Random();
	        	int counter = 0;
	        	double mutProb = 0.4;
	        	for(int it=0; it<= P/my; it++) {
	        		for (int i=0; i<my; i++) {
	        			if(my + counter > P-1) {	
		    				break;
		    			}	        			
		    			newPopulation[my+counter] = newPopulation[i].clone();
		    			for(int j = 0; j<91; j++) {
			        		if (random2.nextDouble() < mutProb) {
			        			int tmp31 = newPopulation[my+counter][j];
			        			newPopulation[my+counter][j]= newPopulation[my+counter][j+1];
			        			newPopulation[my+counter][j+1] = tmp31;
			        		}
			        		
						}
		        		counter ++;	
		        		
	        		}	
	        		if(my + counter > P-1) {	
	    				break;
	    			}
	        	}	
				
	    		population = newPopulation;
	    		/**
	    		for(int i=0; i<P; i++) {
	    			for(int j=0; j<92; j++) {
	    				System.out.print(population[i][j] + " ");
	    			}
	    			System.out.println("");
	    		}**/
    			
	        } 
	        writer.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FileWriter fw1;
		try {
			fw1 = new FileWriter("bestRoute.txt");
			PrintWriter writer = new PrintWriter(fw1, true);
			writer.println("Best route after " + (numGen-1) + " generations:\n");
			writer.println("\tPoint\tCoordinates\n");
			for(int i=1;i<92; i++) {
				writer.print(i +"\t");
				for(int j=0; j<3; j++) {
					writer.print(cities[bestIndividual[i]-1][j]+"\t");
				}
				writer.println("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
	}
	
	public static int[] produceValidIndividual(){
		int[] individual = new int[92];
		
		// each point is to appear exactly twice
		for (int i=1; i<= 46; i++) {
			individual[i-1] = i;
			individual[92-i] = i;
		}
		shuffleArray(individual);
		
		return individual;
	}
	
	 // From stack overflow :P
	  static void shuffleArray(int[] ar)
	  {
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	public static float evaluateFitness(int[] individual, int[][] cities) {
		float fitness = 0;
		for (int i=0; i< 91; i++) {
			fitness += distance(cities[individual[i]-1], cities[individual[i+1]-1]);
		}
		return fitness;
	}
	
	// returns the Euclidean distance of two points in 2dim
	public static double distance(int[] point1, int[] point2) {
		double distX = (point1[1]-point2[1])*(point1[1]-point2[1]);
		double distY = (point1[2]-point2[2])*(point1[2]-point2[2]);
		
		return Math.sqrt(distX + distY);
	}

}
