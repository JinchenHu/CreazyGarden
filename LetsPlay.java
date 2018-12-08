package a4;

//-----------------------------------------------------------------------
//Assignment 4
//Written by: Jinchen Hu ID#40080398
//For COMP 248 Section FF 2182 - Fall 2018
//
//This is a Crazy Nancy's Garden Game. First of all, the users decide the
//size of garden, the number and the name of players. Secondly, decide the
//order of game through dice. Finally, the players plant flower(1x1) or
//tree(2x2) depended on dice on their garden orderly until the winner who 
//fills up his or her garden with flowers and trees first.
//-----------------------------------------------------------------------
import java.util.Scanner;//import the package

public class LetsPlay {
	// the statements below is to create global variables which can be used in whole
	// class
	// create a global array, which stores all information of each player
	static Player[] player;
	// declare a global boolean variable, which is a condition to judge if a player
	// win the game
	static boolean sign;
	// declare a global boolean variable, which is the size of each player's garden
	static int size;
	// declare a global integer variable, which will store the serial number of the
	// winner of the game
	static int t;

	public static void main(String[] args) {
		print();//calls the method print() to print the welcome banner and the rule
		// of the game
		// declare the Scanner class
		Scanner sc = new Scanner(System.in);

		// the aim of the statements below is to ask the users to enter the size of
		// garden
		System.out.print("Enter 0 to use the default garden size or -1 to enter you own size: ");
		// reads an integer and assign it to choice
		int choice;
		do {
			choice = sc.nextInt();
			// if the choice is 0, then the size of garden is 3(default size)
			if (choice == 0) {
				size = 3;
			} else if (choice == -1) {
				// if the users input -1, then ask the users enter their garden size
				System.out.print("Enter your size: ");
				size = sc.nextInt();
			} else {
				// if the choice is neither 0 or -1, then give the users a prompt and ask them
				// to enter again
				System.out.print("Sorry but " + choice + " is not a legal choice. Enter your choice: ");
			}
			// if the users do not enter either 0 or -1, then the loop continues to be
			// executed
		} while (choice != 0 && choice != -1);

		// the aim of the statements below is to ask the users to enter the number and
		// the name of players
		// declare an integer numberOfPlayer
		int numberOfPlayers;
		do {
			System.out.print("How many gardeners will there be (minimum 2 requred to play, max allowed 10)? ");
			// read an integer and assign it to numberOfPlayers
			numberOfPlayers = sc.nextInt();
			// if the number of players is between 2 and 10, it is good and not otherwise
			if (numberOfPlayers <= 10 && numberOfPlayers >= 2) {
				System.out.println("Great, " + numberOfPlayers + " players it will be!");
			} else {
				System.out.println("** Sorry but " + numberOfPlayers + " is not legal number of palyers.");
			}
			// if the number of players is not between 2 and 10, then the loop continues to
			// be executed
		} while (numberOfPlayers < 2 || numberOfPlayers > 10);
		System.out.println();
		// creates a Player array, the size is the number of players
		player = new Player[numberOfPlayers];
		// traverses the array
		for (int i = 0; i < player.length; i++) {
			System.out.print("--> Name of player " + (i + 1) + " (no spaces allowed): ");
			// read a String and assign it to name
			String name = sc.next();
			// initialize the Player object
			player[i] = new Player(name, size);
		}

		// the aim of the statements below is to decide who goes first and then
		// rearrange the sequence of the game;
		// for example, if the there are 4 people and 2nd goes fist, then the order is
		// 2, 3, 4, 1
		// create a integer array that stores the sum of dice of every player
		int[] sum = new int[numberOfPlayers];
		// declare a boolean variable which is a condition that judges if there exists
		// two same number
		boolean flag;
		// create the Dice object
		Dice dice = new Dice();
		System.out.println();
		System.out.println("Let's see who goes first ...");
		// declare a integer variable
		int temp;
		do {
			// assign false to flag
			flag = false;
			// the for loop is to store the sum of dice of each player
			for (int i = 0; i < sum.length; i++) {
				// call the rollDice() method to assign a random number to each element of the
				// array
				sum[i] = dice.rollDice();
				System.out.println(player[i].getName() + " rolled a " + sum[i]);
			}
			// the two for loops is to traverse the sum[] array and compare each integer,
			// if there exists two same number, then start over the do...while loop
			loop1: for (int i = 0; i < sum.length - 1; i++) {
				for (int j = i + 1; j < sum.length; j++) {
					if (sum[i] == sum[j]) {
						flag = true;
						System.out.println("We will start over as " + player[i].getName() + " and "
								+ player[j].getName() + " rolled samely\r");
					}
					// if the flag is true then end the fist for loop
					if (flag)
						break loop1;
				}
			}

			// the aim of the statements below is to decide who goes first
			// declare an integer and assign 0 to it
			int max = 0;
			// assign 0 to temp
			temp = 0;
			if (!flag) {
				// traverses the sum array and compares each figure, then decides which one is
				// the greatest.
				for (int i = 0; i < sum.length; i++) {
					if (sum[i] > max) {
						max = sum[i];
						temp = i;
					}
				}
				System.out.println("\r" + player[temp].getName() + " gose first.");
			}
			// if flag is true; namely, there exists two same sum of dice, then continues to
			// execute the do...while loop
		} while (flag);

		// the aim of the statements below is to rearrange the order of players
		// create a String array to stores the name of players
		String[] s = new String[numberOfPlayers];
		// traverses the String array and assign the players' name to each element of
		// s[] array
		for (int i = 0; i < s.length; i++) {
			s[i] = player[i].getName();
		}
		// declare an integer variable and assign temp which stores the name of first
		// player to it
		int x = temp;
		// declare an integer variable y and assign 0 to it
		int y = 0;
		// rearrange the order of players with for loop
		for (int i = 0; i < numberOfPlayers; i++) {
			// if x is smaller than the length of s, then assign the name from temp index to
			// the last index
			if (x < s.length) {
				player[i] = new Player(s[x], size);
				x++;
			} else if (x >= s.length)
			// if x is greater than or equal to then length of s. then assign the name from
			// the fist index to the index before temp
			{
				player[i] = new Player(s[y], size);
				y++;
			}
		}
		System.out.println();

		// the aim if the statement blow is to start the game
		System.out.println("Time to play!!!");
		System.out.println("***************");
		// create the Dice object
		Dice d = new Dice();
		// create an integer variable
		int sumOfDice;
		// declare an integer, which is used to count the number of turns it takes to
		// win
		int round = 0;
		loop2: do {
			// round execute one increment as long as the do...while loop is been programmed
			round++;
			System.out.println();
			System.out.println("------------- ROUND " + round + " -------------");
			// traverse the player[] array which make all the players throw the dice orderly
			for (t = 0; t < numberOfPlayers; t++) {
				// call the rollDice() method and assign a random integer to sumOfDice
				sumOfDice = d.rollDice();
				// display the sum of dice and each die of the player
				System.out.println();
				System.out.println(player[t].getName() + " you rolled " + sumOfDice + " (Die1: " + d.getDie1()
						+ " Die2: " + d.getDie2() + ")");
				// decide the number and location of flowers and trees the player will plant and
				// if one of locations
				// will be removed with if(else) statements respectively
				// if the player's sum of dice is 3, then they can plant a tree and then a
				// flower
				if (sumOfDice == 3) {
					System.out.println("You must plant a tree(2x2) and a flower(1x1)");
					// print the present content of garden of the player
					System.out.println(player[t].toString());
					// prompt the player how many trees they can plant
					System.out.println("Let's start with the tree. You have " + player[t].howManyTreesPossible()
							+ " places to do this.");
					// if there is no room to plant tree, the player will miss this turn and give
					// him or her a prompt.
					// However, they can plant flowers if there exists rooms to do so
					// if not, then ask the player to enter a set of coordinate they are going to
					// plant
					if (player[t].howManyTreesPossible() == 0) {
						System.out.println("** Sorry no room left to plant a tree");
					} else {
						System.out.print("Enter coordinates as row column: ");
						// declare a boolean variable flagCanT3a
						boolean flagCanT3A = false;
						// declare a boolean variable flagValid3a
						boolean flagValid3A;
						do {
							// read a integer and assign it to r3Tree
							int r3Tree = sc.nextInt();
							// read a integer and assign it to c3Tree
							int c3Tree = sc.nextInt();
							// call the method ifValidCoordinate(r,c) and assign the returned result to
							// flagValid3a
							flagValid3A = ifValidCoordinate(r3Tree, c3Tree);
							// if the set of coordinate is valid, then judge if the location can plant a
							// tree
							if (flagValid3A) {
								// call the method isTCanBePlanted(r,c) and assign the returned result to
								// flagCanT3a
								flagCanT3A = ifTCanBePlanted(r3Tree, c3Tree);
								// if the location can plant a tree, then do it
								if (flagCanT3A) {
									// plant a tree in his or her garden
									player[t].plantTreeInGarden(r3Tree, c3Tree);
								}
							}
							// while the location is not valid or cannot plant a tree, then continue the
							// loop
						} while (!flagCanT3A || !flagValid3A);
						// if the player fills up the garden, then end the do...while loop
						if (ifFull()) {
							System.out.println("Congratuation! You fill up the garden");
							break loop2;
						}
					}
					System.out.println("You still have a flower to plant");
					// display the present graph of garden
					System.out.println(player[t].toString());
					// tell the player how many flowers he or she can plant
					System.out.println("You have " + player[t].howManyFlowersPossible() + " places to do this.");
					System.out.print("Enter coordinates as row column: ");
					// declare a boolean variable flagCanF3b
					boolean flagCanF3B = false;
					// declare a boolean variable falgValid3b
					boolean flagValid3B;
					do {
						// read an integer and assign it to r3Flower
						int r3Flower = sc.nextInt();
						// read an integer and assign it to c3Flower
						int c3Flower = sc.nextInt();
						// call the ifValidCoordinate(r,c) method and return the value to flagValidb
						flagValid3B = ifValidCoordinate(r3Flower, c3Flower);
						// if the set of coordinate is valid, then judge if the location can plant a
						// flower
						if (flagValid3B) {
							// call the method ifFcanBePlanted(r,c) and assign the returned value to
							// flagCanF3b
							flagCanF3B = ifFCanBePlanted(r3Flower, c3Flower);
							// if the location can plant a flower, then execute it
							if (flagCanF3B) {
								player[t].plantFlowerInGarden(r3Flower, c3Flower);
							}
						}
						// while the location is not valid or cannot plant a flower, then continue the
						// loop
					} while (!flagValid3B || !flagCanF3B);
					// if the player fills up the garden, then end the do...while loop
					if (ifFull()) {
						System.out.println("Congratuation! You fill up the garden");
						break loop2;
					}

					// if the player throws 6, then they can plant 2 flowers
				} else if (sumOfDice == 6) {
					System.out.println("You must plant 2 flowers(2 times 1x1)");
					// display the present graph
					System.out.println(player[t].toString());
					// tell the player how many flowers they can plant
					System.out.println("You have " + player[t].howManyFlowersPossible() + " places to do this.");
					System.out.print("First flower - Enter coordinates as row column: ");
					// declare a boolean, and assign false to it
					boolean flagCanF6A = false;
					// declare a boolean
					boolean flagValid6A;
					do {
						// read an integer and assign it to r6A
						int r6A = sc.nextInt();
						// read an integer and assign it to c6A
						int c6A = sc.nextInt();
						// call the method and assign the returned value to flagValid6A
						flagValid6A = ifValidCoordinate(r6A, c6A);
						// if the set of coordinate is valid, then judge if the location can plant a
						// flower
						if (flagValid6A) {
							// call the method and assign the returned value to flagCanF6A
							flagCanF6A = ifFCanBePlanted(r6A, c6A);
							// if the location can plant a flower, then do it
							if (flagCanF6A) {
								player[t].plantFlowerInGarden(r6A, c6A);
							}
						}
						// if the location is not valid and cannot plant a flower, then continue the
						// loop
					} while (!flagValid6A || !flagCanF6A);
					// if the player fills up the garden, then end the do...while loop
					if (ifFull()) {
						System.out.println("Congratuation! You fill up the garden");
						break loop2;
					}
					// display the present graph of garden
					System.out.println(player[t].toString());
					// prompt the player how many flowers they can plant
					System.out.println("You have " + player[t].howManyFlowersPossible() + " places to do this.");
					System.out.print("Second flower - Enter coordinates as row column: ");
					boolean flagCanF6B = false;
					// declare a boolean
					boolean flagValid6B;
					do {
						// read an integer and assign it to r6B
						int r6B = sc.nextInt();
						// read an integer and assign it to c6B
						int c6B = sc.nextInt();
						// call the method and assign the returned value to flagValid6B
						flagValid6B = ifValidCoordinate(r6B, c6B);
						// if the set of coordinate is valid, then judge if the location can plant a
						// flower
						if (flagValid6B) {
							// call the method and assign the returned value to flagCanF6B
							flagCanF6B = ifFCanBePlanted(r6B, c6B);
							// if the location can plant a flower, then do it
							if (flagCanF6B) {
								player[t].plantFlowerInGarden(r6B, c6B);
							}
						}
						// if the location is not valid and cannot plant a flower, then continue the
						// loop
					} while (!flagValid6B || !flagCanF6B);
					// if the player fills up the garden, then end the do...while loop
					if (ifFull()) {
						System.out.println("Congratuation! You fill up the garden");
						break loop2;
					}

					// if the player throws 12, they can plant two trees
				} else if (sumOfDice == 12) {
					System.out.println("You must plant 2 trees(2 times 2x2)");

					// print the present content of garden of the player
					System.out.println(player[t].toString());
					// prompt the player how many trees they can plant
					System.out.println("You have " + player[t].howManyTreesPossible() + " places to do this.");
					// if there is no room to plant tree, the player will miss this turn and give
					// him or her a prompt.
					// if there is, then ask the player to enter a set of coordinate they are going
					// to plant
					if (player[t].howManyTreesPossible() == 0) {
						System.out.println("** Sorry no room left to plant a tree - You miss a turn");
					} else {
						System.out.print("First tree - Enter coordinates as row column: ");
						// declare a boolean variable flagCanT12A
						boolean flagCanT12A = false;
						// declare a boolean variable flagValid12A
						boolean flagValid12A;
						do {
							// read a integer and assign it to r12Tree
							int r12TreeA = sc.nextInt();
							// read a integer and assign it to c12Tree
							int c12TreeA = sc.nextInt();
							// call the method ifValidCoordinate(r,c) and assign the returned result to
							// flagValid12A
							flagValid12A = ifValidCoordinate(r12TreeA, c12TreeA);
							// if the set of coordinate is valid, then judge if the location can plant a
							// tree
							if (flagValid12A) {
								// call the method isTCanBePlanted(r,c) and assign the returned result to
								// flagCanT12A
								flagCanT12A = ifTCanBePlanted(r12TreeA, c12TreeA);
								// if the location can plant a tree, then do it
								if (flagCanT12A) {
									// plant a tree in his or her garden
									player[t].plantTreeInGarden(r12TreeA, c12TreeA);
								}
							}
							// while the location is not valid or cannot plant a tree, then continue the
							// loop
						} while (!flagCanT12A || !flagValid12A);
						// if the player fills up the garden, then end the do...while loop
						if (ifFull()) {
							System.out.println("Congratuation! You fill up the garden");
							break loop2;
						}
						// if there is no room to plant tree, the player will miss this turn and give
						// him or her a prompt.
						// if there is, then ask the player to enter a set of coordinate they are going
						// to plant
						if (player[t].howManyTreesPossible() == 0) {
							System.out.println("** Sorry no room left to plant a tree - You miss a turn");
						} else {
							//display the present garden
							System.out.println(player[t].toString());
							System.out.print("Second tree - Enter coordinates as row column: ");
							// declare a boolean variable flagCanT12B
							boolean flagCanT12B = false;
							// declare a boolean variable flagValid12B
							boolean flagValid12B;
							do {
								// read a integer and assign it to r12TreeB
								int r12TreeB = sc.nextInt();
								// read a integer and assign it to c12TreeB
								int c12TreeB = sc.nextInt();
								// call the method ifValidCoordinate(r,c) and assign the returned result to
								// flagValid12AB
								flagValid12B = ifValidCoordinate(r12TreeB, c12TreeB);
								// if the set of coordinate is valid, then judge if the location can plant a
								// tree
								if (flagValid12B) {
									// call the method isTCanBePlanted(r,c) and assign the returned result to
									// flagCanT12A
									flagCanT12B = ifTCanBePlanted(r12TreeB, c12TreeB);
									// if the location can plant a tree, then do it
									if (flagCanT12B) {
										// plant a tree in his or her garden
										player[t].plantTreeInGarden(r12TreeB, c12TreeB);
									}
								}
								// while the location is not valid or cannot plant a tree, then continue the
								// loop
							} while (!flagCanT12B || !flagValid12B);
							// if the player fills up the garden, then end the do...while loop
							if (ifFull()) {
								System.out.println("Congratuation! You fill up the garden");
								break loop2;
							}
						}
					}

					// if the player throws 5 or 10, then on location regardless of a flower or a
					// portion of a tree will be removed
				} else if (sumOfDice == 5 || sumOfDice == 10) {
					// display the present content of the garden
					System.out.println(player[t].toString());
					System.out.println("The rabbit ate something that you have planted");
					// declare a boolean and assign true to it
					boolean isFirst = true;
					// traverse the array
					loop4: for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							// if one of elements is not '-'; namely, flowers or trees were planted before,
							// assign false to isFirst and break the first for loop
							if (player[t].whatIsPlanted(i, j) != '-') {
								isFirst = false;
								break loop4;
							}
						}
					}
					// if the player have never planted anything, give him or her a prompt
					if (isFirst) {
						System.out.println("You have never planted anything - You miss a turn");
						// if they have planted something, execute the statements below
					} else {
						// declare two integers
						int r5, c5;
						// declare a character
						char eat;
						do {
							// assign a random integer between 0 and size-1 to r5
							r5 = (int) (Math.random() * size);
							// assign a random integer between 0 and size-1 to c5
							c5 = (int) (Math.random() * size);
							// call the method and assign the returned result to eat
							eat = player[t].whatIsPlanted(r5, c5);
							// if eat is equal to - which represents vacant lot, the loop will be executed
							// continuously
						} while (eat == '-');
						// while the loop ends, call the method
						player[t].eatHere(r5, c5);
						System.out.println();
						System.out.println("The rabbit ate whatever was planted in location (" + r5 + "," + c5 + ")");
						// display the graph of garden after the rabbit ate one of location
						System.out.println(player[t].toString());
					}

					// if the player throws any other even number, they can plant a tree
				} else if (sumOfDice % 2 == 0) {
					System.out.println("You must plant a tree(2x2)");
					// print the present content of garden of the player
					System.out.println(player[t].toString());
					// prompt the player how many trees they can plant
					System.out.println("You have " + player[t].howManyTreesPossible() + " places to do this.");
					// if there is no room to plant tree, the player will miss this turn and give
					// him or her a prompt.
					// if not, then ask the player to enter a set of coordinate they are going to
					// plant
					if (player[t].howManyTreesPossible() == 0) {
						System.out.println("** Sorry no room left to plant a tree - You miss a turn");
					} else {
						System.out.print("Enter coordinates as row column: ");
						// declare a boolean variable flagCanTEven
						boolean flagCanTEven = false;
						// declare a boolean variable flagValidEven
						boolean flagValidEven;
						do {
							// read a integer and assign it to rEven
							int rEven = sc.nextInt();
							// read a integer and assign it to cEven
							int cEven = sc.nextInt();
							// call the method ifValidCoordinate(r,c) and assign the returned result to
							// flagValidEven
							flagValidEven = ifValidCoordinate(rEven, cEven);
							// if the set of coordinate is valid, then judge if the location can plant a
							// tree
							if (flagValidEven) {
								// call the method isTCanBePlanted(r,c) and assign the returned result to
								// flagCanTEven
								flagCanTEven = ifTCanBePlanted(rEven, cEven);
								// if the location can plant a tree, then do it
								if (flagCanTEven) {
									// plant a tree in his or her garden
									player[t].plantTreeInGarden(rEven, cEven);
								}
							}
							// while the location is not valid or cannot plant a tree, then continue the
							// loop
						} while (!flagCanTEven || !flagValidEven);
						// if the player fills up the garden, then end the do...while loop
						if (ifFull()) {
							System.out.println("Congratuation! You fill up the garden");
							break loop2;
						}
					}

					// if the player throws any other odd number, they can plant a flower
				} else if (sumOfDice % 2 == 1) {
					System.out.println("You must plant a flower(1x1)");
					// display the present graph
					System.out.println(player[t].toString());
					// tell the player how many flowers they can plant
					System.out.println("You have " + player[t].howManyFlowersPossible() + " places to do this.");
					System.out.print("Enter coordinates as row column: ");
					// declare a boolean, and assign false to it
					boolean flagCanFOdd = false;
					// declare a boolean
					boolean flagValidOdd;
					do {
						// read an integer and assign it to rOdd
						int rOdd = sc.nextInt();
						// read an integer and assign it to cOdd
						int cOdd = sc.nextInt();
						// call the method and assign the returned value to flagValidOdd
						flagValidOdd = ifValidCoordinate(rOdd, cOdd);
						// if the set of coordinate is valid, then judge if the location can plant a
						// flower
						if (flagValidOdd) {
							// call the method and assign the returned value to flagCanF6A
							flagCanFOdd = ifFCanBePlanted(rOdd, cOdd);
							// if the location can plant a flower, then do it
							if (flagCanFOdd) {
								player[t].plantFlowerInGarden(rOdd, cOdd);
							}
						}
						// if the location is not valid and cannot plant a flower, then continue the
						// loop
					} while (!flagValidOdd || !flagCanFOdd);
					// if the player fills up the garden, then end the do...while loop
					if (ifFull()) {
						System.out.println("Congratuation! You fill up the garden");
						break loop2;
					}
				}
			}
			// if the sign is false; namely, no one fills up the garden, then continue to
			// execute the loop
		} while (!sign);

		// the aim of the statements below is to display all the players' garden
		System.out.println();
		System.out.println("FINAL RESULTS");
		System.out.println("-------------");
		System.out.println("Here are the gardens after " + round + " rounds");
		// display all the players' garden in turn with for loop
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.println(player[i].getName() + "'s garden");
			System.out.println();
			System.out.println(player[i].toString());
			System.out.println();
		}
		System.out.println("\r");
		System.out.println("And the winner is ..... " + player[t].getName() + "!!!!!");
		System.out.println("what a beautiful garden you have.");
		System.out.println();
		System.out.println("Hope you had fun!!!!");

		sc.close();// close the Scanner class
	}// end of main

	// method that prints the welcome banner and the rule of the game
	public static void print() {// method that prints the welcome banner and the rule of the game
		System.out.println("\t-------****-------****-------****-------****-------****-------");
		System.out.println("\t\tWelCome to Crazy Nancy's Garden Game!");
		System.out.println("\t-------****-------****-------****-------****-------****-------\r");
		System.out.println("To win this game you need some luck with the dice and a bit of strategy."
				+ "\rYour garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. "
				+ "A\rtree takes 4 spots (2x2).\rYour roll the dice and based on the outcome you get to plant "
				+ "a pre-set number of trees\rand flowers.\r");
		System.out
				.println("Rules and their outcome:\r----------------------\r  3: plant a tree (2x2) and a flower (1x1)"
						+ "\r  6: plant a flowers (2 times 1x1)\r  12: plant 2 trees (2 times 2x2)\r  5 or 10: the rabbit "
						+ "will eat something that you have planted - might be a flower or a part of a tree (1x1)"
						+ "\r  Any other EVEN rolls: plant a tree (2x2)\r  Any other ODD rolls: plant a flower (1x1)"
						+ "\r\rMinimum number of players: 2.\rMinimum garden size: 3x3."
						+ "\rYou can only plant in empty locations. To plant a tree you give the top left\rcoordinates of the "
						+ "2x2 space\rand I will check to make sure all 4 locations are free.\rOkay .. Let's start the game! "
						+ "May the best gardener win!!!\r\r\rThe default garden size is a 3x3 square. "
						+ "You can use this default board size or change the size");
	}

	// method that judges if the garden is full or not
	public static boolean ifFull() {
		// if the method returns true, then assign false to sign
		if (player[t].isGardenFull()) {
			sign = true;
		} else {
			sign = false;
		}
		return sign;
	}

	// method that judges if the coordinate the player enter is valid, if not, give
	// them a prompt and ask to enter again
	public static boolean ifValidCoordinate(int r, int c) {
		// declare a boolean variable flag and assign false to it
		boolean flag = true;
		// if the location the player enter is off the grid then assign true to flag
		if (r > size - 1 || c > size - 1) {
			flag = false;
			System.out.println("The coordinate you enter is off the grid.");
			System.out.print("Please enter a new set of coordinate: ");
		}
		return flag;
	}

	// method that judges if the location was planted, if it was. then give the
	// player a prompt and ask them to enter again
	public static boolean ifFCanBePlanted(int r, int c) {
		// declare a boolean variable flag and assign false to it
		boolean flag = true;
		// if the set of coordinate the player enter has been planted a flower or a
		// portion of tree, then assign true to flag
		if (player[t].whatIsPlanted(r, c) != '-') {
			flag = false;
			System.out.println("** Sorry that location is already taken up by a " + player[t].whatIsPlanted(r, c));
			System.out.print("Please enter a new set of coordinates:");
		}
		return flag;
	}

	// method that judges if the locations can be planted a tree(2x2), if not, then
	// give them a prompt and ask them to enter again
	public static boolean ifTCanBePlanted(int r, int c) {
		// declare a boolean variable flag and assign false to it
		boolean flag = true;
		// if the set of coordinate the player enter and the other sets of coordinates
		// that will be planted a portion of tree has been
		// planted a flower, a portion of tree, or will be off the grid, then assign
		// true to flag
		if (r > size - 1 || c > size - 1 || r + 1 > size - 1 || c + 1 > size - 1 || player[t].whatIsPlanted(r, c) != '-'
				|| player[t].whatIsPlanted(r + 1, c) != '-' || player[t].whatIsPlanted(r, c + 1) != '-'
				|| player[t].whatIsPlanted(r + 1, c + 1) != '-') {
			flag = false;
			System.out.println("** Sorry either the row or column is not in the range of 0 to " + (size-1)
					+ "\ror your tree will be off the grid.");
			System.out.print("Please enter a new set of coordinate: ");
		}
		return flag;
	}
}// end of class
