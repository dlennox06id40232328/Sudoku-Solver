package david.sudoku;

public class Sudoku {

	public static int[][] GRID = {
			{3, 0, 6, 5, 0, 8, 4, 0, 0},
			{5, 2, 0, 0, 0, 0, 0, 0, 0},
			{0, 8, 7, 0, 0, 0, 0, 3, 1},
			{0, 0, 3, 0, 1, 0, 0, 8, 0},
			{9, 0, 0, 8, 6, 3, 0, 0, 5},
			{0, 5, 0, 0, 9, 0, 6, 0, 0},
			{1, 3, 0, 0, 0, 0, 2, 5, 0},
			{0, 0, 0, 0, 0, 0, 0, 7, 4},
			{0, 0, 5, 2, 0, 6, 3, 0, 0},
	};
	
	public int[][] board;
	public static final int EMPTY = 0; // empty cell
	public static final int SIZE = 9; // size of grid
	
	public Sudoku(int[][] board) {
		this.board = new int[SIZE][SIZE];
		
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}
	
	private boolean isInRow(int row, int number) {
		for(int i=0; i<SIZE; i++) 
			if (board[row][i] == number)
				return true;
		return false;
		
	}
	
	private boolean isInCol(int col, int number) {
		for(int i=0; i<SIZE; i++) 
			if (board[i][col] == number)
				return true;
		return false;
		
	}
	
	private boolean isInBox(int row, int col, int number) {
		int r = row - row % 3;
		int c = col - col % 3;
		
		for(int i = r; i < r+3; i++) 
			for(int j = c; j < c+3; j++)
				if(board[i][j] == number)
					return true;
		return false;
	}
	
	private boolean isOk(int row, int col, int number) {
		return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
	}
	
	// Solve method using BackTracking algorithm
	
	public boolean solve() {
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				// search for empty cell
				if(board[row][col] == EMPTY) {
					// try possible numbers
					for(int number = 1; number <= SIZE; number++) {
						if(isOk(row, col, number)) {
							board[row][col] = number;
						
							if(solve()) {
								return true;
							}	else {
								board[row][col] = EMPTY;	
							}				
						}
					}
	
					return false; // not solved
		
				}	
			}
		}
		
		return true; // solved
	}
	
	public void display() {
		for(int i=0; i < SIZE; i++) {
			for(int j=0; j < SIZE; j++) {
				System.out.print(" " +board[i][j]);
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(GRID);
		System.out.println("Sudoku grid to solve: \n");
		sudoku.display();
		
		if(sudoku.solve()) {
			System.out.println("Solved: \n");
			sudoku.display();
		}	else {
			System.out.println("Unsolvable. \n");
		}
	}

}
