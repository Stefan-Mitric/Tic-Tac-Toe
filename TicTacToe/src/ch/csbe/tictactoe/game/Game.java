package ch.csbe.tictactoe.game;

public class Game {
	
	private static char[][] grid;
	
	public static int currentPlayer = 1;
	
	public static final char SYMBOL_EMPTY = ' ';
	public static final char SYMBOL_PLAYER_1 = 'x';
	public static final char SYMBOL_PLAYER_2 = 'o';
	
	
	public static void reset() {
		grid = new char[3][3];
		
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid[y].length; x++) {
				grid[y][x] = ' ';
			}
		}
		
		currentPlayer = 1;
	}
	
	public static void drawGrid() {
		System.out.println(" " + grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2]);
		System.out.println("---+---+---");
		System.out.println(" " + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2]);
		System.out.println("---+---+---");
		System.out.println(" " + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2]);
	}
	
	public static boolean placeSymbol(int fielNumber) {
		fielNumber--;
		int y = fielNumber / 3;
		int x = fielNumber - (y * 3);
		
		char symbol = getCurrentPlayerSymbol();
		
		if (grid[y][x] != SYMBOL_EMPTY) {
			return false;
		}
		
		grid[y][x] = symbol;
		return true;
	}
	
	private static char getCurrentPlayerSymbol() {
		char symbol;
		if (currentPlayer == 1) {
			symbol = SYMBOL_PLAYER_1;
		}
		else {
			symbol = SYMBOL_PLAYER_2;
		}
		
		return symbol;
	}
	
	public static void switchPlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		}
		else {
			currentPlayer = 1;
		}
	}
	
	public static boolean checkForWin() {
		char symbol = getCurrentPlayerSymbol();
		
		String winningCombination = "" + symbol + symbol + symbol;
		
		String line1 = "" + grid[0][0] + grid[0][1] + grid[0][2];
		String line2 = "" + grid[1][0] + grid[1][1] + grid[1][2];
		String line3 = "" + grid[2][0] + grid[2][1] + grid[2][2];
		
		String column1 = "" + grid[0][0] + grid[1][0] + grid[2][0];
		String column2 = "" + grid[0][1] + grid[1][1] + grid[2][1];
		String column3 = "" + grid[0][2] + grid[1][2] + grid[2][2];
		
		String diagonal1 = "" + grid[0][0] + grid[1][1] + grid[2][2];
		String diagonal2 = "" + grid[0][2] + grid[1][1] + grid[2][0];
		
		if (line1.equals(winningCombination) || line2.equals(winningCombination) || line3.equals(winningCombination) || column1.equals(winningCombination) || column2.equals(winningCombination) || column3.equals(winningCombination) || diagonal1.equals(winningCombination) || diagonal2.equals(winningCombination)) {
			return true;
		}
		return false;
	}
	
	public static boolean checkForDraw() {
		for (int y = 0; y < grid.length; y++) {
			for (int x = 0; x < grid.length; x++) {
				if (grid[y][x] == SYMBOL_EMPTY) {
					return false;
				}
			}
		}
		
		return true;
	}
}


