package mvc;

public class Grid {
	
	private char[][] body = new char[6][7];
	
	public Grid() {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				body[i][j] = 'O';
			}
		}
			//{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			//{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			//{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			//{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			//{'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			//{'O', 'O', 'O', 'O', 'O', 'O', 'O'}
		//};
	}
	
	//This method converts standard coordinates into the index for use within the 2d array, body.
	//If the coordinates are invalid, the index returned is negative. Be sure to check for this whenever using this method.
	private int[] coordConverter(int x, int y) {
		int[] coords = {-1, -1};
		if(x < 1 || y < 1 || x > 7 || y > 6) {
			//System.out.println(x + " " + y);
			//System.out.println("Bad input");
			return coords;
		}
		//the char at (1,1) is body[5][0]
		//the char at (3,4) is body[2][2]
		//the char at {7,6} is body[0][6]
		
		//Determining x: x = x-1
		//1 -> [][0]
		//2 -> [][1]
		//3 -> [][2]
		//4 -> [][3]
		//5 -> [][4]
		//6 -> [][5]
		//7 -> [][6]
		
		//Determining y:
		//1 -> [5][]
		//2 -> [4][]
		//3 -> [3][]
		//4 -> [2][]
		//5 -> [1][]
		//6 -> [0][]
		
		coords[1] = x-1;
		coords[0] = 6-y;
		//System.out.println(coords[0] + " " + coords[1]);
		return coords;
	}
	
	public char coordPeek(int x, int y) {
		int[] coords = coordConverter(x,y);
		if(coords[0] < 0 || coords[1] < 0) return '\\';
		return body[coords[0]][coords[1]];
	}
	
	public void bodyset(char symbol, int xcoord, int ycoord) {
		int[] coords = coordConverter(xcoord, ycoord);
		if(coords[0] < 0 || coords[1] < 0)
			System.out.println("Error: Invalid coordinates");
		else
			body[coords[0]][coords[1]] = symbol;
	}
	
	public boolean drop(char symbol, int column) {
		for(int row = 1; row < body.length+1; row++) {
			//System.out.println(column + " " + row);
			if(coordPeek(column,row) == 'O') {
				bodyset(symbol, column, row);
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		for(int i = 0; i < body.length; i++) {
			String row = "";
			for(int j = 0; j < body[i].length; j++) {
				row += "  " + body[i][j];
			}
			System.out.println(row);
		}
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
		int wincount;
		for(int row = 1; row < body.length; row++) {
			for(int col = 1; col < body[row].length; col++) {
				int[] coords = {col, row};
				wincount = 0;
				
				//UP RIGHT
				while(coordPeek(coords[0], coords[1]) != '\\') {
					
					if(coordPeek(coords[0], coords[1]) == symbol)
						wincount++;
					else
						wincount = 0;
					if(wincount == 4)
						return true;
					coords[0]++;
					coords[1]++;
				}
				coords[0] = col;
				coords[1] = row;
				//UP LEFT
				while(coordPeek(coords[0], coords[1]) != '\\') {
					if(coordPeek(coords[0], coords[1]) == symbol)
						wincount++;
					else
						wincount = 0;
					if(wincount == 4)
						return true;
					coords[0]--;
					coords[1]++;
				}
			}
		}
		return false;
	}
	
	public boolean checkWin(char symbol) {
		return (checkRows(symbol) || checkCols(symbol) || checkDiag(symbol));
	}
	
}

