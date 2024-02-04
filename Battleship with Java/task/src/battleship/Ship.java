package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {

    private String name;
    private int length;
    private List<Coordinate> coordinates;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.coordinates = new ArrayList<>();
    }

    public static List<Ship> createShips() {
        return ships;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }


    public void addCoordinate(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public boolean placeShip(Board board, Coordinate start, Coordinate end) {
        int distance = Coordinate.calculateDistance(start, end) + 1;
        if(distance != this.length) {
            System.out.println("Error! Wrong length of the " + this.name + "! Try again!:");
            return false;
        }

        if (start.getRow() != end.getRow() && start.getCol() != end.getCol()) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        if(!board.isPlacementValid(start, end, this.length)) {
            return false;
        }

        if(start.getRow() == end.getRow()) {
            if(board.placeHorizontally(this, start, end)) {
                for(int i = Math.min(start.getCol(), end.getCol()); i <= Math.max(start.getCol(), end.getCol()); i++) {
                    this.addCoordinate(new Coordinate(start.getRow(), i));
                }
                return true;
            }
        } else {
            if(board.placeVertically(this, start, end)) {
                for(int i = Math.min(start.getRow(), end.getRow()); i <= Math.max(start.getRow(), end.getRow()); i++) {
                    this.addCoordinate(new Coordinate(i, start.getCol()));
                }
                return true;
            }
        }

        return false;
    }

    public static List<Ship> ships = Arrays.asList(
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    );
}
