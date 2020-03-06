package mvc;

import java.util.Scanner;

public class Connect4 {
	
	
	public static void turn(char symbol, Grid grid) {
		boolean done = false;
		Scanner scan = new Scanner(System.in);
			while(!done) {
				System.out.println(symbol + " Turn");
				System.out.println("Which column do you wish to drop into?");
				grid.print();
				System.out.print("");
				if(grid.drop(symbol, scan.nextInt()))
					done = true;
				else
					System.out.println("Please choose a different column.");
		}
	}
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Grid grid = new Grid();
		
		final char PLAYER1 = 'R';
		final char PLAYER2 = 'Y';
		
		boolean done = false;
		
		while(!done) {
			turn(PLAYER1, grid);
			if(grid.checkWin(PLAYER1)) {
				System.out.println("Congratulations " + PLAYER1 + "!");
				break;
			}
			turn(PLAYER2, grid);
			if(grid.checkWin(PLAYER2)) {
				System.out.println("Congratulations " + PLAYER2 + "!");
				break;
			}
		}
	}
}
