import java.awt.*;
import java.awt.event.*;

import javax.swing.*;  

public class MyGridLayout {  
	private JFrame f;
	private JPanel p;
	private final int row = 6;
	private final int column = 7;
	JButton[] buttonArray;
	JLabel[][] rect;
	int counter = 0;
	Grid g;
	Model m;
	public MyGridLayout(){
		g = new Grid();  
		m = new Model(g);
		f = new JFrame(); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p = new JPanel();
		f.getContentPane();
		buttonArray = new JButton[column];
			for (int j = 0; j < column; j++) {
				buttonArray[j] = new JButton(" Add to Col " + (j+1));
				buttonArray[j].setBounds(j*120, 350, 115, 50);
				f.add(buttonArray[j]);
				counter++;
			}	
		int y = 250;
		rect = new JLabel[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				rect[i][j] = new JLabel("  Column " + (j+1) + " [" + i + ", " + j + "]");
				rect[i][j].setBackground(Color.WHITE);
				rect[i][j].setOpaque(true);
				rect[i][j].setBounds(j*120, y, 115, 49);
				rect[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				p.setLayout(null);
				p.add(rect[i][j]);
				counter++;
			}
			y -= 50;
		}
		
		f.add(p);
		//f.setLayout(null);
		f.setSize(1200,600);  
		f.setVisible(true); 
		
		
		
		buttonArray[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(0);
			}
		});
		buttonArray[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(1);
			}
		});
		buttonArray[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(2);
			}
		});
		buttonArray[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(3);
			}
		});
		buttonArray[4].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(4);
			}
		});
		buttonArray[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(5);
			}
		});
		buttonArray[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update(6);
			}
		});
	}
	
	public void update(int col) {
		int[] cord = m.getPosition(col);
		if(m.add(Controler.turnP, col+1)) {
			if(Controler.turnP == 'R') {
				rect[cord[0]][cord[1]].setBackground(Color.RED);
				Controler.turnP = 'Y';
				if(checkWin(Color.RED)) { 
					victory("Red");
					
				}
			}
			else {
				rect[cord[0]][cord[1]].setBackground(Color.YELLOW);
				Controler.turnP = 'R';
				if(checkWin(Color.YELLOW)) {
					victory("Yellow");
				}
			}
		}
	}
	
	//returns true if any row has 4 in a row
	private boolean checkRows(Color symbol) {
		for(int row = 0; row < rect.length; row++) {
			int wincount = 0;
			for(int col = 0; col < rect[row].length; col++) {
				if(rect[row][col].getBackground() == symbol)
					wincount++;
				else
					wincount = 0;
				if(wincount == 4)
					return true;
			}
		}
		return false;
	}
	
	//returns true if any column has 4 in a row
	private boolean checkCols(Color symbol) {
		for(int col = 0; col < rect[0].length; col++) {
			int wincount = 0;
			for(int row = 0; row < rect.length; row++) {
				if(rect[row][col].getBackground() == symbol)
					wincount++;
				else
					wincount = 0;
				if(wincount == 4)
					return true;
			}
		}
		return false;
	}
	
	private boolean checkDiag(Color symbol) {
		for(int row = 3; row < rect.length; row++){
			for(int col = 0; col < rect[0].length - 3; col++){
				if (rect[row][col].getBackground() == symbol   && 
					rect[row-1][col+1].getBackground() == symbol &&
					rect[row-2][col+2].getBackground() == symbol &&
					rect[row-3][col+3].getBackground() == symbol){
					return true;
				}
			}
		}
		
		for(int row = 0; row < rect.length - 3; row++){
			for(int col = 0; col < rect[0].length - 3; col++){
				if (rect[row][col].getBackground() == symbol   && 
					rect[row+1][col+1].getBackground() == symbol &&
					rect[row+2][col+2].getBackground() == symbol &&
					rect[row+3][col+3].getBackground() == symbol){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkWin(Color symbol) {
		return (checkRows(symbol) || checkCols(symbol) || checkDiag(symbol));
	}
	
	public void victory(String currentPlayer) {
        String winner = currentPlayer + " wins.\nThanks for playing!";
        int n = JOptionPane.showConfirmDialog(null, winner, "Victory!", JOptionPane.DEFAULT_OPTION); //(f,winner)
        f.dispose();
    }
	
}
