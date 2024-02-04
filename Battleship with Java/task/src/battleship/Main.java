package battleship;

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        List<Ship> ships = Ship.createShips();

        for (Ship ship : ships) {
            System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
            while (true) {

                String[] input = scanner.nextLine().split(" ");

                Coordinate start = parseCoordinate(input[0]);
                Coordinate end = parseCoordinate(input[1]);

                String error = ship.placeShip(board, start, end);
                if (error != null) {
                    System.out.println(error);
                    continue;
                }

                board.printBoard();
                break;
            }
        }
    }

    private static Coordinate parseCoordinate(String input) {
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        return new Coordinate(row, col);
    }
}
