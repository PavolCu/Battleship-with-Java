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
    public boolean placeShip(Ship ship, Coordinate start, Coordinate end) {
        if(!isPlacementValid(start, end)) {
            return false;
        }

        if(start.getRow() == end.getRow()) {
            return placeHorizontally(ship, start, end);
        } else {
            return placeVertically(ship, start, end);
        }
    }

    private boolean isPlacementValid(Coordinate start, Coordinate end) {
        if(start.getRow() != end.getRow() && start.getCol() != end.getCol()) {
            return false;
        }
        if(start.getRow() < 0 || start.getRow() >= BOARD_SIZE || start.getCol() < 0 || start.getCol() >= BOARD_SIZE) {
            return false;
        }
        if (end.getRow() < 0 || end.getRow() >= BOARD_SIZE || end.getCol() < 0 || end.getCol() >= BOARD_SIZE) {
            return false;
        }
        // This checks if the ship is placed diagonally
        if (Math.abs(start.getRow() - end.getRow()) == Math.abs(start.getCol() - end.getCol())) {
            return false;
        }
        return true;
    }


    private boolean placeVertically(Ship ship, Coordinate start, Coordinate end) {
        int startRow = Math.min(start.getRow(), end.getRow());
        int endRow = Math.max(start.getRow(), end.getRow());
        int col = start.getCol();
        int shipSize = Coordinate.calculateDistance(start, end) + 1;
        if(endRow - startRow + 1 != shipSize) {
            return false;
        }
        for(int i = startRow; i <= endRow; i++) {
            if(board[i][col] != '~') {
                return false;
            }
        }
        for(int i = startRow; i <= endRow; i++) {
            board[i][col] = 'O';
            ship.addCoordinate(new Coordinate(i, col));
        }
        return true;
    }


    private boolean placeHorizontally(Ship ship, Coordinate start, Coordinate end) {
        int startCol = Math.min(start.getCol(), end.getCol());
        int endCol = Math.max(start.getCol(), end.getCol());
        int row = start.getRow();
        int shipSize = Coordinate.calculateDistance(start, end) + 1;
        if(endCol - startCol + 1 != shipSize) {
            return false;
        }
        for(int i = startCol; i <= endCol; i++) {
            if(board[row][i] != '~') {
                return false;
            }
        }
        for(int i = startCol; i <= endCol; i++) {
            board[row][i] = 'O';
            ship.addCoordinate(new Coordinate(row, i));
        }
        return true;
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

