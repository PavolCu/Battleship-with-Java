package battleship;

import java.util.Arrays;
import java.util.List;


public class Board {

    private static final int BOARD_SIZE = 10; // Assumed size
    private final char[][] board;

    private final List<Ship> ships;

    public Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], '~');
        }
        this.ships = Ship.createShips();
    }


    public String isPlacementValid(Coordinate start, Coordinate end, int length) {
        // Calculate the distance between the start and end coordinates
        int distance = Coordinate.calculateDistance(start, end) + 1;

        // Check if the distance matches the length of the ship
        if (distance != length) {
            return null;
        }

        // Check if the ship fits into the board
        if (start.getRow() < 0 || start.getRow() >= BOARD_SIZE || start.getCol() < 0 || start.getCol() >= BOARD_SIZE ||
                end.getRow() < 0 || end.getRow() >= BOARD_SIZE || end.getCol() < 0 || end.getCol() >= BOARD_SIZE) {
            return null;
        }

        // Check if the ship overlaps with other ships or if it doesn't leave enough space around it
        for (int i = Math.min(start.getRow(), end.getRow()) - 1; i <= Math.max(start.getRow(), end.getRow()) + 1; i++) {
            for (int j = Math.min(start.getCol(), end.getCol()) - 1; j <= Math.max(start.getCol(), end.getCol()) + 1; j++) {
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j] != '~') {
                    return "Error! You placed it too close to another one. Try again:";

                }
            }
        }

        return null;
    }

    public boolean placeShip(Ship ship, Coordinate start, Coordinate end, boolean isVertical) {
        int startPrimary = isVertical ? Math.min(start.getRow(), end.getRow()) : Math.min(start.getCol(), end.getCol());
        int endPrimary = isVertical ? Math.max(start.getRow(), end.getRow()) : Math.max(start.getCol(), end.getCol());
        int secondary = isVertical ? start.getCol() : start.getRow();
        int shipSize = Coordinate.calculateDistance(start, end) + 1;

        if (endPrimary - startPrimary + 1 != shipSize) {
            return false;
        }

        for (int i = startPrimary; i <= endPrimary; i++) {
            if (isVertical ? board[i][secondary] != '~' : board[secondary][i] != '~') {
                return false;
            }
        }

        for (int i = startPrimary; i <= endPrimary; i++) {
            if (isVertical) {
                board[i][secondary] = 'O';
                ship.addCoordinate(new Coordinate(i, secondary));
            } else {
                board[secondary][i] = 'O';
                ship.addCoordinate(new Coordinate(secondary, i));
            }
        }

        return true;
    }

    public boolean placeVertically(Ship ship, Coordinate start, Coordinate end) {
        int startRow = Math.min(start.getRow(), end.getRow());
        int endRow = Math.max(start.getRow(), end.getRow());
        int col = start.getCol();
        int shipSize = Coordinate.calculateDistance(start, end) + 1;
        if (endRow - startRow + 1 != shipSize) {
            return false;
        }
        for (int i = startRow; i <= endRow; i++) {
            if (board[i][col] != '~') {
                return false;
            }
        }
        for (int i = startRow; i <= endRow; i++) {
            board[i][col] = 'O';
            ship.addCoordinate(new Coordinate(i, col));
        }
        return true;
    }


    public boolean placeHorizontally(Ship ship, Coordinate start, Coordinate end) {
        int startCol = Math.min(start.getCol(), end.getCol());
        int endCol = Math.max(start.getCol(), end.getCol());
        int row = start.getRow();
        int shipSize = Coordinate.calculateDistance(start, end) + 1;
        if (endCol - startCol + 1 != shipSize) {
            return false;
        }
        for (int i = startCol; i <= endCol; i++) {
            if (board[row][i] != '~') {
                return false;
            }
        }
        for (int i = startCol; i <= endCol; i++) {
            board[row][i] = 'O';
            ship.addCoordinate(new Coordinate(row, i));
        }
        return true;
    }

    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String shoot(Coordinate target) {
        if (board[target.getRow()][target.getCol()] == 'O') {
            board[target.getRow()][target.getCol()] = 'X';
            return "You hit a ship!";
        }
        for (Ship ship : ships) {
            if (ship.isHit(target)) {
                board[target.getRow()][target.getCol()] = 'X';
                return "You hit a ship!";
            }
        }
        if (board[target.getRow()][target.getCol()] == '~') {
            board[target.getRow()][target.getCol()] = 'M';
            return "You missed!";
        }
        if (board[target.getRow()][target.getCol()] == 'M') {
            return "You've already shot there!";
        }

        return "You've already shot there!";
    }
}

