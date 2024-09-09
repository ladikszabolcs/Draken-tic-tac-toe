package org.draken;

import java.util.Scanner;

public class TicTacToeView {
    private Scanner scanner;

    public TicTacToeView() {
        scanner = new Scanner(System.in);
    }

    // Display the game board
    public void printBoard(char[][] board) {
        System.out.println("Current Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Prompt the player for their move
    public int getPlayerMove(char player) {
        System.out.println("Player " + player + ", enter your move (1-9): ");
        return scanner.nextInt();
    }

    // Display win message
    public void displayWinner(char player) {
        System.out.println("Player " + player + " wins!");
    }

    // Display draw message
    public void displayDraw() {
        System.out.println("The game is a draw!");
    }

    // Display invalid move message
    public void displayInvalidMove() {
        System.out.println("Invalid move. Please try again.");
    }
}