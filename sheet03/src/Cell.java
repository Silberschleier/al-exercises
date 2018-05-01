
public class Cell {
	
	private String state;
	private Cell northNeighbor;
	private Cell eastNeighbor;
	private Cell southNeighbor;
	private Cell westNeighbor;

	public Cell(String state) {
		this.state = state;
	}
	
	// getter methods
	
	public String getState() {
		return state;
	}
	
	public Cell getNorthNeighbor() {
		return northNeighbor;
	}
	
	public Cell getEastNeighbor() {
		return eastNeighbor;
	}
	
	public Cell getSouthNeighbor() {
		return southNeighbor;
	}
	
	public Cell getWestNeighbor() {
		return westNeighbor;
	}
	
	// setter methods
	
	public void setState(String state) {
		this.state = state;
	}
	
	public void setNorthNeighbor(Cell northNeighbor) {
		this.northNeighbor = northNeighbor;
	}
	
	public void setEastNeighbor(Cell eastNeighbor) {
		this.eastNeighbor = eastNeighbor;
	}
	
	public void setSouthNeighbor(Cell southNeighbor) {
		this.southNeighbor = southNeighbor;
	}
	
	public void setWestNeighbor(Cell westNeighbor) {
		this.westNeighbor = westNeighbor;
	}
	

	
}
