package a4;

public class Garden {
	// declare a 2-D char array representing the garden
	private char[][] garden;

	// default constructor which creates a 3-by-3 array and initializes each
	// location to '-'
	public Garden() {
		garden = new char[3][3];
		initializeGarden();
	}

	// a constructor which creates a size-by-size array and initializes each
	// location to '-'
	public Garden(int size) {
		garden = new char[size][size];
		initializeGarden();
	}

	// method that initializes each space to '-'
	private void initializeGarden() {
		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden.length; j++) {
				garden[i][j] = '-';
			}
		}
	}

	// method that returns the character stored in location[r][z]
	public char getInlocation(int r, int c) {
		return garden[r][c];
	}

	// method which stores flower in the location[r][c]
	public void plantFlower(int r, int c) {
		garden[r][c] = 'f';
	}

	// method that stores the character 't' in the
	// location[r][c],[r+1][c],[r][c+1],[r+1][c+1]
	public void plantTree(int r, int c) {
		garden[r][c] = 't';
		garden[r + 1][c] = 't';
		garden[r][c + 1] = 't';
		garden[r + 1][c + 1] = 't';
	}

	// method that replace the character in the location[r][c] with '-'
	public void removeFlover(int r, int c) {
		garden[r][c] = '-';
	}

	// method that returns the number of places a tree can be planted
	public int countPossibleTrees() {
		// declare a integer variable places and assign 0 to it
		int places = 0;
		// traverse the array
		for (int i = 0; i < garden.length - 1; i++) {
			for (int j = 0; j < garden.length - 1; j++) {
				// if every location of the 2*2 block is '-', then places increases 1
				if (garden[i][j] == '-' && garden[i + 1][j] == '-' && garden[i][j + 1] == '-'
						&& garden[i + 1][j + 1] == '-') {
					places++;
				}
			}
		}
		return places;
	}

	// method which returns the number of places a flower can be planted
	public int countPossibleFlowes() {
		int places = 0;
		for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden.length; j++) {
				// if the location[i][j] is '-', then places increases 1
				if (garden[i][j] == '-') {
					places++;
				}
			}
		}
		return places;
	}

	// method that returns true if the garden is full and false otherwise
	public boolean gardenFull() {
		boolean isFull = true;
		loop: for (int i = 0; i < garden.length; i++) {
			for (int j = 0; j < garden.length; j++) {
				// if one of the locations is '-', then assigns false to isFull and ends the
				// loop
				if (garden[i][j] == '-') {
					isFull = false;
					break loop;
				}
			}
		}
		return isFull;
	}

	// rewrite the toString method to return the specified graph
	public String toString() {
		String content = " ";// declare a String
		String[][] s = new String[garden.length + 1][garden.length + 2];// declare a 2-D String array
		s[0][0] = " ";// assign a space to s[0][0]
		// assign the '|' to all the location of the second column with for loop
		for (int i = 0; i < garden.length + 1; i++) {
			s[i][1] = "|";
		}
		// stores the serial number to the first row
		for (int i = 2; i < garden.length + 2; i++) {
			s[0][i] = "" + (i - 2);
		}
		// stores the serial number to the first column
		for (int i = 1; i < garden.length + 1; i++) {
			s[i][0] = "" + (i - 1);
		}
		// assign every element of garden to s[][]
		for (int i = 1; i < garden.length + 1; i++) {
			for (int j = 2; j < garden.length + 2; j++) {
				s[i][j] = "" + garden[i - 1][j - 2];
			}
		}
		// stores the String with for loops
		for (int i = 0; i < garden.length + 1; i++) {
			for (int j = 0; j < garden.length + 2; j++) {
				// the location[0][0] only stores one space
				if (i == 0 && j == 0) {
					content += " ";
				} else {
					content += s[i][j] + " ";
				}
			}
			content += "\n";
		}
		return content;
	}
}// end of class
