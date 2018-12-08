package a4;

public class Player {
	private String name;// declare a String to name
	Garden garden;// declare a Garden object garden

	// constructor which takes tow parameters
	public Player(String name, int size) {
		this.name = name;
		garden = new Garden(size);
	}

	// accessor method to get name
	public String getName() {
		return name;
	}

	// method that returns the result of a call to the method countPossibleFlowers()
	public int howManyFlowersPossible() {
		return garden.countPossibleFlowes();
	}

	// method that returns the result of a call to the method countPossibleTrees()
	public int howManyTreesPossible() {
		return garden.countPossibleTrees();
	}

	// method that returns the result of a call to tthe method getInLocation()
	public char whatIsPlanted(int r, int c) {
		return garden.getInlocation(r, c);
	}

	// method that calls the method plantTree() to plant a tree in the garden in
	// location[r][c],[r+1][c],[r][c+1],[r+1][c+1]
	public void plantTreeInGarden(int r, int c) {
		garden.plantTree(r, c);
	}

	// method which calls the method plantFlower() to plant a flower in the garden
	// in location[r][c]
	public void plantFlowerInGarden(int r, int c) {
		garden.plantFlower(r, c);
	}

	// method that calls the method removeFlower() to remove a flower or a part of
	// tree in the garden
	public void eatHere(int r, int c) {
		garden.removeFlover(r, c);
	}

	// method that returns the result of a call of the method gardenFull()
	public boolean isGardenFull() {
		return garden.gardenFull();
	}

	// method that prints the content of garden
	public String toString() {
		return garden.toString();
	}

}// end of class
