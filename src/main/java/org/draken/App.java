package org.draken;

public class App {
    public static void main(String[] args) {
        // Initialize the Model, View, and Controller
        TicTacToe model = new TicTacToe();
        TicTacToeView view = new TicTacToeView();
        TicTacToeController controller = new TicTacToeController(model, view);

        // Start the game
        controller.playGame();
    }
}
