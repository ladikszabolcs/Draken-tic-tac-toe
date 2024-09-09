package org.draken;

public class TicTacToeController {
    private TicTacToe model;
    private TicTacToeView view;

    public TicTacToeController(TicTacToe model, TicTacToeView view) {
        this.model = model;
        this.view = view;
    }

    public void playGame() {
        // Game loop
        while (true) {
            view.printBoard(model.getBoard());

            int move = view.getPlayerMove(model.getCurrentPlayer());

            // Validate the move
            if (!model.placeMove(move)) {
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