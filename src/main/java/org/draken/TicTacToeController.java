package org.draken;

public class TicTacToeController {
    private TicTacToe model;
    private TicTacToeView view;

    public TicTacToeController(TicTacToe model, TicTacToeView view) {
        this.model = model;
        this.view = view;
    }

    public int minimax(char[][] board, boolean isMaximizing) {
        // First, check if there's a winner or a draw
        char winner = model.checkWinnerForMinimax();

        if (winner == 'O') return 1;  // AI (O) wins
        if (winner == 'X') return -1; // Human (X) wins
        if (model.checkDraw()) return 0; // It's a draw

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 1; i <= 9; i++) {
                int row = (i - 1) / 3;
                int col = (i - 1) % 3;
                if (board[row][col] != 'X' && board[row][col] != 'O') {
                    // Simulate AI move
                    board[row][col] = 'O';
                    int score = minimax(board, false);
                    board[row][col] = (char) ('1' + (i - 1)); // Undo move
                    bestScore = Math.max(score, bestScore);  // Maximize score
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 1; i <= 9; i++) {
                int row = (i - 1) / 3;
                int col = (i - 1) % 3;
                if (board[row][col] != 'X' && board[row][col] != 'O') {
                    // Simulate human move
                    board[row][col] = 'X';
                    int score = minimax(board, true);
                    board[row][col] = (char) ('1' + (i - 1)); // Undo move
                    bestScore = Math.min(score, bestScore);  // Minimize score
                }
            }
            return bestScore;
        }
    }

    public int findBestMove() {
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;

        for (int i = 1; i <= 9; i++) {
            int row = (i - 1) / 3;
            int col = (i - 1) % 3;
            if (model.getBoard()[row][col] != 'X' && model.getBoard()[row][col] != 'O') {
                // Simulate the move
                model.getBoard()[row][col] = 'O';
                int score = minimax(model.getBoard(), false);
                model.getBoard()[row][col] = (char) ('1' + (i - 1)); // Undo the move

                // If the move's score is better than the best score, update the best move
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        return bestMove;
    }

    public void playGame() {
        while (true) {
            view.printBoard(model.getBoard());

            int move;
            if (model.getCurrentPlayer() == 'X') {
                // Human Player X
                move = view.getPlayerMove(model.getCurrentPlayer());
            } else {
                // AI Player O
                System.out.println("AI is making a move...");
                move = findBestMove();
                model.placeMove(move);
            }

            // Validate the move (for human player only)
            if (model.getCurrentPlayer() == 'X' && !model.placeMove(move)) {
                view.displayInvalidMove();
                continue;
            }

            // Check for a winner
            if (model.checkWinner()) {
                view.printBoard(model.getBoard());
                view.displayWinner(model.getCurrentPlayer());
                break;
            }

            // Check for a draw
            if (model.checkDraw()) {
                view.printBoard(model.getBoard());
                view.displayDraw();
                break;
            }

            // Switch player
            model.switchPlayer();
        }
    }
}