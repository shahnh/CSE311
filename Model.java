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
	
	public boolean checkWin(char symbol) {
		return (checkRows(symbol) || checkCols(symbol) || checkDiag(symbol));
	}
}
