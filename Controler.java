import java.util.Scanner;

public class Controler {
	public static char turnP = 'R';
	public static Model m;

	static boolean done = false;


	public static void turn(char symbol, Grid grid) {

		boolean done = false;
		Scanner scan = new Scanner(System.in);
		while(!done) {
			System.out.println(symbol + " Turn");
			System.out.println("Which column do you wish to drop into?");
			grid.print();
			//System.out.print("");
			int col = scan.nextInt();
			if(m.add(symbol, col)) {
				done = true;
			}
			else
				System.out.println("Please choose a different column.");
		}
	}


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter:\n1 for text base \n2 for GUI");
		int boardIn = scan.nextInt();
		if (boardIn == 1) run();
		if (boardIn == 2) GUIBase();
		if (boardIn >2 || boardIn < 1) System.out.println("Incorrect choice");
	}

	private static void GUIBase() {
		MyGridLayout GUI = new MyGridLayout(); 
	}

	public static void run() {
		Grid grid = new Grid();
		m = new Model(grid);
		while(!done) {			
			if (turnP == 'R') {			
				turn(turnP, grid);
				if(m.checkWin(turnP)) {
					grid.print();
					System.out.println("Congratulations " + turnP + " won!");
					done = true;
				}
				turnP = 'Y';
			}
			else {
				turn(turnP, grid);
				if(m.checkWin(turnP)) {
					grid.print();
					System.out.println("Congratulations " + turnP + " won!");
					done = true;
				}
				turnP = 'R';
			}
		}
	}
}