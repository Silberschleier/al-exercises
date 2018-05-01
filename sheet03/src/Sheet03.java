import java.util.Random;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Authors: Maren Pielka, Christopher Schmidt
 * 
*/

public class Sheet03 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int iterations = 10;

		float p = 0;
		float f = 0;
		float q = 0;
		
        // Read parameters from user
        System.out.print("Choose the parameter p (rate of spontaneous growth): ");
        float tmp = reader.nextFloat();
        if(tmp >= 0 && tmp <= 1){
        	p = tmp;
        }
        
        System.out.print("Choose the parameter f (rate of spontaneous fire): ");
        tmp = reader.nextFloat();
        if(tmp >= 0 && tmp <= 1){
        	f = tmp;
        }
        
        System.out.print("Choose the parameter q (rate of induced growth): ");
        tmp = reader.nextFloat();
        if(tmp >= 0 && tmp <= 1){
        	q = tmp;
        }
        

       System.out.print("chose " + p + " for p, " + f + " for f, " + q + " for q.\n");
          
        // create grid of 101x82 cells, all initially set to "A"
        Cell[][] grid = new Cell[101][82];
        for(int i=0; i<grid.length; i++) {
        	for(int j=0; j<grid[1].length; j++) {
        		grid[i][j] = new Cell("A");
        	}
        }
        
        //initialize the neighbors
        setNeighbors(grid);
        
        FileWriter fw;
		try {
			fw = new FileWriter("results.txt");
			PrintWriter writer = new PrintWriter(fw, true);
			writer.println("A\tT\tF");
			Cell[][] tmpGrid = new Cell[grid.length][grid[1].length];
	        for(int it=0; it<iterations; it++) {
	        	//count cells in different states for each iteration and outputthe numbers to file
	        	int aCount = countCells("A", grid);
	        	int tCount = countCells("T", grid);
	        	int fCount = countCells("F", grid);
	        	writer.println(aCount + "\t" + tCount + "\t" + fCount);
	        	
	        	for(int i=0; i<grid.length; i++) {
	            	for(int j=0; j<grid[1].length; j++) {
	            		tmpGrid[i][j] = stateTransition(grid[i][j], p, q, f);
	            	}
	            }
	        	grid = tmpGrid;
	        	setNeighbors(grid);       	
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}   
		
        System.out.print("\nFinal grid:\n\n");
        printGrid(grid);
	}
	
	public static int countCells(String state, Cell[][] grid) {
		int count = 0;
		for(int i=0; i<grid.length; i++) {
        	for(int j=0; j<grid[1].length; j++) {
        		if(grid[i][j].getState() == state) {
        			count++;
        		}
        	}
        }
		return count;
	}
	
	public static void setNeighbors(Cell[][] grid){
		for(int i=0; i<grid.length; i++) {
        	for(int j=0; j<grid[1].length; j++) {
        		if(i > 0) {
        			grid[i][j].setNorthNeighbor(grid[i-1][j]);
        		}
        		else {
        			grid[i][j].setNorthNeighbor(grid[grid.length-1][j]);
        		}
        		if(j < grid[1].length-1) {
        			grid[i][j].setEastNeighbor(grid[i][j+1]);
        		}
        		else {
        			grid[i][j].setEastNeighbor(grid[i][0]);
        		}
        		if(i < grid[0].length-1) {
        			grid[i][j].setSouthNeighbor(grid[i+1][j]);
        		}
        		else {
        			grid[i][j].setSouthNeighbor(grid[0][j]);
        		}
        		if(j > 0) {
        			grid[i][j].setWestNeighbor(grid[i][j-1]);
        		}
        		else {
        			grid[i][j].setWestNeighbor(grid[i][grid[1].length-1]);
        		}
        	}
        }
	}
	
	public static Cell stateTransition(Cell cell, float p, float q, float f) {
		Cell newCell = new Cell(cell.getState());
		newCell.setNorthNeighbor(cell.getNorthNeighbor());
		newCell.setEastNeighbor(cell.getEastNeighbor());
		newCell.setSouthNeighbor(cell.getSouthNeighbor());
		newCell.setWestNeighbor(cell.getWestNeighbor());
		
		Random random = new Random();
		
		if(cell.getState()=="A") {
			// induced growth
			if(cell.getNorthNeighbor().getState() == "T" || cell.getEastNeighbor().getState() == "T"
					|| cell.getSouthNeighbor().getState() == "T" || cell.getWestNeighbor().getState() == "T") {
					float rq = random.nextFloat();
					if(rq < q) {
						newCell.setState("T");
					}
				}
			// spontaneous growth
			else {
				float rp = random.nextFloat();
				if(rp < p) {
					newCell.setState("T");
				}
			}
		}
		else if(cell.getState()=="T") {
			// induced fire
			if(cell.getNorthNeighbor().getState() == "F" || cell.getEastNeighbor().getState() == "F"
					|| cell.getSouthNeighbor().getState() == "F" || cell.getWestNeighbor().getState() == "F") {
					newCell.setState("F");
				}
			// spontaneous fire
				else {
					float rf = random.nextFloat();
					if(rf < f) {
						newCell.setState("F");
					}
				}
		}
		else if(cell.getState()=="F") {
			newCell.setState("A");
		}
		
		return newCell;
	}
	
	public static void printGrid(Cell[][] grid) {
		for(int i=0; i<grid.length; i++) {
        	for(int j=0; j<grid[1].length; j++) {
        		System.out.print(grid[i][j].getState());
        	}
        	System.out.print("\n");
        }
	}

}