import java.awt.Color;

import javax.swing.JLabel;

public class Model {
	char[][] body = null;
	
	public Model(Grid grid) {
		this.body = grid.body;
	}
		
	public int[] getPosition(int col) {
		int[] result = {-1, -1};
		for(int i = 0; i < body.length; i++) {
			if (body[i][col] == 'O') {
				result[0] = i;
				result[1] = col;
				return result;
			}
		}
		return result;
	}
	
	public boolean add(char player, int col) {
		int[] cord = getPosition(col-1);
		if(cord[0] != -1 && cord[1] != -1) {
			body[cord[0]][cord[1]] = player;
			return true;
		}
		return false;
	}

	//returns true if any row has 4 in a row
	private boolean checkRows(char symbol) {
		for(int row = 0; row < body.length; row++) {
			int wincount = 0;
			for(int col = 0; col < body[row].length; col++) {
				if(body[row][col] == symbol)
					wincount++;
				else
					wincount = 0;
				if(wincount == 4)
					return true;
			}
		}
		return false;
	}
	
	//returns true if any row has 4 in a row
	private boolean checkRows(Color symbol, JLabel[][] labelArray) {
		for(int row = 0; row < labelArray.length; row++) {
			int wincount = 0;
			for(int col = 0; col < labelArray[row].length; col++) {
				if(labelArray[row][col].getBackground() == symbol)
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
	private boolean checkCols(char symbol) {
		for(int col = 0; col < body[0].length; col++) {
			int wincount = 0;
			for(int row = 0; row < body.length; row++) {
				if(body[row][col] == symbol)
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
	private boolean checkCols(Color symbol, JLabel[][] labelArray) {
		for(int col = 0; col < labelArray[0].length; col++) {
			int wincount = 0;
			for(int row = 0; row < labelArray.length; row++) {
				if(labelArray[row][col].getBackground() == symbol)
					wincount++;
				else
					wincount = 0;
				if(wincount == 4)
					return true;
			}
		}
		return false;
	}
	
	private boolean checkDiag(char symbol) {
		for(int row = 3; row < body.length; row++){
			for(int col = 0; col < body[0].length - 3; col++){
				if (body[row][col] == symbol   && 
					body[row-1][col+1] == symbol &&
					body[row-2][col+2] == symbol &&
					body[row-3][col+3] == symbol){
					return true;
				}
			}
		}
		
		for(int row = 0; row < body.length - 3; row++){
			for(int col = 0; col < body[0].length - 3; col++){
				if (body[row][col] == symbol   && 
					body[row+1][col+1] == symbol &&
					body[row+2][col+2] == symbol &&
					body[row+3][col+3] == symbol){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean checkDiag(Color symbol, JLabel[][] labelArray) {
		for(int row = 3; row < labelArray.length; row++){
			for(int col = 0; col < labelArray[0].length - 3; col++){
				if (labelArray[row][col].getBackground() == symbol   && 
					labelArray[row-1][col+1].getBackground() == symbol &&
					labelArray[row-2][col+2].getBackground() == symbol &&
					labelArray[row-3][col+3].getBackground() == symbol){
					return true;
				}
			}
		}
		
		for(int row = 0; row < labelArray.length - 3; row++){
			for(int col = 0; col < labelArray[0].length - 3; col++){
				if (labelArray[row][col].getBackground() == symbol   && 
					labelArray[row+1][col+1].getBackground() == symbol &&
					labelArray[row+2][col+2].getBackground() == symbol &&
					labelArray[row+3][col+3].getBackground() == symbol){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkWin(char symbol) {
		return (checkRows(symbol) || checkCols(symbol) || checkDiag(symbol));
	}
	
	public boolean isFull(JLabel[][] labelArray) {
		for (int i = 0; i < labelArray.length; i++) {
			for (int j = 0; j < labelArray[i].length; j++) {
				if(labelArray[i][j].getBackground() == Color.WHITE)
					return false;
			}
		}
		return true;
		
		
	}
	
	public boolean checkWin(Color symbol, JLabel[][] labelArray) {
		return (checkRows(symbol, labelArray) || checkCols(symbol, labelArray) || checkDiag(symbol, labelArray));
	}
}
