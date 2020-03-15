import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Grid {
	
	//this 2d character array is used to represent the board
	protected char[][] body = new char[6][7];
	
	//The constructor fills every slot in the 2d array with 'O', which represents an empty slot.
	public Grid() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				body[i][j] = 'O';
			}
		}
	}
	
	//Calling this method prints out the 2d array in a way that's useful to the user.
	//It also tells them which columns are which in terms of input, rather than from
	//the perspective of a developer.
	public void print() {
		System.out.println("  1  2  3  4  5  6  7\n");
		for(int i = body.length-1; i >=0 ; i--) {
			String row = "";
			for(int j = 0; j < body[i].length; j++) {
				row += "  " + body[i][j];
				//row += i + " " + j + "|";
			}
			System.out.println(row);
		}
	}
}
