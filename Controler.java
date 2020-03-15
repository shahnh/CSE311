import java.util.Scanner;

public class Controler {
	
	//this char represent which player's turn it is. It starts at R, but will later
	//alternate between Y and R
	public static char turnP = 'R';
	
	// Model.java contains much of the logic for the text-based view
	public static Model m;
	static int turncounter = 0;

	static boolean done = false;

	//This method runs through one turn of the text-based view
	public static void turn(char symbol, Grid grid) {
		boolean done = false;
		Scanner scan = new Scanner(System.in);
		while(!done) {
			System.out.println(symbol + " Turn");
			System.out.println("Which column do you wish to drop into?");
			grid.print();
			System.out.println();
			int col = scan.nextInt();
			if(m.add(symbol, col)) {
				done = true;
			}
			else
				System.out.println("Please choose a different column.");
		}
	}

	//This very short main function lets the player choose which view they wish to play.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter:\n1 for text base \n2 for GUI");
		int boardIn = scan.nextInt();
		if (boardIn == 1) run();
		if (boardIn == 2) GUIBase();
		if (boardIn >2 || boardIn < 1) System.out.println("Incorrect choice\nRun the program again");
	}

	//This initiates the relationship with MyGridLayout.java, which contains most of the logic for
	//the GUI-based view.
	private static void GUIBase() {
		MyGridLayout GUI = new MyGridLayout(); 
	}

	//This initiates the text-based view. It alternates through the players' turns,
	//calls upon the logic contained in Model.java and Grid.java, and announces the results of the game.
	public static void run() {
		Grid grid = new Grid();
		m = new Model(grid);
		while(!done && turncounter < 42) {			
			if (turnP == 'R') {			
				turn(turnP, grid);
				if(m.checkWin(turnP)) {
					grid.print();
					System.out.println("Congratulations " + turnP + " won!");
					done = true;
				}
				turnP = 'Y';
				turncounter++;
			}
			else {
				turn(turnP, grid);
				if(m.checkWin(turnP)) {
					grid.print();
					System.out.println("Congratulations " + turnP + " won!");
					done = true;
				}
				turnP = 'R';
				turncounter++;
			}
		}
	}
}
