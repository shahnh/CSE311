import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Grid {
	
	protected char[][] body = new char[6][7];
	
	public Grid() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				body[i][j] = 'O';
			}
		}
	}
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