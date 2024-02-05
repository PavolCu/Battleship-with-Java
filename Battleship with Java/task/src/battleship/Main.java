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

        System.out.println("The game starts!");
        board.printBoard();

        System.out.println("Take a shot!");
        while (true) {

            String input = scanner.nextLine();
            if (input.length() < 2 || input.charAt(0) < 'A' || input.charAt(0) > 'J' ||
                    Integer.parseInt(input.substring(1)) < 1 || Integer.parseInt(input.substring(1)) > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            Coordinate target = parseCoordinate(input);
            String result = board.shoot(target);
            if (result != null) {
                System.out.println(result);
                }
           board.printBoard();
            if(result != null && result.equals("You missed!")) {
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