package battleship;


import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the coordinates of the ship:");
        String[] input = scanner.nextLine().split(" ");

        Coordinate start = parseCoordinate(input[0]);

        Coordinate end = parseCoordinate(input[1]);

        int shipLength = Coordinate.calculateDistance(start, end) + 1;
        Ship ship = new Ship(shipLength);

        if (board.placeShip(ship, start, end)) {
            System.out.println("Length: " + ship.getLength());
            System.out.print("Parts: ");
            List<Coordinate> coordinates = ship.getCoordinates();
            if (start.compareTo(end) > 0) {
                Collections.reverse(coordinates);
            }
            for (Coordinate coordinate : coordinates) {
                System.out.print((char)('A' + coordinate.getRow()) + "" + (coordinate.getCol() + 1) + " ");
            }
        } else {
            System.out.println("Error!");
        }
    }
    private static Coordinate parseCoordinate(String input) {
        int row = input.charAt(0) - 'A';
        int col = Integer.parseInt(input.substring(1)) - 1;
        return new Coordinate(row, col);
    }
}
