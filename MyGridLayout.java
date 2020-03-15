import java.awt.*;
import java.awt.event.*;
import javax.swing.*;  

public class MyGridLayout {  
	private JFrame frame;
	private JPanel panel;
	private final int row = 6;
	private final int column = 7;
	JButton[] buttonArray;
	JLabel[][] labelArray;
	int counter = 0;
	Grid grid;
	Model model;
	
	// Initialize Grid that is 6x7 using JLabel, Model, JFrame, JPanel, and JButtons
	public MyGridLayout(){
		grid = new Grid();  
		model = new Model(grid);
		frame = new JFrame(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.getContentPane();
		createButton();
		createLabel();
		frame.add(panel);
		frame.setSize(1200,600);  
		frame.setVisible(true);
		actionButton(0);
		actionButton(1);
		actionButton(2);
		actionButton(3);
		actionButton(4);
		actionButton(5);
		actionButton(6);
	}
	
	//calls update method upon clicking a button 
	public void actionButton(int i) {
		buttonArray[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(i);
			}
		});
	}
	
	//updates the grid's color and changes the player.
	public void update(int col) {
		int[] cord = model.getPosition(col);
		if(model.add(Controler.turnP, col+1)) {
			if(Controler.turnP == 'R') {
				labelArray[cord[0]][cord[1]].setBackground(Color.RED);
				Controler.turnP = 'Y';
				if(model.checkWin(Color.RED, labelArray)) { 
					victory("Red");
				}
				checkTie();
			}
			else {
				labelArray[cord[0]][cord[1]].setBackground(Color.YELLOW);
				Controler.turnP = 'R';
				if(model.checkWin(Color.YELLOW, labelArray)) {
					victory("Yellow");
				}
				checkTie();
			}
		}
	}

	//Creates a 6x7 grid using JLabel 2d array
	public void createLabel() {
		int y = 250;
		labelArray = new JLabel[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				labelArray[i][j] = new JLabel();//"  Column " + (j+1) + " [" + i + ", " + j + "]");
				labelArray[i][j].setBackground(Color.WHITE);
				labelArray[i][j].setOpaque(true);
				labelArray[i][j].setBounds(j*120, y, 115, 49);
				labelArray[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				panel.setLayout(null);
				panel.add(labelArray[i][j]);
				counter++;
			}
			y -= 50;
		}
	}
	
	//creates a array of 7 JButton and adding to the frame
	public void createButton() {
		buttonArray = new JButton[column];
		for (int j = 0; j < column; j++) {
			buttonArray[j] = new JButton(" Add to Col " + (j+1));
			buttonArray[j].setBounds(j*120, 350, 115, 50);
			frame.add(buttonArray[j]);
			counter++;
		}
	}

	//displays a tie massage.
	public void checkTie() {
		if (model.isFull(labelArray)) {
			int n = JOptionPane.showConfirmDialog(null, "It is tie. Play again", "Tie", JOptionPane.DEFAULT_OPTION); //(if ties quit)
			frame.dispose();
		}
	}
	
	//displays a window if a player wins
	public void victory(String currentPlayer) {
		String winner = currentPlayer + " wins.\nThanks for playing!";
		int n = JOptionPane.showConfirmDialog(null, winner, "Victory!", JOptionPane.DEFAULT_OPTION); //(f,winner)
		frame.dispose();
	}
}
