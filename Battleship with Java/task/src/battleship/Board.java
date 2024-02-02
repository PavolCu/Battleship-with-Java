package battleship;

import java.util.Arrays;
public class Board {

    private static int BOARD_SIZE = 10; // Assumed size
    private char[][] board;
    public Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], '~');
        }
    }
    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for(int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

