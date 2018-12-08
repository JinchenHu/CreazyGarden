package a4;

public class Dice {
	private int die1;// declare the first die
	private int die2;// declare the second die

	// no argument constructor, set the value of each die to 0
	public Dice() {
		die1 = 0;
		die2 = 0;
	}

	// argument constructor
	public Dice(int die1, int die2) {
		this.die1 = die1;
		this.die2 = die2;
	}

	// accessor method to get die1
	public int getDie1() {
		return die1;
	}

	// accessor method to get die2
	public int getDie2() {
		return die2;
	}

	// method that assigns two random number between 1 and 6 to each die and return
	// the sum of two dice
	public int rollDice() {
		die1 = (int) (Math.random() * 6) + 1;
		die2 = (int) (Math.random() * 6) + 1;
		return die1 + die2;
	}

	// overwrite the toString method that returns the content of each die as a
	// String
	public String toString() {
		return "Dice [die1=" + die1 + ", die2=" + die2 + "]";
	}

}// end of class
