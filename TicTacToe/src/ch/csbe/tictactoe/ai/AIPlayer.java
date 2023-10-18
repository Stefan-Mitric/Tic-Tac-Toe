package ch.csbe.tictactoe.ai;

import ch.csbe.tictactoe.scanner.TicTacToe;

public class AIPlayer {
    private char aiPlayer;
    private char humanPlayer;
    @SuppressWarnings("unused")
	private int level;

    public AIPlayer(int aiLevel) {
        this.aiPlayer = 'O';
        this.humanPlayer = 'X';
        this.level = aiLevel;
    }

    public void play() {
        char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("You are '" + humanPlayer + "'. Let's begin!");

        while (true) {
            drawBoard(board);
            if (isGameOver(board)) {
                break;
            }
            if (humanPlayer == 'X') {
                makeHumanMove(board);
                if (isGameOver(board)) {
                    break;
                }
                makeAIMove(board);
            } else {
                makeAIMove(board);
                if (isGameOver(board)) {
                    break;
                }
                makeHumanMove(board);
            }
        }

        drawBoard(board);
        printResult(board);
    }

    private void drawBoard(char[] board) {
        System.out.println("-------------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
            System.out.println("-------------");
        }
    }

    private void makeHumanMove(char[] board) {
        int move = -1;
        while (move < 0 || move > 8 || board[move] != Character.forDigit(move, 10)) {
            System.out.print("Enter your move (0-8): ");
            String input = TicTacToe.inputScanner.nextLine();
            if (input.equals("q")) {
                System.out.println("Exiting the game.");
                System.exit(0);
            }
            try {
                move = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                move = -1;
            }
            if (move < 0 || move > 8 || board[move] != Character.forDigit(move, 10)) {
                System.out.println("Invalid move. Try again.");
            }
        }
        board[move] = humanPlayer;
    }

    private void makeAIMove(char[] board) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < 9; i++) {
            if (board[i] == Character.forDigit(i, 10)) {
                board[i] = aiPlayer;
                int score = minimax(board, 0, false);
                board[i] = Character.forDigit(i, 10);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        board[bestMove] = aiPlayer;
        System.out.println("AI played at position " + bestMove);
    }

    private int minimax(char[] board, int depth, boolean isMaximizingPlayer) {
        if (isGameOver(board)) {
            return evaluate(board, depth);
        }

        if (isMaximizingPlayer) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == Character.forDigit(i, 10)) {
                    board[i] = aiPlayer;
                    int score = minimax(board, depth + 1, false);
                    board[i] = Character.forDigit(i, 10);
                    bestScore = Math.max(bestScore, score);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == Character.forDigit(i, 10)) {
                    board[i] = humanPlayer;
                    int score = minimax(board, depth + 1, true);
                    board[i] = Character.forDigit(i, 10);
                    bestScore = Math.min(bestScore, score);
                }
            }
            return bestScore;
        }
    }

    private boolean isGameOver(char[] board) {
        return hasPlayerWon(board, aiPlayer) || hasPlayerWon(board, humanPlayer) || isBoardFull(board);
    }

    private boolean hasPlayerWon(char[] board, char player) {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6} // Diagonals
        };

        for (int[] condition : winConditions) {
            if (board[condition[0]] == player && board[condition[1]] == player && board[condition[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull(char[] board) {
        for (char position : board) {
            if (position != 'X' && position != 'O') {
                return false;
            }
        }
        return true;
    }

    private int evaluate(char[] board, int depth) {
        if (hasPlayerWon(board, aiPlayer)) {
            return 10 - depth;
        } else if (hasPlayerWon(board, humanPlayer)) {
            return depth - 10;
        } else {
            return 0;
        }
    }

    private void printResult(char[] board) {
        if (hasPlayerWon(board, aiPlayer)) {
            System.out.println("AI wins!");
        } else if (hasPlayerWon(board, humanPlayer)) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
