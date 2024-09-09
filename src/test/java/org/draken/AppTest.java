package org.draken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private TicTacToe game;

    @BeforeEach
    public void setUp() {
        game = new TicTacToe(); // Initialize a new game before each test
    }

    @Test
    public void testBoardInitialization() {
        char[][] board = game.getBoard();
        assertEquals('1', board[0][0]);
        assertEquals('5', board[1][1]);
        assertEquals('9', board[2][2]);
    }

    @Test
    public void testPlayerSwitching() {
        assertEquals('X', game.getCurrentPlayer());
        game.switchPlayer();
        assertEquals('O', game.getCurrentPlayer());
    }

    @Test
    public void testMovePlacement() {
        assertTrue(game.placeMove(5));
        char[][] board = game.getBoard();
        assertEquals('X', board[1][1]);
        game.switchPlayer();
        assertTrue(game.placeMove(1));
        assertEquals('O', board[0][0]);
    }

    @Test
    public void testWinner() {
        game.placeMove(1); // X
        game.switchPlayer();
        game.placeMove(4); // O
        game.switchPlayer();
        game.placeMove(2); // X
        game.switchPlayer();
        game.placeMove(5); // O
        game.switchPlayer();
        game.placeMove(3); // X - X wins
        assertTrue(game.checkWinner());
    }

    @Test
    public void testDraw() {
        game.placeMove(1); // X
        game.switchPlayer();
        game.placeMove(2); // O
        game.switchPlayer();
        game.placeMove(3); // X
        game.switchPlayer();
        game.placeMove(5); // O
        game.switchPlayer();
        game.placeMove(4); // X
        game.switchPlayer();
        game.placeMove(6); // O
        game.switchPlayer();
        game.placeMove(8); // X
        game.switchPlayer();
        game.placeMove(7); // O
        game.switchPlayer();
        game.placeMove(9); // X
        assertTrue(game.checkDraw());
        assertFalse(game.checkWinner());
    }
}