package ch.csbe.tictactoe.ui;


import ch.csbe.tictactoe.game.Game;
import ch.csbe.tictactoe.scanner.TicTacToe;

public class GameMenu {
	
	public static void show() {
		
		System.out.println();
		System.out.println("Player " + Game.currentPlayer);
		System.out.println();
		System.out.println("Enter the field number (1 to 9) of the field to place your symbol at!");
		
		boolean success = false;
		
		while (!success) {
			String fieldNumberString = TicTacToe.inputScanner.nextLine();
			
			if (fieldNumberString.equals("q")) {
				success = false;
				break;
			}
			
			int fieldNumber;
			try {
				fieldNumber = Integer.parseInt(fieldNumberString);
			} catch (Exception e) {
				System.out.println("Please enter a valid number between 1 and 9!");
				continue;
			}
			
			while (fieldNumber < 1 || fieldNumber > 9) {
				System.out.println("The number must be between 1 and 9! Try again");
				fieldNumber = TicTacToe.inputScanner.nextInt();
			}
			
			success = Game.placeSymbol(fieldNumber);
			
			if (!success) {
				System.out.println("This filed is not empty. Try again!");
			}
		}
		
		if (!success) {
			MainMenu.show();
			return;
		}
		
		boolean won = Game.checkForWin();
		if (won) {
			Game.drawGrid();
			
			System.out.println();
			System.out.println("Player " + Game.currentPlayer + "won!");
			System.out.println();
			askForAnotherRound();
			
			return;
		}
		
		boolean draw = Game.checkForDraw();
		if (draw) {
			Game.drawGrid();
			
			System.out.println();
			System.out.println("it's a draw :");
			System.out.println();
			askForAnotherRound();
			
			return;
		}
		
		Game.switchPlayer();
		
		GameMenu.show();
	}
	
	private static void askForAnotherRound() {
		System.out.println("Do you want to play another round? (y or n)");
		
		String input = TicTacToe.inputScanner.nextLine();
		while (!input.equals("y") && !input.equals("n")) {
			System.out.println("y or n");
			input = TicTacToe.inputScanner.nextLine();
		}
		
		if (input.equals("y")) {
			Game.reset();
			GameMenu.show();
		}
	}

}
