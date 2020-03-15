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
	
	public void actionButton(int i) {
		buttonArray[i].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(i);
			}
		});
	}

	public void update(int col) {
		int[] cord = model.getPosition(col);
		if(model.add(Controler.turnP, col+1)) {
			if(Controler.turnP == 'R') {
				labelArray[cord[0]][cord[1]].setBackground(Color.RED);
				Controler.turnP = 'Y';
				if(model.checkWin(Color.RED, labelArray)) { 
					victory("Red");
				}
			}
			else {
				labelArray[cord[0]][cord[1]].setBackground(Color.YELLOW);
				Controler.turnP = 'R';
				if(model.checkWin(Color.YELLOW, labelArray)) {
					victory("Yellow");
				}
			}
		}
	}

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
	
	public void createButton() {
		buttonArray = new JButton[column];
		for (int j = 0; j < column; j++) {
			buttonArray[j] = new JButton(" Add to Col " + (j+1));
			buttonArray[j].setBounds(j*120, 350, 115, 50);
			frame.add(buttonArray[j]);
			counter++;
		}
	}

	public void victory(String currentPlayer) {
		String winner = currentPlayer + " wins.\nThanks for playing!";
		int n = JOptionPane.showConfirmDialog(null, winner, "Victory!", JOptionPane.DEFAULT_OPTION); //(f,winner)
		frame.dispose();
	}
}
