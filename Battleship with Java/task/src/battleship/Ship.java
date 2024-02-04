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

    public String placeShip(Board board, Coordinate start, Coordinate end) {

        if(start.getRow() != end.getRow() && start.getCol() != end.getCol()) {
            return "Error! Wrong ship location! Try again:";
        }

        int distance = Coordinate.calculateDistance(start, end) + 1;
        if(distance != this.length) {
            return "Error! Wrong length of the " + this.name + "! Try again:";
        }

        String placementError = board.isPlacementValid(start, end, this.length);
        if(placementError != null) {
            return placementError;
        }

        if(start.getRow() == end.getRow()) {
            if(board.placeHorizontally(this, start, end)) {
                for(int i = Math.min(start.getCol(), end.getCol()); i <= Math.max(start.getCol(), end.getCol()); i++) {
                    this.addCoordinate(new Coordinate(start.getRow(), i));
                }
                return null;
            }
        } else {
            if(board.placeVertically(this, start, end)) {
                for(int i = Math.min(start.getRow(), end.getRow()); i <= Math.max(start.getRow(), end.getRow()); i++) {
                    this.addCoordinate(new Coordinate(i, start.getCol()));
                }
                return null;
            }
        }

        return "Error! Coould not place the ship!";
    }

    public static List<Ship> ships = Arrays.asList(
            new Ship("Aircraft Carrier", 5),
            new Ship("Battleship", 4),
            new Ship("Submarine", 3),
            new Ship("Cruiser", 3),
            new Ship("Destroyer", 2)
    );
}
