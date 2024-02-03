package battleship;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);

        List<Ship> ships = Ship.createShips();

        for (Ship ship : ships) {
            while (true) {
                System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
                String[] input = scanner.nextLine().split(" ");

                Coordinate start = parseCoordinate(input[0]);
                Coordinate end = parseCoordinate(input[1]);

                if (!ship.placeShip(board, start, end)) {
                    System.out.println("Error! Wrong ship location! Try again:");
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
