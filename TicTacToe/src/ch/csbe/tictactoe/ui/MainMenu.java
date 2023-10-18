package ch.csbe.tictactoe.ui;

import ch.csbe.tictactoe.ai.AIPlayer;
import ch.csbe.tictactoe.game.Game;
import ch.csbe.tictactoe.scanner.TicTacToe;

public class MainMenu {
    public static void show() {
        System.out.println("Welcome to the best TICTACTOE Game you'll ever play.");
        System.out.println();
        System.out.println("Hint: Enter 'q' at any time to leave the game!");
        System.out.println();
        System.out.println("Press Enter to start the game");

        String input = TicTacToe.inputScanner.nextLine();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Enter '1' to play against a human or '2' to play against an AI!");
            input = TicTacToe.inputScanner.nextLine();
        }

        switch (input) {
            case "1":
                Game.reset();
                GameMenu.show();
                break;
            case "2":
                playAgainstAI();
                break;
            default:
                System.out.println("Invalid input. Exiting the game.");
                break;
        }
    }

    private static void playAgainstAI() {
        System.out.println();
        System.out.println("Choose AI level (1, 2, 3)");
        String levelInput = TicTacToe.inputScanner.nextLine();
        int level;
        try {
            level = Integer.parseInt(levelInput);
        } catch (NumberFormatException e) {
            level = 1;
        }

        AIPlayer aiPlayer = new AIPlayer(level);
        aiPlayer.play();

        System.out.println();
        System.out.println("Do you want to play again? (y/n)");
        String playAgain = TicTacToe.inputScanner.nextLine();
        if (playAgain.equalsIgnoreCase("y")) {
            playAgainstAI();
        }
    }
}
