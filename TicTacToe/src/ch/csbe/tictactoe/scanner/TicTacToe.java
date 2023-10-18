package ch.csbe.tictactoe.scanner;

import java.util.Scanner;

import ch.csbe.tictactoe.ui.MainMenu;


public class TicTacToe {
	
	public static Scanner inputScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		MainMenu.show();
		
		inputScanner.close();
	}

}
